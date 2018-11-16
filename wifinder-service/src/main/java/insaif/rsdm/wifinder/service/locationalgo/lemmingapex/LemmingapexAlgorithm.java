package insaif.rsdm.wifinder.service.locationalgo.lemmingapex;

import insaif.rsdm.wifinder.model.back.Hotspot;
import insaif.rsdm.wifinder.model.back.Location;
import insaif.rsdm.wifinder.model.back.builder.LocationBuilder;
import insaif.rsdm.wifinder.service.locationalgo.LocationAlgorithm;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Component
public class LemmingapexAlgorithm implements LocationAlgorithm {

    /**
     * The constant to add in the distance computation from the free space path loss, with the distance in Meters and
     * the frequency in MHz
     */
    private final static double FSPL_CONST_M_MHZ = 27.55;

    @Override
    public Location computeLocation(@NotNull Hotspot hotspot) {

        List<Location> locations = hotspot.getLocations();
        int size = locations.size();

        if (size < 3) {
            return null;
        }

        double[][] positions = new double[size][2];
        double[] distances = new double[size];

        for (int i = 0; i < size; i++) {

            Location location = locations.get(i);
            positions[i][0] = location.getLatitude();
            positions[i][1] = location.getLongitude();
            distances[i] = strengthToDistance(location, hotspot.getFrequency());
        }

        double[] centroid = computeTrilateration(positions, distances);

        hotspot.setComputedLocation(LocationBuilder.get()
                .setLatitude(centroid[0])
                .setLongitude(centroid[1])
                .setStrength(0)
                .build());

        return hotspot.getComputedLocation();
    }

    /**
     * Use the Lemmingapex API to compute the multi-trilateration of the positions given in parameters and theirs
     * associated distances
     * @param positions the table of the coordinates of the positions from which the trilateration is computed, [i][0]
     *                 for latitude and [1] for longitude, cannot be null.
     * @param distances the distances between the positions and the researched centroid, in the same order than
     *                 positions, cannot be null.
     * @return the table of the two coordinates of the centroid of the ellipse computed by the trilateration, [0] is
     * latitude and [1] is longitude, cannot be null.
     */
    private double[] computeTrilateration(@NotNull double[][] positions, @NotNull double[] distances) {
        TrilaterationFunction trilaterationFunction = new TrilaterationFunction(positions, distances);
        NonLinearLeastSquaresSolver solver =
                new NonLinearLeastSquaresSolver(trilaterationFunction, new LevenbergMarquardtOptimizer());
        LeastSquaresOptimizer.Optimum optimum = solver.solve();

        return optimum.getPoint().toArray();
    }

    /**
     * Get the distance between the hotspot and the location given the strength and frequency of the WiFi signal
     * @param location the location with the strength of the signal, cannot be null
     * @param frequency the frequency of the signal, cannot be negative
     */
    private double strengthToDistance(@NotNull Location location, @Positive double frequency) {

        return Math.pow(10, (FSPL_CONST_M_MHZ + (20 * Math.log10(frequency)) + ((double)location.getStrength()))/ 20);
    }
}

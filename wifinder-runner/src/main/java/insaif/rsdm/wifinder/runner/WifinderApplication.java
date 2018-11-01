package insaif.rsdm.wifinder.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"insaif.rsdm.wifinder.endpoint"})
@EntityScan(basePackages = "insaif.rsdm.wifinder.model")
public class WifinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WifinderApplication.class, args);
    }
}

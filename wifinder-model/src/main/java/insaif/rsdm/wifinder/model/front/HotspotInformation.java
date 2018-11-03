package insaif.rsdm.wifinder.model.front;

import javax.validation.constraints.NotNull;

public class HotspotInformation {

    @NotNull
    private String bssid;

    @NotNull
    private String ssid;

    @NotNull
    private int strength;

    public String getBssid() {
        return bssid;
    }

    public String getSsid() {
        return ssid;
    }

    public int getStrength() {
        return strength;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}

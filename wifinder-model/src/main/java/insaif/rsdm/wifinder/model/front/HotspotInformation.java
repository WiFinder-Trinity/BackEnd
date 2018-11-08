package insaif.rsdm.wifinder.model.front;

import javax.validation.constraints.NotNull;

public class HotspotInformation {

    @NotNull
    private String bssid;

    @NotNull
    private String ssid;

    @NotNull
    private Integer strength;

    public String getBssid() {
        return bssid;
    }

    public String getSsid() {
        return ssid;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }
}

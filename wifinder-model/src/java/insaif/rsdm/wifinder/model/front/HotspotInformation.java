package insaif.rsdm.wifinder.model.front;

public class HotspotInformation {

    private String bssid;

    private String ssid;

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

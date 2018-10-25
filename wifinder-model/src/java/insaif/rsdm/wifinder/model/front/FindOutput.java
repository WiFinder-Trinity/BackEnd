package insaif.rsdm.wifinder.model.front;

public class FindOutput {

    private String ssid;

    private String bssid;

    public String getSsid() {
        return ssid;
    }

    public String getBssid() {
        return bssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }
}

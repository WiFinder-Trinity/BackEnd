package ifinsa.rsdm.wifinder.model.front;

public class FindOutput {

    private String ssid;

    private String bssid;

    public FindOutput(String ssid, String bssid) {
        this.ssid = ssid;
        this.bssid = bssid;
    }

    public String getSsid() {
        return ssid;
    }

    public String getBssid() {
        return bssid;
    }
}

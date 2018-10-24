package ifinsa.rsdm.wifinder.model.front;

public class HotspotInformation {

    private String bssid;

    private String ssid;

    private int strengh;

    public HotspotInformation(String bssid, String ssid, int strengh) {
        this.bssid = bssid;
        this.ssid = ssid;
        this.strengh = strengh;
    }

    public String getBssid() {
        return bssid;
    }

    public String getSsid() {
        return ssid;
    }

    public int getStrengh() {
        return strengh;
    }
}

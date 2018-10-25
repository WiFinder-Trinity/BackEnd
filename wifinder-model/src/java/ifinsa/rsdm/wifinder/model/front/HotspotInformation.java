package ifinsa.rsdm.wifinder.model.front;

public class HotspotInformation {

    private String bssid;

    private String ssid;

    private int strengh;

    public String getBssid() {
        return bssid;
    }

    public String getSsid() {
        return ssid;
    }

    public int getStrengh() {
        return strengh;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public void setStrengh(int strengh) {
        this.strengh = strengh;
    }
}

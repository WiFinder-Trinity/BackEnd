package insaif.rsdm.wifinder.model.front;

import javax.validation.constraints.NotNull;

public class ConncectionInput {

    @NotNull
    private String bssid;

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }
}

package sk.akademiasovy.monikajassova.jedalnylistok.data.model2;

import java.util.List;

import sk.akademiasovy.monikajassova.jedalnylistok.data.model.Addon;

/**
 * Created by monika.jassova on 11/29/2017.
 */

public class AddOnsResponse {
    private int version;
    private List<Addon> addOns;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<Addon> getAddOns() {
        return addOns;
    }

    public void setAddOns(List<Addon> addOns) {
        this.addOns = addOns;
    }
}

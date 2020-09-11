package ai.lenna.lennachatmodul.chat.model.column;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import ai.lenna.lennachatmodul.chat.model.passenger.AdultPassenger;
import ai.lenna.lennachatmodul.chat.model.passenger.ChildPassenger;
import ai.lenna.lennachatmodul.chat.model.passenger.Contact;
import ai.lenna.lennachatmodul.chat.model.passenger.InfantPassenger;


public class ChatColumnPlanePassengerForm implements Serializable {

    @SerializedName("contact")
    @Expose
    private List<Contact> contact = null;
    @SerializedName("adultPassenger")
    @Expose
    private List<List<AdultPassenger>> adultPassenger = null;
    @SerializedName("childPassenger")
    @Expose
    private List<List<ChildPassenger>> childPassenger = null;
    @SerializedName("infantPassenger")
    @Expose
    private List<List<InfantPassenger>> infantPassenger = null;

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }

    public List<List<AdultPassenger>> getAdultPassenger() {
        return adultPassenger;
    }

    public void setAdultPassenger(List<List<AdultPassenger>> adultPassenger) {
        this.adultPassenger = adultPassenger;
    }

    public List<List<ChildPassenger>> getChildPassenger() {
        return childPassenger;
    }

    public void setChildPassenger(List<List<ChildPassenger>> childPassenger) {
        this.childPassenger = childPassenger;
    }

    public List<List<InfantPassenger>> getInfantPassenger() {
        return infantPassenger;
    }

    public void setInfantPassenger(List<List<InfantPassenger>> infantPassenger) {
        this.infantPassenger = infantPassenger;
    }
}

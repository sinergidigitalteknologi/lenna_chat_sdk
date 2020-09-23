package ai.lenna.lennachatmodul.chat.model.column;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import ai.lenna.lennachatmodul.chat.model.contact.ChatContactDetilPenumpangKereta;
import ai.lenna.lennachatmodul.chat.passenger.ChatTrainPassengerForm;

public class ChatColumnTrainPassengerForm implements Serializable {

    @SerializedName("contact")
    @Expose
    private List<ChatContactDetilPenumpangKereta> contact = null;
    @SerializedName("passenger")
    @Expose
    private List<List<ChatTrainPassengerForm>> passenger = null;

    public List<ChatContactDetilPenumpangKereta> getContact() {
        return contact;
    }

    public void setContact(List<ChatContactDetilPenumpangKereta> contact) {
        this.contact = contact;
    }

    public List<List<ChatTrainPassengerForm>> getPassenger() {
        return passenger;
    }

    public void setPassenger(List<List<ChatTrainPassengerForm>> passenger) {
        this.passenger = passenger;
    }
}

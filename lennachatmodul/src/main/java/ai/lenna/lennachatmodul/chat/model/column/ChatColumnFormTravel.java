package ai.lenna.lennachatmodul.chat.model.column;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import ai.lenna.lennachatmodul.chat.model.master.ChatMasterAirport;

@Keep
public class ChatColumnFormTravel implements Serializable {
    @SerializedName("tripType")
    @Expose
    private String tripType;
    @SerializedName("masterAirport")
    @Expose
    private ArrayList<ChatMasterAirport> masterAirport = null;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("departure")
    @Expose
    private String departure;
    @SerializedName("return")
    @Expose
    private String _return;
    @SerializedName("paxAdult")
    @Expose
    private ArrayList<Integer> paxAdult = null;
    @SerializedName("paxChild")
    @Expose
    private ArrayList<Integer> paxChild = null;
    @SerializedName("paxInfant")
    @Expose
    private ArrayList<Integer> paxInfant = null;

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public ArrayList<ChatMasterAirport> getMasterAirport() {
        return masterAirport;
    }

    public void setMasterAirport(ArrayList<ChatMasterAirport> masterAirport) {
        this.masterAirport = masterAirport;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String get_return() {
        return _return;
    }

    public void set_return(String _return) {
        this._return = _return;
    }

    public ArrayList<Integer> getPaxAdult() {
        return paxAdult;
    }

    public void setPaxAdult(ArrayList<Integer> paxAdult) {
        this.paxAdult = paxAdult;
    }

    public ArrayList<Integer> getPaxChild() {
        return paxChild;
    }

    public void setPaxChild(ArrayList<Integer> paxChild) {
        this.paxChild = paxChild;
    }

    public ArrayList<Integer> getPaxInfant() {
        return paxInfant;
    }

    public void setPaxInfant(ArrayList<Integer> paxInfant) {
        this.paxInfant = paxInfant;
    }
}

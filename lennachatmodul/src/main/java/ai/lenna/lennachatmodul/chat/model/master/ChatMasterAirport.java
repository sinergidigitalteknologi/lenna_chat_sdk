package ai.lenna.lennachatmodul.chat.model.master;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChatMasterAirport implements Serializable {
    @SerializedName("airport_code")
    @Expose
    private String airportCode;
    @SerializedName("location")
    @Expose
    private String location;

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

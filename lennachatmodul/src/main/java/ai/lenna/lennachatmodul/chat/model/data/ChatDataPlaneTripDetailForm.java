package ai.lenna.lennachatmodul.chat.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import ai.lenna.lennachatmodul.chat.airport.Airport;


public class ChatDataPlaneTripDetailForm implements Serializable {

    @SerializedName("airports")
    @Expose
    private ArrayList<Airport> airports = null;

    public ArrayList<Airport> getAirports() {
        return airports;
    }

    public void setAirports(ArrayList<Airport> airports) {
        this.airports = airports;
    }
}

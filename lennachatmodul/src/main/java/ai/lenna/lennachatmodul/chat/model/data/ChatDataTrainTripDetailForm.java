package ai.lenna.lennachatmodul.chat.model.data;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import ai.lenna.lennachatmodul.chat.model.station.ChatStationTrainTripDetailForm;

@Keep
public class ChatDataTrainTripDetailForm implements Serializable {
    @SerializedName("stations")
    @Expose
    private ArrayList<ChatStationTrainTripDetailForm> stations = null;

    public ArrayList<ChatStationTrainTripDetailForm> getStations() {
        return stations;
    }

    public void setStations(ArrayList<ChatStationTrainTripDetailForm> stations) {
        this.stations = stations;
    }

}

package ai.lenna.lennachatmodul.chat.model.output;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import ai.lenna.lennachatmodul.chat.model.column.ChatColumnTrainTripDetailForm;
import ai.lenna.lennachatmodul.chat.model.data.ChatDataTrainTripDetailForm;

public class ChatOutputTrainTripDetailForm implements Serializable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("columns")
    @Expose
    private ArrayList<ChatColumnTrainTripDetailForm> columns = null;
    @SerializedName("data")
    @Expose
    private ChatDataTrainTripDetailForm data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ChatColumnTrainTripDetailForm> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<ChatColumnTrainTripDetailForm> columns) {
        this.columns = columns;
    }

    public ChatDataTrainTripDetailForm getData() {
        return data;
    }

    public void setData(ChatDataTrainTripDetailForm data) {
        this.data = data;
    }
}

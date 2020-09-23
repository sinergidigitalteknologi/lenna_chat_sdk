package ai.lenna.lennachatmodul.chat.model.output;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ai.lenna.lennachatmodul.chat.model.column.ChatColumnPlaneTripDetailForm;
import ai.lenna.lennachatmodul.chat.model.data.ChatDataPlaneTripDetailForm;

public class ChatOutputPlaneTripDetailForm implements Serializable {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("columns")
    @Expose
    private ArrayList<ChatColumnPlaneTripDetailForm> columns = null;
    @SerializedName("data")
    @Expose
    private ChatDataPlaneTripDetailForm data;

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

    public List<ChatColumnPlaneTripDetailForm> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<ChatColumnPlaneTripDetailForm> columns) {
        this.columns = columns;
    }

    public ChatDataPlaneTripDetailForm getData() {
        return data;
    }

    public void setData(ChatDataPlaneTripDetailForm data) {
        this.data = data;
    }

}

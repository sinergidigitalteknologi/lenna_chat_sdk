package ai.lenna.lennachatmodul.chat.model.output;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import ai.lenna.lennachatmodul.chat.model.column.ChatColumnAirline;

@Keep
public class ChatOutputTravel implements Serializable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("columns")
    @Expose
    private ArrayList<ChatColumnAirline> columns = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<ChatColumnAirline> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<ChatColumnAirline> columns) {
        this.columns = columns;
    }
}

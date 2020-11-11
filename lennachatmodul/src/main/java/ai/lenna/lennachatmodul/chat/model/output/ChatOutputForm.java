package ai.lenna.lennachatmodul.chat.model.output;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import ai.lenna.lennachatmodul.chat.model.column.ChatColumnForm;

@Keep
public class ChatOutputForm implements Serializable {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("columns")
    @Expose
    private ArrayList<ChatColumnForm> columns = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<ChatColumnForm> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<ChatColumnForm> columns) {
        this.columns = columns;
    }
}

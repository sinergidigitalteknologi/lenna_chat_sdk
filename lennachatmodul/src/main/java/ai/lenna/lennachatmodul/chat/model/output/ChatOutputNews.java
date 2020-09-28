package ai.lenna.lennachatmodul.chat.model.output;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import ai.lenna.lennachatmodul.chat.model.column.ChatColumnNews;

@Keep
public class ChatOutputNews implements Serializable {
    @SerializedName("speech")
    @Expose
    private String speech;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("columns")
    @Expose
    private ArrayList<ChatColumnNews> columns = null;

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<ChatColumnNews> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<ChatColumnNews> columns) {
        this.columns = columns;
    }
}

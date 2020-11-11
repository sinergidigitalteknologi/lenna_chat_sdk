package ai.lenna.lennachatmodul.chat.model.action;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ai.lenna.lennachatmodul.chat.model.data.ChatDataMovie;

@Keep
public class ChatMovieAction implements Serializable {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("data")
    @Expose
    private ChatDataMovie data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ChatDataMovie getData() {
        return data;
    }

    public void setData(ChatDataMovie data) {
        this.data = data;
    }

}

package ai.lenna.lennachatmodul.chat.model.output;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import ai.lenna.lennachatmodul.chat.model.column.ChatColumnMovie;

public class ChatOutputMovie implements Serializable {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("imageAspectRatio")
    @Expose
    private String imageAspectRatio;
    @SerializedName("imageSize")
    @Expose
    private String imageSize;
    @SerializedName("columns")
    @Expose
    private ArrayList<ChatColumnMovie> columns = null;
    @SerializedName("speech")
    @Expose
    private String speech;

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

    public String getImageAspectRatio() {
        return imageAspectRatio;
    }

    public void setImageAspectRatio(String imageAspectRatio) {
        this.imageAspectRatio = imageAspectRatio;
    }

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public ArrayList<ChatColumnMovie> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<ChatColumnMovie> columns) {
        this.columns = columns;
    }
}

package ai.lenna.lennachatmodul.chat.model.output;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Keep
public class ChatOutputCarouselApi {
    @SerializedName("speech")
    @Expose
    private String speech;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("columns")
    @Expose
    private ArrayList<ChatColumnCarouselApi> columns = null;
    @SerializedName("imageAspectRatio")
    @Expose
    private String imageAspectRatio;
    @SerializedName("imageSize")
    @Expose
    private String imageSize;

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

    public ArrayList<ChatColumnCarouselApi> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<ChatColumnCarouselApi> columns) {
        this.columns = columns;
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
}

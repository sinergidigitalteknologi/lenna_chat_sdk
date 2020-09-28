package ai.lenna.lennachatmodul.chat.model.output;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChatOutputImage implements Serializable {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("originalContentUrl")
    @Expose
    private String originalContentUrl;
    @SerializedName("previewImageUrl")
    @Expose
    private String previewImageUrl;
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

    public String getOriginalContentUrl() {
        return originalContentUrl;
    }

    public void setOriginalContentUrl(String originalContentUrl) {
        this.originalContentUrl = originalContentUrl;
    }

    public String getPreviewImageUrl() {
        return previewImageUrl;
    }

    public void setPreviewImageUrl(String previewImageUrl) {
        this.previewImageUrl = previewImageUrl;
    }

}

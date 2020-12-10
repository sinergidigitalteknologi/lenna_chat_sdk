package ai.lenna.lennachatmodul.chat.model.output.action;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Keep
public class ChatOutputAction implements Serializable {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("speech")
    @Expose
    private String speech;
    @SerializedName("subType")
    @Expose
    private String subType;
    @SerializedName("data")
    @Expose
    private ChatDataAction data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public ChatDataAction getData() {
        return data;
    }

    public void setData(ChatDataAction data) {
        this.data = data;
    }
}

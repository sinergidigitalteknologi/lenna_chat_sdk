package ai.lenna.lennachatmodul.chat.model.column;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ai.lenna.lennachatmodul.chat.model.action.DefaultAction;

public class ChatColumnGrid implements Serializable {
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("subText")
    @Expose
    private String subText;
    @SerializedName("defaultAction")
    @Expose
    private DefaultAction defaultAction;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public DefaultAction getDefaultAction() {
        return defaultAction;
    }

    public void setDefaultAction(DefaultAction defaultAction) {
        this.defaultAction = defaultAction;
    }

}

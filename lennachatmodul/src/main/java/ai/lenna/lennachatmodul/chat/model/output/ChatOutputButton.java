package ai.lenna.lennachatmodul.chat.model.output;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import ai.lenna.lennachatmodul.chat.model.output.action.ChatActionButton;

public class ChatOutputButton implements Serializable {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("actions")
    @Expose
    private ArrayList<ChatActionButton> actions = null;

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

    public ArrayList<ChatActionButton> getActions() {
        return actions;
    }

    public void setActions(ArrayList<ChatActionButton> actions) {
        this.actions = actions;
    }
}

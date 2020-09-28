package ai.lenna.lennachatmodul.chat.model.column;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import ai.lenna.lennachatmodul.chat.model.action.ChatMovieAction;
import ai.lenna.lennachatmodul.chat.model.action.ChatMovieDefaultAction;


public class ChatColumnMovie implements Serializable {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("thumbnailImageUrl")
    @Expose
    private String thumbnailImageUrl;
    @SerializedName("defaultAction")
    @Expose
    private ChatMovieDefaultAction defaultAction;
    @SerializedName("actions")
    @Expose
    private ArrayList<ChatMovieAction> actions = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public ChatMovieDefaultAction getDefaultAction() {
        return defaultAction;
    }

    public void setDefaultAction(ChatMovieDefaultAction defaultAction) {
        this.defaultAction = defaultAction;
    }

    public ArrayList<ChatMovieAction> getActions() {
        return actions;
    }

    public void setActions(ArrayList<ChatMovieAction> actions) {
        this.actions = actions;
    }
}

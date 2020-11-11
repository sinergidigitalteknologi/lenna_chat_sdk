package ai.lenna.lennachatmodul.chat.model.column;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import ai.lenna.lennachatmodul.chat.model.carousel.ChatActionCarousel;
import ai.lenna.lennachatmodul.chat.model.carousel.ChatDefaultActionCarousel;

@Keep
public class ChatColumnCarousel implements Serializable {
    @SerializedName("thumbnailImageUrl")
    @Expose
    private String thumbnailImageUrl;
    @SerializedName("imageBackgroundColor")
    @Expose
    private String imageBackgroundColor;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("defaultAction")
    @Expose
    private ChatDefaultActionCarousel defaultAction;
    @SerializedName("actions")
    @Expose
    private List<ChatActionCarousel> actions = null;

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public String getImageBackgroundColor() {
        return imageBackgroundColor;
    }

    public void setImageBackgroundColor(String imageBackgroundColor) {
        this.imageBackgroundColor = imageBackgroundColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ChatDefaultActionCarousel getDefaultAction() {
        return defaultAction;
    }

    public void setDefaultAction(ChatDefaultActionCarousel defaultAction) {
        this.defaultAction = defaultAction;
    }

    public List<ChatActionCarousel> getActions() {
        return actions;
    }

    public void setActions(List<ChatActionCarousel> actions) {
        this.actions = actions;
    }
}

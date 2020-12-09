package ai.lenna.lennachatmodul.chat.model.output;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import ai.lenna.lennachatmodul.chat.model.carousel.ChatActionCarousel;
import ai.lenna.lennachatmodul.chat.model.carousel.ChatDefaultActionCarousel;

@Keep
public class ChatColumnCarouselApi {
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

    //    @SerializedName("defaultAction")
//    @Expose
//    private ArrayList<ChatDefaultActionCarousel> defaultAction;
    @SerializedName("defaultAction")
    @Expose
    private List<ChatDefaultActionCarousel> defaultAction;
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

//    public ArrayList<ChatDefaultActionCarousel> getDefaultAction() {
//        return defaultAction;
//    }
//
//    public void setDefaultAction(ArrayList<ChatDefaultActionCarousel> defaultAction) {
//        this.defaultAction = defaultAction;
//    }

    public String getTitle() {
        return title;
    }

    public List<ChatDefaultActionCarousel> getDefaultAction() {
        return defaultAction;
    }

    public void setDefaultAction(List<ChatDefaultActionCarousel> defaultAction) {
        this.defaultAction = defaultAction;
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

    public List<ChatActionCarousel> getActions() {
        return actions;
    }

    public void setActions(List<ChatActionCarousel> actions) {
        this.actions = actions;
    }
}

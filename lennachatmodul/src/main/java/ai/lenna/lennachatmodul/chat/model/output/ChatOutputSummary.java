package ai.lenna.lennachatmodul.chat.model.output;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import ai.lenna.lennachatmodul.chat.model.column.ChatColumnSummary;

@Keep
public class ChatOutputSummary implements Serializable {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("columns")
    @Expose
    private ArrayList<ChatColumnSummary> columns = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<ChatColumnSummary> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<ChatColumnSummary> columns) {
        this.columns = columns;
    }
}

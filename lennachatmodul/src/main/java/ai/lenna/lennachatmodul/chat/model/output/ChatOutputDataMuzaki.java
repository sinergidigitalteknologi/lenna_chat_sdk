package ai.lenna.lennachatmodul.chat.model.output;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ai.lenna.lennachatmodul.chat.model.column.ChatColumnDataMuzaki;


public class ChatOutputDataMuzaki {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("columns")
    @Expose
    private List<ChatColumnDataMuzaki> columns = null;

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

    public List<ChatColumnDataMuzaki> getColumns() {
        return columns;
    }

    public void setColumns(List<ChatColumnDataMuzaki> columns) {
        this.columns = columns;
    }
}

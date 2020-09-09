package ai.lenna.lennachatmodul.chat.model.output;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import ai.lenna.lennachatmodul.chat.model.column.ChatColumnDonasi;

public class ChatOutputDonasi implements Serializable {


    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("columns")
    @Expose
    private List<ChatColumnDonasi> columns = null;

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

    public List<ChatColumnDonasi> getColumns() {
        return columns;
    }

    public void setColumns(List<ChatColumnDonasi> columns) {
        this.columns = columns;
    }

}

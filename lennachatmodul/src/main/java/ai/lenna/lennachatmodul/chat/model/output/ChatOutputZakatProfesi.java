package ai.lenna.lennachatmodul.chat.model.output;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import ai.lenna.lennachatmodul.chat.model.column.ChatColumnZakatProfesi;

@Keep
public class ChatOutputZakatProfesi implements Serializable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("columns")
    @Expose
    private List<ChatColumnZakatProfesi> columns = null;

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

    public List<ChatColumnZakatProfesi> getColumns() {
        return columns;
    }

    public void setColumns(List<ChatColumnZakatProfesi> columns) {
        this.columns = columns;
    }

}

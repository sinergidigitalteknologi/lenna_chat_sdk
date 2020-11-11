package ai.lenna.lennachatmodul.chat.model.output;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ai.lenna.lennachatmodul.chat.model.column.ChatColumnTrainPassengerForm;

@Keep
public class ChatOutputTrainPassengerForm implements Serializable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("columns")
    @Expose
    private ChatColumnTrainPassengerForm columns;

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

    public ChatColumnTrainPassengerForm getColumns() {
        return columns;
    }

    public void setColumns(ChatColumnTrainPassengerForm columns) {
        this.columns = columns;
    }
}

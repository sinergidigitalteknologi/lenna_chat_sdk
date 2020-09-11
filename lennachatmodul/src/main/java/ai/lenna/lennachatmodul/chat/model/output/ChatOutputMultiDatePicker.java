package ai.lenna.lennachatmodul.chat.model.output;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChatOutputMultiDatePicker implements Serializable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("currentValue")
    @Expose
    private MultipleDateCurrentValue currentValue;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MultipleDateCurrentValue getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(MultipleDateCurrentValue currentValue) {
        this.currentValue = currentValue;
    }
}

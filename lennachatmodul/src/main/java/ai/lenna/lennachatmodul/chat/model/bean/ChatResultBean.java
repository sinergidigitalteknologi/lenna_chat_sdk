package ai.lenna.lennachatmodul.chat.model.bean;

import androidx.annotation.Keep;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

@Keep
public class ChatResultBean implements Serializable {
    @SerializedName("output")
    @Expose
    private ArrayList<JsonObject> output = null;
    @SerializedName("intents")
    @Expose
    private String intents;
    @SerializedName("intentsid")
    @Expose
    private String intentsid;
    @SerializedName("quickbutton")
    @Expose
    private ArrayList<String> quickbutton = null;

    public ArrayList<JsonObject> getOutput() {
        return output;
    }

    public void setOutput(ArrayList<JsonObject> output) {
        this.output = output;
    }

    public String getIntents() {
        return intents;
    }

    public void setIntents(String intents) {
        this.intents = intents;
    }

    public String getIntentsid() {
        return intentsid;
    }

    public void setIntentsid(String intentsid) {
        this.intentsid = intentsid;
    }

    public ArrayList<String> getQuickbutton() {
        return quickbutton;
    }

    public void setQuickbutton(ArrayList<String> quickbutton) {
        this.quickbutton = quickbutton;
    }
}

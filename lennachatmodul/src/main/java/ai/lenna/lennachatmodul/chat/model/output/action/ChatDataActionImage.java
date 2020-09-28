package ai.lenna.lennachatmodul.chat.model.output.action;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChatDataActionImage implements Serializable {

    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("packageName")
    @Expose
    private String packageName;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}

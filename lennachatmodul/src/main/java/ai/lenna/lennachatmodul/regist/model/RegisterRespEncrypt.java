package ai.lenna.lennachatmodul.regist.model;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
public class RegisterRespEncrypt {
    @SerializedName("data")
    @Expose
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

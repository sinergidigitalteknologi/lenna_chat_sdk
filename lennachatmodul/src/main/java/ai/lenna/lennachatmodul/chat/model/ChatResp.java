package ai.lenna.lennachatmodul.chat.model;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ai.lenna.lennachatmodul.chat.model.bean.ChatResultBean;

@Keep
public class ChatResp implements Serializable {

    @SerializedName("resolvequery")
    @Expose
    private String resolvequery;
    @SerializedName("result")
    @Expose
    private ChatResultBean result;
    @SerializedName("statuscode")
    @Expose
    private Integer statuscode;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("user_type")
    @Expose
    private String usertype;

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    @Keep
    public String getResolvequery() {
        return resolvequery;
    }

    @Keep
    public void setResolvequery(String resolvequery) {
        this.resolvequery = resolvequery;
    }

    @Keep
    public ChatResultBean getResult() {
        return result;
    }

    @Keep
    public void setResult(ChatResultBean result) {
        this.result = result;
    }

    @Keep
    public Integer getStatuscode() {
        return statuscode;
    }

    @Keep
    public void setStatuscode(Integer statuscode) {
        this.statuscode = statuscode;
    }

    @Keep
    public String getDate() {
        return date;
    }

    @Keep
    public void setDate(String date) {
        this.date = date;
    }

    @Keep
    public String getTime() {
        return time;
    }

    @Keep
    public void setTime(String time) {
        this.time = time;
    }

}

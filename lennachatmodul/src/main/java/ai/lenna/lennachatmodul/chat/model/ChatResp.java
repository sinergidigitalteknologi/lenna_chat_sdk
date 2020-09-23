package ai.lenna.lennachatmodul.chat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ai.lenna.lennachatmodul.chat.model.bean.ChatResultBean;


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

    public String getResolvequery() {
        return resolvequery;
    }

    public void setResolvequery(String resolvequery) {
        this.resolvequery = resolvequery;
    }

    public ChatResultBean getResult() {
        return result;
    }

    public void setResult(ChatResultBean result) {
        this.result = result;
    }

    public Integer getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(Integer statuscode) {
        this.statuscode = statuscode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}

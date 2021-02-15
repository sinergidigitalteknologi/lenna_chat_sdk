package ai.lenna.lennachatmodul.chat.model;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Keep
public class ChatReq implements Serializable {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("type")
    @Expose
    private String type;

    public ChatReq(String userId, String query, String lat, String lon, String channel, String type) {
        this.userId = userId;
        this.query = query;
        this.lat = lat;
        this.lon = lon;
        this.channel = channel;
        this.type = type;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

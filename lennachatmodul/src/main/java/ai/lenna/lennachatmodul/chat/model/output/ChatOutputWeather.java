package ai.lenna.lennachatmodul.chat.model.output;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import ai.lenna.lennachatmodul.chat.model.column.ChatColumnWeather;

@Keep
public class ChatOutputWeather implements Serializable {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("columns")
    @Expose
    private ArrayList<ChatColumnWeather> columns = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public ArrayList<ChatColumnWeather> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<ChatColumnWeather> columns) {
        this.columns = columns;
    }
}

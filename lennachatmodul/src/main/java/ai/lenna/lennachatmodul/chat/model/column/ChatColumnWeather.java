package ai.lenna.lennachatmodul.chat.model.column;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Keep
public class ChatColumnWeather implements Serializable {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("longDate")
    @Expose
    private String longDate;
    @SerializedName("shortDate")
    @Expose
    private String shortDate;
    @SerializedName("dayWeather")
    @Expose
    private String dayWeather;
    @SerializedName("dayIconUrl")
    @Expose
    private String dayIconUrl;
    @SerializedName("nightWeather")
    @Expose
    private String nightWeather;
    @SerializedName("nightIconUrl")
    @Expose
    private String nightIconUrl;
    @SerializedName("temperature")
    @Expose
    private String temperature;
    @SerializedName("minTemperature")
    @Expose
    private String minTemperature;
    @SerializedName("maxTemperature")
    @Expose
    private String maxTemperature;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLongDate() {
        return longDate;
    }

    public void setLongDate(String longDate) {
        this.longDate = longDate;
    }

    public String getShortDate() {
        return shortDate;
    }

    public void setShortDate(String shortDate) {
        this.shortDate = shortDate;
    }

    public String getDayWeather() {
        return dayWeather;
    }

    public void setDayWeather(String dayWeather) {
        this.dayWeather = dayWeather;
    }

    public String getDayIconUrl() {
        return dayIconUrl;
    }

    public void setDayIconUrl(String dayIconUrl) {
        this.dayIconUrl = dayIconUrl;
    }

    public String getNightWeather() {
        return nightWeather;
    }

    public void setNightWeather(String nightWeather) {
        this.nightWeather = nightWeather;
    }

    public String getNightIconUrl() {
        return nightIconUrl;
    }

    public void setNightIconUrl(String nightIconUrl) {
        this.nightIconUrl = nightIconUrl;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

}

package ai.lenna.lennachatmodul.chat.model.column;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChatColumnAirline implements Serializable {

    @SerializedName("airline")
    @Expose
    private String airline;
    @SerializedName("airlineIconUrl")
    @Expose
    private String airlineIconUrl;
    @SerializedName("flightNumber")
    @Expose
    private String flightNumber;
    @SerializedName("flightType")
    @Expose
    private String flightType;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("originAirport")
    @Expose
    private String originAirport;
    @SerializedName("originCity")
    @Expose
    private String originCity;
    @SerializedName("originCountry")
    @Expose
    private String originCountry;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("destinationAirport")
    @Expose
    private String destinationAirport;
    @SerializedName("destinationCity")
    @Expose
    private String destinationCity;
    @SerializedName("destinationCountry")
    @Expose
    private String destinationCountry;
    @SerializedName("departTime")
    @Expose
    private String departTime;
    @SerializedName("arriveTime")
    @Expose
    private String arriveTime;
    @SerializedName("scheduleMainId")
    @Expose
    private String scheduleMainId;
    @SerializedName("itemId")
    @Expose
    private String itemId;
    @SerializedName("classGroupId")
    @Expose
    private String classGroupId;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("shortDepartTime")
    @Expose
    private String shortDepartTime;
    @SerializedName("shortArriveTime")
    @Expose
    private String shortArriveTime;
    @SerializedName("shortDepartDate")
    @Expose
    private String shortDepartDate;
    @SerializedName("shortArriveDate")
    @Expose
    private String shortArriveDate;
    @SerializedName("rawPrice")
    @Expose
    private String rawPrice;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("price")
    @Expose
    private String price;

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getAirlineIconUrl() {
        return airlineIconUrl;
    }

    public void setAirlineIconUrl(String airlineIconUrl) {
        this.airlineIconUrl = airlineIconUrl;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getScheduleMainId() {
        return scheduleMainId;
    }

    public void setScheduleMainId(String scheduleMainId) {
        this.scheduleMainId = scheduleMainId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getClassGroupId() {
        return classGroupId;
    }

    public void setClassGroupId(String classGroupId) {
        this.classGroupId = classGroupId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortDepartTime() {
        return shortDepartTime;
    }

    public void setShortDepartTime(String shortDepartTime) {
        this.shortDepartTime = shortDepartTime;
    }

    public String getShortArriveTime() {
        return shortArriveTime;
    }

    public void setShortArriveTime(String shortArriveTime) {
        this.shortArriveTime = shortArriveTime;
    }

    public String getShortDepartDate() {
        return shortDepartDate;
    }

    public void setShortDepartDate(String shortDepartDate) {
        this.shortDepartDate = shortDepartDate;
    }

    public String getShortArriveDate() {
        return shortArriveDate;
    }

    public void setShortArriveDate(String shortArriveDate) {
        this.shortArriveDate = shortArriveDate;
    }

    public String getRawPrice() {
        return rawPrice;
    }

    public void setRawPrice(String rawPrice) {
        this.rawPrice = rawPrice;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}

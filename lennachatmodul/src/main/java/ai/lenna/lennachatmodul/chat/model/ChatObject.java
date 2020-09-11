package ai.lenna.lennachatmodul.chat.model;

import androidx.annotation.NonNull;


import java.util.ArrayList;

import ai.lenna.lennachatmodul.chat.model.column.ChatColumnAirline;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnCarousel;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnGrid;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnMovie;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnNews;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnSummary;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnWeather;
import ai.lenna.lennachatmodul.chat.model.output.action.ChatActionButton;
import ai.lenna.lennachatmodul.chat.model.output.action.ChatOutputAction;

public abstract class ChatObject {
    public static final int INPUT_OBJECT = 0;
    public static final int RESPONSE_OBJECT = 1;
    public static final int RESPONSE_LOADING_OBJECT = 2;
    public static final int RESPONSE_CAROUSEL = 3;
    public static final int RESPONSE_IMAGE = 4;
    public static final int RESPONSE_SUMMARY = 5;
    public static final int RESPONSE_MOVIE = 6;
    public static final int RESPONSE_GRID = 7;
    public static final int RESPONSE_CONFIRM = 8;
    public static final int RESPONSE_NEWS = 9;
    public static final int RESPONSE_TRAVEL = 10;
    public static final int RESPONSE_WEATHER = 11;
    public static final int RESPONSE_HTML = 12;
    public static final int RESPONSE_LIST = 13;
    public static final int RESPONSE_BUTTON = 14;
    public static final int RESPONSE_ACTION = 15;


    private String text;
    private String time;
    private String date;

    //response image
    private String image_original_url;
    private String image_preview_url;

    //response action
    private String imageAction;
    private String textAction;
    private String packageAction;
    private String subTypeAction;
    private ChatOutputAction chatOutputAction;


    //response grid
    private String imageUrlGrid;
    private String subTitleGrid;
    private String titleGrid;
    private ArrayList<ChatColumnGrid> chatColumnGrids;

    //confirm
    private String textTitleConfirm;

    //button
    private String textTitleButton;
    private ArrayList<ChatActionButton> chatActionButtons;
    //summary
    private String type;
    private String titleSummary;
    private String imageUrl;
    private ArrayList<ChatColumnSummary> chatColumnSummaries;

    //weather
    private ArrayList<ChatColumnWeather> chatColumnWeathers;
    private String area;
    private String country;
    private String countryCode;

    //html
    private String html;

    private int chatType;

    private ArrayList<ChatColumnCarousel> chatColumnCarousels;

    private ArrayList<ChatColumnMovie> chatColumnMovies;

    private ArrayList<ChatColumnNews> chatColumnNews;

    private ArrayList<ChatColumnAirline> chatColumnAirlines;

    public ArrayList<ChatActionButton> getChatActionButtons() {
        return chatActionButtons;
    }

    public void setChatActionButtons(ArrayList<ChatActionButton> chatActionButtons) {
        this.chatActionButtons = chatActionButtons;
    }

    public ArrayList<ChatColumnAirline> getChatColumnAirlines() {
        return chatColumnAirlines;
    }

    public void setChatColumnAirlines(ArrayList<ChatColumnAirline> chatColumnAirlines) {
        this.chatColumnAirlines = chatColumnAirlines;
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

    public ArrayList<ChatColumnNews> getChatColumnNews() {
        return chatColumnNews;
    }

    public void setChatColumnNews(ArrayList<ChatColumnNews> chatColumnNews) {
        this.chatColumnNews = chatColumnNews;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitleSummary() {
        return titleSummary;
    }

    public void setTitleSummary(String titleSummary) {
        this.titleSummary = titleSummary;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<ChatColumnSummary> getChatColumnSummaries() {
        return chatColumnSummaries;
    }

    public void setChatColumnSummaries(ArrayList<ChatColumnSummary> chatColumnSummaries) {
        this.chatColumnSummaries = chatColumnSummaries;
    }

    public ArrayList<ChatColumnMovie> getChatColumnMovies() {
        return chatColumnMovies;
    }

    public void setChatColumnMovies(ArrayList<ChatColumnMovie> chatColumnMovies) {
        this.chatColumnMovies = chatColumnMovies;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTextTitleConfirm() {
        return textTitleConfirm;
    }

    public void setTextTitleConfirm(String textTitleConfirm) {
        this.textTitleConfirm = textTitleConfirm;
    }

    public int getChatType() {
        return chatType;
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }

    public String getImage_original_url() {
        return image_original_url;
    }

    public void setImage_original_url(String image_original_url) {
        this.image_original_url = image_original_url;
    }

    public String getImage_preview_url() {
        return image_preview_url;
    }

    public void setImage_preview_url(String image_preview_url) {
        this.image_preview_url = image_preview_url;
    }

    @NonNull
    public String getText() {
        return text;
    }

    public void setText(@NonNull String text) {
        this.text = text;
    }

    public abstract int getType();

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<ChatColumnCarousel> getChatColumnCarousels() {
        return chatColumnCarousels;
    }

    public void setChatColumnCarousels(ArrayList<ChatColumnCarousel> chatColumnCarousels) {
        this.chatColumnCarousels = chatColumnCarousels;
    }

    public String getImageUrlGrid() {
        return imageUrlGrid;
    }

    public void setImageUrlGrid(String imageUrlGrid) {
        this.imageUrlGrid = imageUrlGrid;
    }

    public String getSubTitleGrid() {
        return subTitleGrid;
    }

    public void setSubTitleGrid(String subTitleGrid) {
        this.subTitleGrid = subTitleGrid;
    }

    public String getTitleGrid() {
        return titleGrid;
    }

    public void setTitleGrid(String titleGrid) {
        this.titleGrid = titleGrid;
    }

    public ArrayList<ChatColumnGrid> getChatColumnGrids() {
        return chatColumnGrids;
    }

    public void setChatColumnGrids(ArrayList<ChatColumnGrid> chatColumnGrids) {
        this.chatColumnGrids = chatColumnGrids;
    }

    public ArrayList<ChatColumnWeather> getChatColumnWeathers() {
        return chatColumnWeathers;
    }

    public void setChatColumnWeathers(ArrayList<ChatColumnWeather> chatColumnWeathers) {
        this.chatColumnWeathers = chatColumnWeathers;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getTextTitleButton() {
        return textTitleButton;
    }

    public void setTextTitleButton(String textTitleButton) {
        this.textTitleButton = textTitleButton;
    }

    public String getImageAction() {
        return imageAction;
    }

    public void setImageAction(String imageAction) {
        this.imageAction = imageAction;
    }

    public String getTextAction() {
        return textAction;
    }

    public void setTextAction(String textAction) {
        this.textAction = textAction;
    }

    public String getPackageAction() {
        return packageAction;
    }

    public void setPackageAction(String packageAction) {
        this.packageAction = packageAction;
    }
    public String getSubTypeAction() {
        return subTypeAction;
    }

    public void setSubTypeAction(String subTypeAction) {
        this.subTypeAction = subTypeAction;
    }

    public ChatOutputAction getChatOutputAction() {
        return chatOutputAction;
    }

    public void setChatOutputAction(ChatOutputAction chatOutputAction) {
        this.chatOutputAction = chatOutputAction;
    }
}

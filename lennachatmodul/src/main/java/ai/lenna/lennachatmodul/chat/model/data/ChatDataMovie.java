package ai.lenna.lennachatmodul.chat.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatDataMovie implements Serializable {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("thumbnailUrl")
    @Expose
    private String thumbnailUrl;
    @SerializedName("trailerUrl")
    @Expose
    private String trailerUrl;
    @SerializedName("synopsis")
    @Expose
    private String synopsis;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("ageIconUrl")
    @Expose
    private String ageIconUrl;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("categoryIconUrl")
    @Expose
    private String categoryIconUrl;
    @SerializedName("producer")
    @Expose
    private String producer;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("writer")
    @Expose
    private String writer;
    @SerializedName("production")
    @Expose
    private String production;
    @SerializedName("stars")
    @Expose
    private String stars;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("ratingIconUrl")
    @Expose
    private String ratingIconUrl;
    @SerializedName("showTimes")
    @Expose
    private ArrayList<String> showTimes = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAgeIconUrl() {
        return ageIconUrl;
    }

    public void setAgeIconUrl(String ageIconUrl) {
        this.ageIconUrl = ageIconUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryIconUrl() {
        return categoryIconUrl;
    }

    public void setCategoryIconUrl(String categoryIconUrl) {
        this.categoryIconUrl = categoryIconUrl;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRatingIconUrl() {
        return ratingIconUrl;
    }

    public void setRatingIconUrl(String ratingIconUrl) {
        this.ratingIconUrl = ratingIconUrl;
    }

    public ArrayList<String> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(ArrayList<String> showTimes) {
        this.showTimes = showTimes;
    }
}

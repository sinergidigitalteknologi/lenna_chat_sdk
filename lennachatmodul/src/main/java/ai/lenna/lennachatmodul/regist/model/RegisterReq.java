package ai.lenna.lennachatmodul.regist.model;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Keep
public class RegisterReq {
    @SerializedName("app_id")
    @Expose
    private String appId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("client")
    @Expose
    private String client;

    @SerializedName("fcm_token")
    @Expose
    private String fcm_token;

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    @SerializedName("interests")
    @Expose
    private List<String> interests = null;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" + "\"name\":" + "\""+ name +"\"," + "\"nickname\":" + "\""+ name +"\"," + "\"email\":" + "\""+ email +"\"," + "\"phone\":" + "\""+ phone +"\"," +
                "\"password\":" + "\""+ password +"\"," + "\"interests\": [\"Keuangan\", \"Politik\"],\"fcm_token\":\"token\"}";
    }

}

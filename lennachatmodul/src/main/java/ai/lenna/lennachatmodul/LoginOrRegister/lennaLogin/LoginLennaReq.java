package ai.lenna.lennachatmodul.LoginOrRegister.lennaLogin;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Keep
public class LoginLennaReq {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("fcm_token")
    @Expose
    private String fcmToken;
    @SerializedName("client")
    @Expose
    private String client;
    @SerializedName("sales_force_id")
    @Expose
    private String sales_force_id;

    public String getSales_force_id() {
        return sales_force_id;
    }

    public void setSales_force_id(String sales_force_id) {
        this.sales_force_id = sales_force_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
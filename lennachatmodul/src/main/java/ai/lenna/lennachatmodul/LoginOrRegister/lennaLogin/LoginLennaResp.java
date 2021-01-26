package ai.lenna.lennachatmodul.LoginOrRegister.lennaLogin;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Keep
public class LoginLennaResp {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private LoginLennaDataBean data;
    @SerializedName("error")
    @Expose
    private LoginLennaErrorBean error;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("expires_at")
    @Expose
    private String expiresAt;
    @SerializedName("sales_force_id")
    @Expose
    private String sales_force_id;

    public String getSales_force_id() {
        return sales_force_id;
    }

    public void setSales_force_id(String sales_force_id) {
        this.sales_force_id = sales_force_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LoginLennaDataBean getData() {
        return data;
    }

    public void setData(LoginLennaDataBean data) {
        this.data = data;
    }

    public LoginLennaErrorBean getError() {
        return error;
    }

    public void setError(LoginLennaErrorBean error) {
        this.error = error;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }
}

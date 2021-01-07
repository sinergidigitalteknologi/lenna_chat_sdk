package ai.lenna.lennachatmodul.chatRoom.model;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Keep
public class RoomResolveBean  implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("handle_by")
    @Expose
    private Object handle_by;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("app_id")
    @Expose
    private int app_id;

    @SerializedName("sales_force_id")
    @Expose
    private int sales_force_id;

    @SerializedName("created_by")
    @Expose
    private int created_by;

    @SerializedName("livechat")
    @Expose
    private String livechat;

    @SerializedName("agent_info")
    @Expose
    private String agent_info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getHandle_by() {
        return handle_by;
    }

    public void setHandle_by(Object handle_by) {
        this.handle_by = handle_by;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public int getSales_force_id() {
        return sales_force_id;
    }

    public void setSales_force_id(int sales_force_id) {
        this.sales_force_id = sales_force_id;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public String getLivechat() {
        return livechat;
    }

    public void setLivechat(String livechat) {
        this.livechat = livechat;
    }

    public String getAgent_info() {
        return agent_info;
    }

    public void setAgent_info(String agent_info) {
        this.agent_info = agent_info;
    }
}

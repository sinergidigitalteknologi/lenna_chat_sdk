package ai.lenna.lennachatmodul.chat.modelzakat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ZakatProfesi implements Serializable {


    @SerializedName("identity_number")
    @Expose
    private String nik;
    @SerializedName("name")
    @Expose
    private String namaprofesi;
    @SerializedName("email")
    @Expose
    private String emailprofesi;
    @SerializedName("income")
    @Expose
    private String penghasilan;


    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNamaprofesi() {
        return namaprofesi;
    }

    public void setNamaprofesi(String namaprofesi) {
        this.namaprofesi = namaprofesi;
    }

    public String getEmailprofesi() {
        return emailprofesi;
    }

    public void setEmailprofesi(String emailprofesi) {
        this.emailprofesi = emailprofesi;
    }

    public String getPenghasilan() {
        return penghasilan;
    }

    public void setPenghasilan(String penghasilan) {
        this.penghasilan = penghasilan;
    }


}

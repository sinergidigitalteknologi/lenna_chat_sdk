package ai.lenna.lennachatmodul.chat.modelzakat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ZakatFitrahAnak2 implements Serializable {

    @SerializedName("identity_number")
    @Expose
    private String noktp;
    @SerializedName("nama_kepala")
    @Expose
    private String namakepala;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("anak1")
    @Expose
    private String anak1;
    @SerializedName("anak2")
    @Expose
    private String anak2;


    public String getNoktp() {
        return noktp;
    }

    public void setNoktp(String noktp) {
        this.noktp = noktp;
    }

    public String getNamakepala() {
        return namakepala;
    }

    public void setNamakepala(String namakepala) {
        this.namakepala = namakepala;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAnak1() {
        return anak1;
    }

    public void setAnak1(String anak1) {
        this.anak1 = anak1;
    }

    public String getAnak2() {
        return anak2;
    }

    public void setAnak2(String anak2) {
        this.anak2 = anak2;
    }


}

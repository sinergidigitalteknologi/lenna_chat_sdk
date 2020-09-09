package ai.lenna.lennachatmodul.chat.modelzakat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ZakatMaalEmas implements Serializable {


    @SerializedName("identity_number")
    @Expose
    private String nik;
    @SerializedName("name")
    @Expose
    private String nama;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gold_weight")
    @Expose
    private String simpananemas;
    @SerializedName("savings")
    @Expose
    private String uangsimpan;



    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSimpananemas() {
        return simpananemas;
    }

    public void setSimpananemas(String simpananemas) {
        this.simpananemas = simpananemas;
    }

    public String getUangsimpan() {
        return uangsimpan;
    }

    public void setUangsimpan(String uangsimpan) {
        this.uangsimpan = uangsimpan;
    }


}

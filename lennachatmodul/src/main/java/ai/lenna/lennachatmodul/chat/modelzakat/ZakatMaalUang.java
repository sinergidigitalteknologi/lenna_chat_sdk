package ai.lenna.lennachatmodul.chat.modelzakat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ZakatMaalUang implements Serializable {

    @SerializedName("identity_number")
    @Expose
    private String nik;
    @SerializedName("name")
    @Expose
    private String nama;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("savings")
    @Expose
    private String simpananuang;
    @SerializedName("gold_weight")
    @Expose
    private String emassimpan;



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

    public String getSimpananuang() {
        return simpananuang;
    }

    public void setSimpananuang(String simpananuang) {
        this.simpananuang = simpananuang;
    }

    public String getEmassimpan() {
        return emassimpan;
    }

    public void setEmassimpan(String emassimpan) {
        this.emassimpan = emassimpan;
    }


}

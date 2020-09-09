package ai.lenna.lennachatmodul.chat.modelzakat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ZakatFitrahKepala implements Serializable {

    @SerializedName("identity_number")
    @Expose
    private String noktp;
    @SerializedName("nama_kepala")
    @Expose
    private String namakepala;
    @SerializedName("email")
    @Expose
    private String email;


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



}

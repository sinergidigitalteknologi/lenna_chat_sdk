package ai.lenna.lennachatmodul.util;

import android.util.Log;

import androidx.annotation.Keep;

import org.json.JSONObject;

@Keep
public class Encryp {

    @Keep
    public  void dc(){
        try {
            String data = AesCipher.decrypt("lennachatsdk", Constant.APP_KEY_);
            JSONObject json = new JSONObject(data);
            Constant.APP_KEY = AesCipher.decrypt("lennachatsdk",json.get("appkey").toString());
            Constant.REG_KEY = AesCipher.decrypt("lennachatsdk",json.get("regkey").toString());
            Constant.SECRET_KEY = AesCipher.decrypt("lennachatsdk",json.get("seckey").toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

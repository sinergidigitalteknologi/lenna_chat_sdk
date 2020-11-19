package ai.lenna.lennachatmodul.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import androidx.annotation.Keep;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.UUID;

import ai.lenna.lennachatmodul.regist.model.RegisterReq;

import static ai.lenna.lennachatmodul.util.RequestToken.registerReq;

@Keep
public class GenerateUserID {

    public static void generate(Context context){
        new Prefs.Builder()
                .setContext(context)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(context.getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
        Prefs.putInt("LOGO_TITLE",Constant.LOGO_TITLE);
        Prefs.putInt("ICON_BUBLE_CHAT",Constant.ICON_BUBLE_CHAT);
        Encryp encryp  = new Encryp();
        encryp.dc();
        String password = "" ;
        if(Prefs.getString("id_user","").equals("")){
            String uniqueID = UUID.randomUUID().toString();
            Prefs.putString("id_user",uniqueID);
        }
        String ui = Prefs.getString("id_user","");
        registerReq = new RegisterReq();
        registerReq.setName(Constant.USER_NAME);
        registerReq.setNickname(Constant.USER_NAME);
        registerReq.setEmail(ui + "@"+ Constant.APP_ID +".com");
        registerReq.setPhone(Constant.PHONE);
        registerReq.setClient("android");
        registerReq.setFcm_token(Constant.FCM_TOKEN);
        ArrayList<String> array_item = new ArrayList<>();
        array_item.add("goers");
        registerReq.setInterests(array_item);
        try {
            password = AesCipher.encrypt(Constant.SECRET_KEY, Constant.PASSWORD);
            registerReq.setPassword(password);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

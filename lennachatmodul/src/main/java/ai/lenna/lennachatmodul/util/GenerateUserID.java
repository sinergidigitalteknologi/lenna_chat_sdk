package ai.lenna.lennachatmodul.util;

import android.content.Context;
import android.content.ContextWrapper;

import androidx.annotation.Keep;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.LoginOrRegister.regist.model.RegisterLennaReq;

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


//        if(Prefs.getString("id_user","").equals("")){
//            Calendar calendar = Calendar.getInstance();
//            long startTime = calendar.getTimeInMillis();
//            String uniqueID = String.valueOf(startTime);
////            String uniqueID = UUID.randomUUID().toString();
//            Prefs.putString("id_user",uniqueID);
//        }
//        String ui = Prefs.getString("id_user","");
        registerReq = new RegisterLennaReq();
        registerReq.setName(Constant.USER_NAME);
        registerReq.setSales_force_id(Constant.SALEFORCEID);
        registerReq.setNickname(Constant.USER_NAME);
        registerReq.setEmail(Constant.EMAIL);
        registerReq.setPhone(Constant.PHONE);
        registerReq.setClient("android");
        registerReq.setFcm_token(Constant.FCM_TOKEN);
        ArrayList<String> array_item = new ArrayList<>();
        array_item.add("lenna");
        registerReq.setInterests(array_item);
        try {
            password = AesCipher.encrypt(Constant.SECRET_KEY, Constant.EMAIL);
            registerReq.setPassword(password);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

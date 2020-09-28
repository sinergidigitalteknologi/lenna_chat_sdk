package ai.lenna.lennachatmodul.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.UUID;

import ai.lenna.lennachatmodul.regist.model.RegisterReq;

import static ai.lenna.lennachatmodul.util.RequestToken.registerReq;

public class GenerateUserID {

    public static void generate(Context context){
        new Prefs.Builder()
                .setContext(context)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(context.getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        String password = "" ;
        if(Prefs.getString("id_user","").equals("")){
            String uniqueID = UUID.randomUUID().toString();
            Prefs.putString("id_user",uniqueID);
            Log.d("Chat= ",uniqueID);
        }
        Log.d("Chat= ",Prefs.getString("id_user",""));
        registerReq = new RegisterReq();
        registerReq.setName(Constant.USER_NAME);
        registerReq.setNickname(Constant.USER_NAME);
        registerReq.setEmail(Prefs.getString("id_user","") + "@goersapp.com");
        registerReq.setPhone("123456789");
        registerReq.setClient("android");
        ArrayList<String> array_item = new ArrayList<>();
        array_item.add("goers");
        registerReq.setInterests(array_item);
        try {
            String scrt_keky = AesCipher.decrypt("lennachatsdk",Constant.SECRET_KEY);
            password = AesCipher.encrypt(scrt_keky, Prefs.getString("id_user",""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        registerReq.setPassword(password);

    }
}

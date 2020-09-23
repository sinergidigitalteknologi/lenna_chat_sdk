package ai.lenna.lennachatmodul.util;

import android.util.Log;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.UUID;

public class GenerateUserID {

    public static void generate(){
        if(Prefs.getString("id_user","").equals("")){
            String uniqueID = UUID.randomUUID().toString();
            Prefs.putString("id_user",uniqueID);
            Log.d("Chat= ",uniqueID);
        }
        Log.d("Chat= ",Prefs.getString("id_user",""));

    }
}

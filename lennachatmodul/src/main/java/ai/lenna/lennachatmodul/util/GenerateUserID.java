package ai.lenna.lennachatmodul.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.UUID;

public class GenerateUserID {

    public static void generate(Context context){
        new Prefs.Builder()
                .setContext(context)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(context.getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        if(Prefs.getString("id_user","").equals("")){
            String uniqueID = UUID.randomUUID().toString();
            Prefs.putString("id_user",uniqueID);
            Log.d("Chat= ",uniqueID);
        }
        Log.d("Chat= ",Prefs.getString("id_user",""));

    }
}

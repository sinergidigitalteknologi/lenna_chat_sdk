package ai.lenna.lennachatmodul.util;

import android.content.Context;
import android.content.ContextWrapper;

import androidx.annotation.Keep;

import com.pixplicity.easyprefs.library.Prefs;

@Keep
public class GenerateUserID {

    public static void generate(Context context){
        new Prefs.Builder()
                .setContext(context)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(context.getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
//        Prefs.putInt("LOGO_TITLE",Constant.LOGO_TITLE);
//        Prefs.putInt("ICON_BUBLE_CHAT",Constant.ICON_BUBLE_CHAT);
//        Prefs.putInt("ICON_BOT_LENNA", Constant.ICON_BOT_LENNA);
//        Prefs.putInt("ICON_AGENT_LENNA", Constant.ICON_AGENT_LENNA);

    }
}

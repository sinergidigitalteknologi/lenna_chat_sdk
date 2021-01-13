package ai.lenna.lennachatmodul;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.Keep;

import com.pixplicity.easyprefs.library.Prefs;

import ai.lenna.lennachatmodul.chat.ChatActivity;
import ai.lenna.lennachatmodul.util.Constant;
import ai.lenna.lennachatmodul.util.GenerateUserID;

@Keep
public class Chat {

    public static void start(Context context){
        GenerateUserID.generate(context);
    }

    public  static  void setTokenFcm(String token){
        Constant.FCM_TOKEN_LOGIN = token;
    }

    /**
     * set user ID **/
    public  static  void setUserId(String userId){
        Prefs.putString("USER_ID", userId);
        Constant.USER_ID = userId;
    }
    /**
     * set user Name **/

    public  static  void setSaleForceId(String salesId){
        Constant.SALEFORCEID = salesId;
    }

    public  static  void setUserName(String userName){
        Constant.USER_NAME = userName;
    }

    /**
     * set App ID **/
    public  static  void setAppId(String appId){
        Constant.APP_ID  = appId;
    }

    /**
     * set bot ID **/
    public  static  void setBotId(String botId){
        Constant.BOT_ID  = botId;
    }
    /**
     * set email **/
    public  static  void setEmail(String email){
        Constant.EMAIL = email;
    }

    /**
     * set token **/
    public  static  void setToken(String token){
        Prefs.putString("TOKEN_LOGIN",token);
        Constant.TOKEN_LOGIN = token;
    }

    public static void setIsChatLennaActive(boolean isActive) {
        Constant.IS_CHAT_LENNA_ACTIVE = isActive;
    }

    public  static  void removeTokenLogin() {
        Prefs.putString("TOKEN_LOGIN", "");
        Prefs.putString("USER_ID", "");
        Constant.USER_ID = "";
        Constant.TOKEN_LOGIN = "";
        Constant.FCM_TOKEN_LOGIN = "";
    }

    public  static  void setNameBot(String bot){
        Constant.NAME_BOT_MSG = bot;
    }

    public  static  void setNameAgent(String agent){
        Constant.NAME_AGENT_MSG = agent;
    }

    /**
     * set icon in header activity chat **/
    public  static  void setIcon(int icon){
        Constant.LOGO_TITLE = icon;
    }

    public  static  void setIconBitmap(Bitmap iconBBitmap){
        Constant.LOGO_TITLE_BITMAP = iconBBitmap;
    }

    public static void setIconAgent(int icon){
        Constant.ICON_AGENT_LENNA = icon;
    }

    public static void setIconAgentBitmap(Bitmap agentBitmap){
        Constant.ICON_AGENT_LENNA_BITMAP = agentBitmap;
    }

    public  static  void setIconBot(int icon){
        Constant.ICON_BOT_LENNA = icon;
    }

    public  static  void setIconBotBitmap(Bitmap botBitmap){
        Constant.ICON_BOT_LENNA_BITMAP = botBitmap;
    }


    /**
     * set greeting message **/
    public  static  void setGreetingMessage(String greetingMessage){
        Constant.GMESSAGE = greetingMessage;
    }

    /**
     * set icon in header activity chat **/
    public  static  void setIconBubleChat(int icon){
        Constant.ICON_BUBLE_CHAT = icon;
    }

    public  static  void setIconBubleChatBitmap(Bitmap iconBubleChatBitmap){
        Constant.ICON_BUBLE_CHAT_BITMAP = iconBubleChatBitmap;
    }

    /**
     * set pitch text to speech **/
    public  static  void setPitchTts(String value){
        Constant.TTS_PITCH = value;
    }

    /**
     * set pitch text to speech **/
    public  static  void setSpeechRateTts(String value){
        Constant.SPEECH_RATE = value;
    }

    /**
     * set key fallback **/
    public  static  void setKeyFallBack(String value){
        Constant.KEY_FALLBACK = value;
    }

    /**
     * set request menu fallback **/
    public  static  void setRequestMenuFAllback(String value){
        Constant.REQUEST_MENU_FALLBACK = value;
    }
    /**
     * set secret key **/
    public  static  void setSecretKey(String secretKey){
        Constant.SECRET_KEY = secretKey;
    }

    /**
     * set app key **/
    public  static  void setAppKey(String appKey){
        Constant.APP_KEY_ = appKey;
    }

}

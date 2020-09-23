package ai.lenna.lennachatmodul;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.UUID;

import ai.lenna.lennachatmodul.chat.ChatActivity;
import ai.lenna.lennachatmodul.network.ApiService;
import ai.lenna.lennachatmodul.util.Constant;
import ai.lenna.lennachatmodul.util.GenerateUserID;
import ai.lenna.lennachatmodul.util.RequestToken;

public class Chat {

    /**
     This is a funtion to move chat activity
     @author Viky Yahya
     **/
    public static void start(Context context){
        GenerateUserID.generate();
        RequestToken.request(context);

    }
    /**
     * set user ID **/
    public  static  void setUserId(String userId){
        Prefs.putString("USER_ID",userId);
        Constant.USER_ID = userId;
    }
    /**
     * set user Name **/
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
     * set token **/    public  static  void setToken(String token){
        Prefs.putString("TOKEN",token);
         Constant.TOKEN = token;
    }

    /**
     * set icon in header activity chat **/
    public  static  void setIcon(int icon){
        ChatActivity.LOGO_TITLE = icon;
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

}

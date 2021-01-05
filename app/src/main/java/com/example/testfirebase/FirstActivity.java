package com.example.testfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import ai.lenna.lennachatmodul.Chat;

public class FirstActivity extends AppCompatActivity {

    Button buttonBot, buttonAgent;

    String fcmToken = "";
//    public static Runnable r;
//    public static Handler handler;

    private static String TAG = "MyFirebaseInstanceId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
//                            handler.removeCallbacks(r);
                            return;
                        }
                        // Get new Instance ID tokenn
                        String token = task.getResult().getToken();
                        String msg = getString(R.string.msg_token_fmt, token);
                        fcmToken = msg;
//                        handler.postDelayed(r, 0);
                    }
                });

        buttonBot = findViewById(R.id.btn_click_bot);
        buttonBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chat.setAppId("1yoD5Y");
                Chat.setBotId("zbq2dp");
                Chat.setAppKey("gm+sCaMg5ai0vPes+tB83O3G0vaS4ahwGV+81Hnzr6jCbi7g+vYbmzHHcy/vH64jHMwq9pLr8z/eWXfVWZ4gPv64p6PvmW4aHWbnIfpF9SeKSRJGy+pXyMbiqBdzpOEurDhsLixpHvA21sUqlHPq71XJxLoNg9hPhWSfCexpzCh36OlnW1hpoX7YSNGVDRUtorCBcPerj/43UQVfeKCA+Q==");
                Chat.setUserName("ar_test55");
                Chat.setIcon(R.drawable.icon_logo);
                Chat.setIconBubleChat(R.drawable.icon_logo);
                Chat.setGreetingMessage();
                Chat.setKeyFallBack("locna");
                Chat.setRequestMenuFAllback("fallback-locna");
                Chat.setTokenFcm(fcmToken);
                Chat.setSaleForceId("6");
                Chat.setEmail("ar_test55@gmail.com");
                Chat.start(FirstActivity.this);
            }
        });

        buttonAgent = findViewById(R.id.btn_click_agent);
        buttonAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chat.setAppId("1yoD5Y");
                Chat.setBotId("zbq2dp");
                Chat.setAppKey("gm+sCaMg5ai0vPes+tB83O3G0vaS4ahwGV+81Hnzr6jCbi7g+vYbmzHHcy/vH64jHMwq9pLr8z/eWXfVWZ4gPv64p6PvmW4aHWbnIfpF9SeKSRJGy+pXyMbiqBdzpOEurDhsLixpHvA21sUqlHPq71XJxLoNg9hPhWSfCexpzCh36OlnW1hpoX7YSNGVDRUtorCBcPerj/43UQVfeKCA+Q==");
                Chat.setUserName("ar_test55");
                Chat.setIcon(R.drawable.icon_logo);
                Chat.setIconBubleChat(R.drawable.icon_logo);
                Chat.setLiveMessage();
                Chat.setKeyFallBack("locna");
                Chat.setRequestMenuFAllback("fallback-locna");
                Chat.setTokenFcm(fcmToken);
                Chat.setSaleForceId("6");
                Chat.setEmail("ar_test55@gmail.com");
                Chat.start(FirstActivity.this);
            }
        });
    }

//    public void onHandler(Context context) {
//        handler = new Handler();
//        r = new Runnable() {
//            @Override
//            public void run() {
//                Chat.setAppId("1yoD5Y");
//                Chat.setBotId("zbq2dp");
////                Chat.setAppId("NBVO0y");
////                Chat.setBotId("PdRgRe");
//                Chat.setAppKey("gm+sCaMg5ai0vPes+tB83O3G0vaS4ahwGV+81Hnzr6jCbi7g+vYbmzHHcy/vH64jHMwq9pLr8z/eWXfVWZ4gPv64p6PvmW4aHWbnIfpF9SeKSRJGy+pXyMbiqBdzpOEurDhsLixpHvA21sUqlHPq71XJxLoNg9hPhWSfCexpzCh36OlnW1hpoX7YSNGVDRUtorCBcPerj/43UQVfeKCA+Q==");
//                Chat.setUserName("ar_test55");
//                Chat.setIcon(R.drawable.icon_logo);
//                Chat.setIconBubleChat(R.drawable.icon_logo);
//                Chat.setGreetingMessage();
//                Chat.setLiveMessage();
//                Chat.setKeyFallBack("locna");
//                Chat.setRequestMenuFAllback("fallback-locna");
//                Chat.setTokenFcm(fcmToken);
//                Chat.setSaleForceId("6");
//                Chat.setEmail("ar_test55@gmail.com");
//                Chat.start(context);
//            }
//        };
//    }


}

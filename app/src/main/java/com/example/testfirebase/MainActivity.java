package com.example.testfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import ai.lenna.lennachatmodul.Chat;

public class MainActivity extends AppCompatActivity {

    String fcmToken = "";
    public static Runnable r;
    public static Handler handler;

    private static String TAG = "MyFirebaseInstanceId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onHandler(this);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            handler.removeCallbacks(r);
                            return;
                        }

                        // Get new Instance ID tokenn
                        String token = task.getResult().getToken();
                        String msg = getString(R.string.msg_token_fmt, token);

                        fcmToken = msg;
                        handler.postDelayed(r, 0);
//                        if (!fcmToken.equals("")) {
//                            handler.postDelayed(r, 0);
//                        } else {
//                            handler.removeCallbacks(r);
//                        }
                    }
                });
    }

    public void onHandler(Context context) {
        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                Chat.setAppId("1yoD5Y");
                Chat.setBotId("zbq2dp");
//                Chat.setAppId("NBVO0y");
//                Chat.setBotId("PdRgRe");
                Chat.setAppKey("gm+sCaMg5ai0vPes+tB83O3G0vaS4ahwGV+81Hnzr6jCbi7g+vYbmzHHcy/vH64jHMwq9pLr8z/eWXfVWZ4gPv64p6PvmW4aHWbnIfpF9SeKSRJGy+pXyMbiqBdzpOEurDhsLixpHvA21sUqlHPq71XJxLoNg9hPhWSfCexpzCh36OlnW1hpoX7YSNGVDRUtorCBcPerj/43UQVfeKCA+Q==");
                Chat.setUserName("ar55");
                Chat.setIcon(R.drawable.icon_logo);
                Chat.setIconBubleChat(R.drawable.icon_logo);
                Chat.setGreetingMessage("hai");
                Chat.setKeyFallBack("locna");
                Chat.setRequestMenuFAllback("fallback-locna");
                Chat.setTokenFcm(fcmToken);
                Chat.setSaleForceId("6");
                Chat.setEmail("ar55@gmail.com");
                Chat.start(context);
            }
        };
    }
}

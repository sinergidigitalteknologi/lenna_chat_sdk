package ai.lenna.lennachatmodul.service;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;
import java.util.Map;

import ai.lenna.lennachatmodul.util.Constant;

@Keep
public class LennaFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "LennaFirebaseMessagingService";

    @SuppressLint("LongLogTag")
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification());
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + TAG + remoteMessage.getData());
            try {
                Map<String, String> dataMap = remoteMessage.getData();
                handleDataMessage(dataMap);
            } catch (Exception e) {
                Log.d(TAG, "Exception: " + e.getMessage());
            }
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }

    private void handleDataMessage(Map<String, String> dataMap){
        if (Constant.IS_CHAT_LENNA_ACTIVE) {
            String categoryNotif = dataMap.get("category");
            if (categoryNotif.equals("chat")){
                Intent pushNotification = new Intent(Constant.PUSH_NOTIFICATION);
                pushNotification.putExtra("message", dataMap.get("message"));
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
            }
        }
    }


    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }
}
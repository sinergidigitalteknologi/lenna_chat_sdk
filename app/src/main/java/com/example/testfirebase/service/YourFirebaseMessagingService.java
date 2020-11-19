package com.example.testfirebase.service;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class YourFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG1 = "YourFirebaseMessagingService";

    @SuppressLint("LongLogTag")
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG1, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG1, "Message Notification Body: " + remoteMessage.getNotification());
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG1, "Message data payload: " + TAG1 + remoteMessage.getData());
            try {
                Map<String, String> dataMap = remoteMessage.getData();
                Log.d(TAG1, "Message data payload: " + TAG1 + remoteMessage.getData());
            } catch (Exception e) {
                Log.d(TAG1, "Exception: " + e.getMessage());
            }
        }

    }

    @SuppressLint("LongLogTag")
    @Override
    public void onNewToken(String token) {
        Log.d(TAG1, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }
}

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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testfirebase.inbox.InboxActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.pixplicity.easyprefs.library.Prefs;

import ai.lenna.lennachatmodul.Chat;

public class FirstActivity extends AppCompatActivity {

    TextView mTvLogout;
    Button buttonBot, buttonEmailIn;
    EditText editEmailIn, editUsrnameIn;
    LinearLayout layoutEmailIn, layoutWasLogin;

    private static String TAG = "MyFirebaseInstanceId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mTvLogout = findViewById(R.id.tv_logout);
        buttonBot = findViewById(R.id.btn_click_bot);
        editEmailIn = findViewById(R.id.et_email_in);
        buttonEmailIn = findViewById(R.id.btn_email_in);
        layoutEmailIn = findViewById(R.id.ll_first_main);
        editUsrnameIn = findViewById(R.id.et_username_in);
        layoutWasLogin = findViewById(R.id.ll_second_main);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                        Chat.setTokenFcm(msg);
                    }
                });

        getEmailIn();

        mTvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs.putString("editEmailIn", "");
                Prefs.putString("editUsernmaeIn", "");
                Chat.removeTokenLogin();
                finish();
            }
        });

        buttonBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, InboxActivity.class);
                startActivity(intent);
            }
        });

        buttonEmailIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editEmailIn.getText().toString().equals("") && !editUsrnameIn.getText().toString().equals("")) {
                    Prefs.putString("editEmailIn", editEmailIn.getText().toString());
                    Prefs.putString("editUsernmaeIn", editUsrnameIn.getText().toString());
                    Intent intent = new Intent(FirstActivity.this, InboxActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(FirstActivity.this, "Isi form dengan benar",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getEmailIn();
    }

    public void getEmailIn() {
        if (Prefs.getString("editEmailIn","").equals("")
                && Prefs.getString("editUsernameIn","").equals("")) {
            layoutWasLogin.setVisibility(View.GONE);
            layoutEmailIn.setVisibility(View.VISIBLE);
        } else {
            layoutWasLogin.setVisibility(View.VISIBLE);
            layoutEmailIn.setVisibility(View.GONE);
        }
    }


}

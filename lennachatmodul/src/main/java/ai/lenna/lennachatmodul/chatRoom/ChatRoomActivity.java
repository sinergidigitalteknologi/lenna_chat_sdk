package ai.lenna.lennachatmodul.chatRoom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import ai.lenna.lennachatmodul.R;

public class ChatRoomActivity extends AppCompatActivity {

    LinearLayout lyMsgBot, lyMsgAgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        lyMsgBot = (LinearLayout) findViewById(R.id.ly_msg_bot);
        lyMsgBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        lyMsgAgent = (LinearLayout) findViewById(R.id.ly_msg_agent);
        lyMsgAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
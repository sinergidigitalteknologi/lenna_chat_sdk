package ai.lenna.lennachatmodul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import ai.lenna.lennachatmodul.util.AesCipher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        Context context = this;
        Chat.setAppId("NBVO0y");
        Chat.setBotId("PdRgRe");
        Chat.setUserName("Pengunjung");
        Chat.setIcon(R.drawable.logo_ancol);
        Chat.setIconBubleChat(R.drawable.logo_ancol);
        Chat.setGreetingMessage("hai");
        Chat.setPitchTts("1.3");
        Chat.setSpeechRateTts("1.5");
        Chat.start(context);



    }
}

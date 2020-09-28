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
        Chat.setAppId("NBVO0y");
        Chat.setBotId("PdRgRe");
        Chat.setUserName("Pengunjung");
        Chat.setIcon(R.drawable.logo_ancol);
        Chat.setIconBubleChat(R.drawable.logo_ancol);
        Chat.setGreetingMessage("hai");
        Chat.setKeyFallBack("locna");
        Chat.setRequestMenuFAllback("fallback-locna");
        Chat.start(this);

    }
}

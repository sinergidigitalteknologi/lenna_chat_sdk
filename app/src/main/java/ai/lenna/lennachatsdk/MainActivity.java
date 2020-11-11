package ai.lenna.lennachatsdk;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import org.json.JSONObject;
import ai.lenna.lennachatmodul.Chat;
import ai.lenna.lennachatmodul.util.AesCipher;

@Keep
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        Chat.setAppId("NBVO0y");
        Chat.setBotId("PdRgRe");
        Chat.setAppKey("gm+sCaMg5ai0vPes+tB83O3G0vaS4ahwGV+81Hnzr6jCbi7g+vYbmzHHcy/vH64jHMwq9pLr8z/eWXfVWZ4gPv64p6PvmW4aHWbnIfpF9SeKSRJGy+pXyMbiqBdzpOEurDhsLixpHvA21sUqlHPq71XJxLoNg9hPhWSfCexpzCh36OlnW1hpoX7YSNGVDRUtorCBcPerj/43UQVfeKCA+Q==");
        Chat.setUserName("Pengunjung");
        Chat.setIcon(R.drawable.logo_chat);
        Chat.setIconBubleChat(R.drawable.logo_ancol);
        Chat.setGreetingMessage("hai");
        Chat.setKeyFallBack("locna");
        Chat.setRequestMenuFAllback("fallback-locna");
        Chat.start(this);

    }
}

package ai.lenna.lennachatsdk;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import ai.lenna.lennachatmodul.Chat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        Chat.setAppId("");
        Chat.setBotId("");

    }
}

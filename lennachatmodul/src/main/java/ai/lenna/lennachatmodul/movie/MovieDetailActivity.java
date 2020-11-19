package ai.lenna.lennachatmodul.movie;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ai.lenna.lennachatmodul.R;

@Keep
public class MovieDetailActivity extends AppCompatActivity {

    @Keep
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
    }
}

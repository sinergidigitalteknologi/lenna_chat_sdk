package ai.lenna.lennachatmodul;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        Chat.setEmail("viky@lenna.ai");
        Chat.setUserId("Bmbv1y");
        Chat.setIcon(R.drawable.logo_ancol);
        Chat.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImFiZjJiZjk3NDVhNjJjNzMxOGFjYmMyMTUxZTlmYmNiNzMwZDRlY2Y1MzJjMjNjYTg5ODA1ZTcyMDgxMDcxN2I0ZTk3MTkzOTc5ZWUzM2U5In0.eyJhdWQiOiIxIiwianRpIjoiYWJmMmJmOTc0NWE2MmM3MzE4YWNiYzIxNTFlOWZiY2I3MzBkNGVjZjUzMmMyM2NhODk4MDVlNzIwODEwNzE3YjRlOTcxOTM5NzllZTMzZTkiLCJpYXQiOjE1OTk0NjI0MDAsIm5iZiI6MTU5OTQ2MjQwMCwiZXhwIjoxNjMwOTk4NDAwLCJzdWIiOiI1NjU0NSIsInNjb3BlcyI6W119.JIYnaL1hlDLlhiFIHRCWbG_DThWCnEG1GqWE1f3J0NzAaYKIAE8JpiAnZAhnkUTA_bbbLoY29RVW_AF65LeMVlI5ByGuhj93_eDK0RmUdz9rSvHzUyyjb0pfP8rRaTKUo7Q7uiSGUX_GWsWBqXgL1hTJsU-mGCTdBMYkYDgNVlfFnWnrrqACkLJxEQpON5oJN1IdSfmtZAhWZRRj_rhbFoLEgWYuQrsj9vBFgznixRnD6NBLYpkUmGWXXxkduzRBGeWjp8VHEUT-BHslRXEA0SFU08Rqt352itXwDHSPRi982NzT0DkjTH1jhS_404rFCI8jkZ6vpEkgLL5X0udZUR0LanGWegeJ-Km3lGcbI9cT0Ve79OWmuH9P_IQc25D4-aQBUtV-W6qf95-6aW7u0qVw0oi4LwqizDZYa5BRQynilY6aLC2ZpWkp3boeFLu9URQbmOwpZ6qPlTaHnESgPrJvjudsVzwqYE-qNhyLkbBlG3wVesbtM9kqMAR0v2m4ipOSAB9qQOUzD17lpOvKwmSJL25esMS2HDCRwzdTG9K_Tck7gdDAcrfPm3wkcduKRsiSYSDF2Re2NWyj52pb-bjCzRDJzd56bItMZxesSAOfpWaDALC97HXkR8SkBA9AGx2cBL4HEu3SR_oLengJLStVRwBwFJSGMiA8qCUs07M");
        Chat.start(this);

    }
}

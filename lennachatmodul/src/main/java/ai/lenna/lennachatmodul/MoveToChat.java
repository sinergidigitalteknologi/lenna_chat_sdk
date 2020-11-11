package ai.lenna.lennachatmodul;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Keep;

import ai.lenna.lennachatmodul.chat.ChatActivity;

@Keep
public class MoveToChat {
    public static void move(Context context){
        Intent  intent = new Intent(context, ChatActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        ((Activity)context).startActivity(intent);
    }
}

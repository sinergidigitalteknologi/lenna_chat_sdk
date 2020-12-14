package ai.lenna.lennachatmodul.chat.viewholder;

import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.ChatActivity;
import ai.lenna.lennachatmodul.chat.ChatContract;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.model.ChatObject;

@Keep
public class ChatRespOpenAppVH extends BaseViewHolder {

    private CardView cardViewOpenApss;
    private ImageView imageOpenApps;
    private TextView textTitle;
    private ChatContract.View view1;

    public ChatRespOpenAppVH(@NonNull View itemView, ChatContract.View view) {
        super(itemView);
        this.imageOpenApps = itemView.findViewById(R.id.image_wa);
        this.textTitle = itemView.findViewById(R.id.title);
        this.cardViewOpenApss = itemView.findViewById(R.id.cardViewOpenApss);
        this.view1 = view;

    }

    @Override
    public void onBindView(ChatObject object, ArrayList<ChatObject> listObject, int position) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Picasso.get().load(object.getImageAction()).error(R.drawable.imagenotfound).into(this.imageOpenApps);
        } else {
            ChatActivity.mPicasso.load(object.getImageAction()).error(R.drawable.imagenotfound).into(this.imageOpenApps);
        }
        cardViewOpenApss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                view1.actionOpenApp(object.getChatOutputAction(), object.getSubTypeAction());

            }

        });

        this.textTitle.setText(object.getTextAction());


    }
}

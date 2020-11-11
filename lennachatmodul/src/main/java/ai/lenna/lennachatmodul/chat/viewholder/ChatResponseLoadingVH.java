package ai.lenna.lennachatmodul.chat.viewholder;

import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Keep;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.model.ChatObject;
import ai.lenna.lennachatmodul.util.Constant;
import ai.lenna.lennachatmodul.util.TypingIndicator;

@Keep
public class ChatResponseLoadingVH extends BaseViewHolder {

    LinearLayout typingIndicatorContainer,linearLayoutLoading;
    private ImageView imgBotMessage;


    public ChatResponseLoadingVH(View itemView) {
        super(itemView);
        this.typingIndicatorContainer = (LinearLayout) itemView.findViewById(R.id.container_group_channel_list_typing_indicator);
        this.linearLayoutLoading = (LinearLayout) itemView.findViewById(R.id.linearLoading);
        this.imgBotMessage = (ImageView) itemView.findViewById(R.id.img_bot_message_loading);

    }


    @Override
    public void onBindView(ChatObject object) {

        ArrayList<ImageView> indicatorImages = new ArrayList<>();
        indicatorImages.add((ImageView) typingIndicatorContainer.findViewById(R.id.typing_indicator_dot_1));
        indicatorImages.add((ImageView) typingIndicatorContainer.findViewById(R.id.typing_indicator_dot_2));
        indicatorImages.add((ImageView) typingIndicatorContainer.findViewById(R.id.typing_indicator_dot_3));
        this.imgBotMessage.setImageResource(Prefs.getInt("ICON_BUBLE_CHAT",0));

        TypingIndicator indicator  = new TypingIndicator(indicatorImages, 600);
        indicator.animate();

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N){
            linearLayoutLoading.setBackgroundResource(R.drawable.chat_response_background);
        }
    }
}

package ai.lenna.lennachatmodul.chat.viewholder;

import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Keep;

import com.pixplicity.easyprefs.library.Prefs;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.ChatContract;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.model.ChatObject;

@Keep
public class ChatInputVH extends BaseViewHolder {
    private TextView tvInputText;
    private TextView tvTimeTextInput;
    private LinearLayout linearLayoutInput;
    private ChatContract.View view;
    private ImageView ivUserMessage;

    public ChatInputVH(View itemView, ChatContract.View view) {
        super(itemView);
        this.tvInputText = (TextView) itemView.findViewById(R.id.tv_input_text);
        this.tvTimeTextInput =  (TextView) itemView.findViewById(R.id.tv_time_text_input);
        this.linearLayoutInput = (LinearLayout)itemView.findViewById(R.id.linearInput);
        this.ivUserMessage = (ImageView) itemView.findViewById(R.id.iv_user_message);
        this.view = view;
    }

    @Override
    public void onBindView(ChatObject object) {
        this.tvInputText.setText(object.getText());
        this.tvTimeTextInput.setText(object.getTime());

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N){
            linearLayoutInput.setBackgroundResource(R.drawable.background_chat_req);
        }
        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setTextChat(object.getText());
            }
        });
        this.ivUserMessage.setImageResource(Prefs.getInt("ICON_BUBLE_CHAT", 0));
    }
}

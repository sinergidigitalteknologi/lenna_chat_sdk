package ai.lenna.lennachatmodul.chat.viewholder;

import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.model.ChatObject;
import ai.lenna.lennachatmodul.util.Constant;


public class ChatRespTextVH extends BaseViewHolder {

    private TextView tvResponseText;
    private TextView tvTimeTextInput;
    private ImageView imgBotMessage;
    private LinearLayout linearLayoutResponse;

    public ChatRespTextVH(View itemView) {
        super(itemView);
        this.tvResponseText = (TextView) itemView.findViewById(R.id.tv_response_text);
        this.tvTimeTextInput =  (TextView) itemView.findViewById(R.id.tv_time_text_input);
        this.linearLayoutResponse =  (LinearLayout) itemView.findViewById(R.id.linearResponse);
        this.imgBotMessage = (ImageView) itemView.findViewById(R.id.img_bot_message);
    }

    @Override
    public void onBindView(ChatObject object) {
        this.tvResponseText.setText(object.getText());
        this.tvTimeTextInput.setText(object.getTime());
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N){
            linearLayoutResponse.setBackgroundResource(R.drawable.chat_response_background);
        }
        this.imgBotMessage.setImageResource(Constant.ICON_BUBLE_CHAT);
    }
}

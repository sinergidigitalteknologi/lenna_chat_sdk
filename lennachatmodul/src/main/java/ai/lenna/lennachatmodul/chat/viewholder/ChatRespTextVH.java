package ai.lenna.lennachatmodul.chat.viewholder;

import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.model.ChatObject;


public class ChatRespTextVH extends BaseViewHolder {

    private TextView tvResponseText;
    private TextView tvTimeTextInput;
    private LinearLayout linearLayoutResponse;

    public ChatRespTextVH(View itemView) {
        super(itemView);
        this.tvResponseText = (TextView) itemView.findViewById(R.id.tv_response_text);
        this.tvTimeTextInput =  (TextView) itemView.findViewById(R.id.tv_time_text_input);
        this.linearLayoutResponse =  (LinearLayout) itemView.findViewById(R.id.linearResponse);;
    }

    @Override
    public void onBindView(ChatObject object) {
        this.tvResponseText.setText(object.getText());
        this.tvTimeTextInput.setText(object.getTime());
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N){
            linearLayoutResponse.setBackgroundResource(R.drawable.chat_response_background);
        }
    }
}

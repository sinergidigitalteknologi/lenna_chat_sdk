package ai.lenna.lennachatmodul.chat.viewholder;

import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.ChatContract;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.model.ChatObject;


public class ChatInputVH extends BaseViewHolder {
    private TextView tvInputText;
    private TextView tvTimeTextInput;
    private LinearLayout linearLayoutInput;
    private ChatContract.View view;

    public ChatInputVH(View itemView, ChatContract.View view) {
        super(itemView);
        this.tvInputText = (TextView) itemView.findViewById(R.id.tv_input_text);
        this.tvTimeTextInput =  (TextView) itemView.findViewById(R.id.tv_time_text_input);
        this.linearLayoutInput = (LinearLayout)itemView.findViewById(R.id.linearInput);
        this.view = view;
    }

    @Override
    public void onBindView(ChatObject object) {
        this.tvInputText.setText(object.getText());
        this.tvTimeTextInput.setText(object.getTime());

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N){
            linearLayoutInput.setBackgroundResource(R.drawable.chat_input_background);
        }
        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setTextChat(object.getText());
            }
        });
    }
}

package ai.lenna.lennachatmodul.chat.viewholder;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Keep;

import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.ChatContract;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.model.ChatObject;
import ai.lenna.lennachatmodul.util.Constant;

@Keep
public class ChatInputVH extends BaseViewHolder {

    private ChatContract.View view;
    private ImageView ivUserMessage;
    private LinearLayout linearLayoutInput, lyTglChatInput;
    private TextView tvInputText, tvTimeTextInput, tvTglChatIn;

    public ChatInputVH(View itemView, ChatContract.View view) {
        super(itemView);
        this.tvInputText = (TextView) itemView.findViewById(R.id.tv_input_text);
        this.tvTglChatIn = (TextView) itemView.findViewById(R.id.tv_tgl_chat_in);
        this.ivUserMessage = (ImageView) itemView.findViewById(R.id.iv_user_message);
        this.linearLayoutInput = (LinearLayout)itemView.findViewById(R.id.linearInput);
        this.tvTimeTextInput =  (TextView) itemView.findViewById(R.id.tv_time_text_input);
        this.lyTglChatInput = (LinearLayout) itemView.findViewById(R.id.ly_tgl_chat_input);

        this.view = view;
    }

    private String getDateYesterday() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }

    private String getDateNow() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    private void showDateLayout(String textDate) {
        lyTglChatInput.setVisibility(View.VISIBLE);
        if (textDate.equals(getDateNow())) {
            tvTglChatIn.setText("Hari ini");
        } else if (textDate.equals(getDateYesterday())) {
            tvTglChatIn.setText("Kemarin");
        } else {
            tvTglChatIn.setText(textDate);
        }
    }

    @Override
    public void onBindView(ChatObject object, ArrayList<ChatObject> listObject, int position) {
        this.tvInputText.setText(object.getText());
        this.tvTimeTextInput.setText(object.getTime());

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
            linearLayoutInput.setBackgroundResource(R.drawable.background_chat_req);
            lyTglChatInput.setBackgroundResource(R.drawable.background_chat_tgl);
        }

        if (position == 0) {
            showDateLayout(object.getDate());
        } else {
            if (!object.getDate().equals(listObject.get(position-1).getDate())) {
                showDateLayout(object.getDate());
            } else {
                lyTglChatInput.setVisibility(View.GONE);
                tvTglChatIn.setText("");
            }
        }

        this.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setTextChat(object.getText());
            }
        });
//        this.ivUserMessage.setImageResource(Prefs.getInt("ICON_BUBLE_CHAT", 0));
        this.ivUserMessage.setImageBitmap(Constant.ICON_BUBLE_CHAT_BITMAP);
    }
}

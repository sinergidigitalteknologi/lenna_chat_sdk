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
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.model.ChatObject;
import ai.lenna.lennachatmodul.util.Constant;

@Keep
public class ChatRespTextVH extends BaseViewHolder {

    private ImageView imgBotMessage;
    private LinearLayout linearLayoutResponse, lyTglChatResp;
    private TextView tvResponseText, tvTimeTextInput, tvTglChatRes;

    public ChatRespTextVH(View itemView) {
        super(itemView);
        this.tvTglChatRes = (TextView) itemView.findViewById(R.id.tv_tgl_chat_res);
        this.imgBotMessage = (ImageView) itemView.findViewById(R.id.img_bot_message);
        this.tvResponseText = (TextView) itemView.findViewById(R.id.tv_response_text);
        this.tvTimeTextInput =  (TextView) itemView.findViewById(R.id.tv_time_text_input);
        this.lyTglChatResp =  (LinearLayout) itemView.findViewById(R.id.ly_tgl_chat_resp);
        this.linearLayoutResponse =  (LinearLayout) itemView.findViewById(R.id.linearResponse);
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
        lyTglChatResp.setVisibility(View.VISIBLE);
        if (textDate.equals(getDateNow())) {
            tvTglChatRes.setText("Hari ini");
        } else if (textDate.equals(getDateYesterday())) {
            tvTglChatRes.setText("Kemarin");
        } else {
            tvTglChatRes.setText(textDate);
        }
    }

    @Override
    public void onBindView(ChatObject object, ArrayList<ChatObject> listObject, int position) {
        this.tvResponseText.setText(object.getText());
        this.tvTimeTextInput.setText(object.getTime());

        Log.d("getUserType", object.getUserType());

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N){
            linearLayoutResponse.setBackgroundResource(R.drawable.background_chat_res);
        }
        if (position == 0) {
            showDateLayout(object.getDate());
        } else {
            if (!object.getDate().equals(listObject.get(position-1).getDate())) {
                showDateLayout(object.getDate());
            } else {
                lyTglChatResp.setVisibility(View.GONE);
                tvTglChatRes.setText("");
            }
        }

        if (object.getUserType() != null) {
            if (object.getUserType().equals("user_platform")) {
//                this.imgBotMessage.setImageResource(R.drawable.icon_agent);
                this.imgBotMessage.setImageBitmap(Constant.ICON_AGENT_LENNA_BITMAP);
            }
            if (object.getUserType().equals("bot")) {
                this.imgBotMessage.setImageBitmap(Constant.ICON_BOT_LENNA_BITMAP);
//                this.imgBotMessage.setImageResource(R.drawable.icon_bot);
            }
        } else {
            this.imgBotMessage.setImageBitmap(Constant.ICON_AGENT_LENNA_BITMAP);
//            this.imgBotMessage.setImageResource(R.drawable.icon_agent);
        }
//        this.imgBotMessage.setImageResource(Prefs.getInt("ICON_BUBLE_CHAT", 0));
    }
}

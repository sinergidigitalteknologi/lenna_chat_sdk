package ai.lenna.lennachatmodul.chat.viewholder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.ChatContract;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.adapter.ChatCarouselAdapter;
import ai.lenna.lennachatmodul.chat.adapter.ChatCarouselApiAdapter;
import ai.lenna.lennachatmodul.chat.adapter.ChatPostCallBack;
import ai.lenna.lennachatmodul.chat.model.ChatObject;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnCarousel;
import ai.lenna.lennachatmodul.chat.model.output.ChatColumnCarouselApi;

@Keep
public class ChatRespCarouselVH extends BaseViewHolder {

    Context context;
    TextView tvTglChatCarousel;
    RecyclerView recyclerViewAdv;
    LinearLayout lyTglChatCarousel;

    private ChatContract.View view;

    public ChatRespCarouselVH(@NonNull View itemView, ChatContract.View view) {
        super(itemView);

        this.recyclerViewAdv = (RecyclerView) itemView.findViewById(R.id.rvChatCarousel);
        this.tvTglChatCarousel = (TextView) itemView.findViewById(R.id.tv_tgl_chat_caraousel);
        this.lyTglChatCarousel =  (LinearLayout) itemView.findViewById(R.id.ly_tgl_chat_carousel);
        this.context = itemView.getContext();
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
        lyTglChatCarousel.setVisibility(View.VISIBLE);
        if (textDate.equals(getDateNow())) {
            tvTglChatCarousel.setText("Hari ini");
        } else if (textDate.equals(getDateYesterday())) {
            tvTglChatCarousel.setText("Kemarin");
        } else {
            tvTglChatCarousel.setText(textDate);
        }
    }

    @Override
    public void onBindView(ChatObject object, ArrayList<ChatObject> listObject, int position) {
        String sourceTypeString = object.getSourceType();

        if (position == 0) {
            showDateLayout(object.getDate());
        } else {
            if (!object.getDate().equals(listObject.get(position-1).getDate())) {
                showDateLayout(object.getDate());
            } else {
                lyTglChatCarousel.setVisibility(View.GONE);
                tvTglChatCarousel.setText("");
            }
        }

        if (sourceTypeString.equals("history")) {
            ArrayList<ChatColumnCarouselApi> chatColumnCarousels = object.getChatColumnCarouselsApi();

            ChatCarouselApiAdapter adapter = new ChatCarouselApiAdapter(chatColumnCarousels, new ChatPostCallBack() {
                @Override
                public void onRowClick(String textChat) {
                    view.inputChat(textChat);
                }
            });
            this.recyclerViewAdv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            this.recyclerViewAdv.setAdapter(adapter);
            this.recyclerViewAdv.setOnFlingListener(null);
            SnapHelper snapHelper = new LinearSnapHelper();
            snapHelper.attachToRecyclerView(this.recyclerViewAdv);

        } else {
            ArrayList<ChatColumnCarousel> chatColumnCarousels = object.getChatColumnCarousels();

            ChatCarouselAdapter adapter = new ChatCarouselAdapter(chatColumnCarousels, new ChatPostCallBack() {
                @Override
                public void onRowClick(String textChat) {
                    view.inputChat(textChat);
                }
            });
            this.recyclerViewAdv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            this.recyclerViewAdv.setAdapter(adapter);
            this.recyclerViewAdv.setOnFlingListener(null);
            SnapHelper snapHelper = new LinearSnapHelper();
            snapHelper.attachToRecyclerView(this.recyclerViewAdv);
        }

    }


}

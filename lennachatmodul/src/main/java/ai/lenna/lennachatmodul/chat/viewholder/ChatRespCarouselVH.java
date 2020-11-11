package ai.lenna.lennachatmodul.chat.viewholder;

import android.content.Context;
import android.view.View;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.ChatContract;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.adapter.ChatCarouselAdapter;
import ai.lenna.lennachatmodul.chat.adapter.ChatPostCallBack;
import ai.lenna.lennachatmodul.chat.model.ChatObject;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnCarousel;

@Keep
public class ChatRespCarouselVH extends BaseViewHolder {

    Context context;
    RecyclerView recyclerViewAdv;
    private ChatContract.View view;

    public ChatRespCarouselVH(@NonNull View itemView, ChatContract.View view) {
        super(itemView);
        this.recyclerViewAdv = (RecyclerView) itemView.findViewById(R.id.rvChatCarousel);
        this.context = itemView.getContext();
        this.view = view;
    }

    @Override
    public void onBindView(ChatObject object) {
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

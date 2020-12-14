package ai.lenna.lennachatmodul.chat.viewholder;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.ChatActivity;
import ai.lenna.lennachatmodul.chat.ChatContract;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.adapter.ChatListAdapter;
import ai.lenna.lennachatmodul.chat.adapter.ChatPostCallBack;
import ai.lenna.lennachatmodul.chat.model.ChatObject;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnGrid;

@Keep
public class ChatRespListVH extends BaseViewHolder implements ChatContract.Adapter{

    ImageView imageViewLogo;
    TextView textViewSubtitle;
    RecyclerView recyclerView;
    Context context;
    private ChatContract.View view;

    public ChatRespListVH(@NonNull View itemView, ChatContract.View view) {
        super(itemView);
        this.view = view;
        this.imageViewLogo = (ImageView) itemView.findViewById(R.id.image_view_provider);
        this.textViewSubtitle = (TextView) itemView.findViewById(R.id.text_view_subtitle);
        this.recyclerView = (RecyclerView) itemView.findViewById(R.id.rvGrid);
        this.context = itemView.getContext();
    }

    @Override
    public void onBindView(ChatObject object, ArrayList<ChatObject> listObject, int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Picasso.get().load(object.getImageUrlGrid()).error(R.drawable.imagenotfound).into(imageViewLogo);
        } else {
            ChatActivity.mPicasso.load(object.getImageUrlGrid()).error(R.drawable.imagenotfound).into(imageViewLogo);
        }
//        Glide.with(context).load(object.getImageUrlGrid()).into(imageViewLogo);
//        Picasso.get().load(object.getImageUrlGrid()).into(this.imageViewLogo);
        this.textViewSubtitle.setText(object.getSubTitleGrid());
        ArrayList<ChatColumnGrid> chatColumnGridArrayList = object.getChatColumnGrids();
        ChatListAdapter adapter = new ChatListAdapter(chatColumnGridArrayList, new ChatPostCallBack() {
            @Override
            public void onRowClick(String textChat) {
                view.inputChat(textChat);
            }
        });
        this.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        this.recyclerView.setAdapter(adapter);
    }

    @Override
    public void attachViewAdapter(ChatContract.View view) {
        this.view = view;
    }
}

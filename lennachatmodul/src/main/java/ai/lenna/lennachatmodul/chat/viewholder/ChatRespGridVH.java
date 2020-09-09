package ai.lenna.lennachatmodul.chat.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.ChatContract;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.adapter.ChatGridAdapter;
import ai.lenna.lennachatmodul.chat.adapter.ChatPostCallBack;
import ai.lenna.lennachatmodul.chat.model.ChatObject;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnGrid;

public class ChatRespGridVH extends BaseViewHolder implements ChatContract.Adapter{

    ImageView imageViewLogo;
    TextView textViewSubtitle;
    RecyclerView recyclerView;
    Context context;
    private ChatContract.View view;

    public ChatRespGridVH(@NonNull View itemView, ChatContract.View view) {
        super(itemView);
        this.view = view;
        this.imageViewLogo = (ImageView) itemView.findViewById(R.id.image_view_provider);
        this.textViewSubtitle = (TextView) itemView.findViewById(R.id.text_view_subtitle);
        this.recyclerView = (RecyclerView) itemView.findViewById(R.id.rvGrid);
        this.context = itemView.getContext();
    }

    @Override
    public void onBindView(ChatObject object) {
        Glide.with(context).load(object.getImageUrlGrid()).into(imageViewLogo);
//        Picasso.get().load(object.getImageUrlGrid()).into(this.imageViewLogo);
        this.textViewSubtitle.setText(object.getSubTitleGrid());
        ArrayList<ChatColumnGrid> chatColumnGridArrayList = object.getChatColumnGrids();
        ChatGridAdapter adapter = new ChatGridAdapter(chatColumnGridArrayList, new ChatPostCallBack() {
            @Override
            public void onRowClick(String textChat) {
                view.inputChat(textChat);
            }
        });
        this.recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        this.recyclerView.setAdapter(adapter);
    }

    @Override
    public void attachViewAdapter(ChatContract.View view) {
        this.view = view;
    }


}

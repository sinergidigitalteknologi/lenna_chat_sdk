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
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.adapter.ChatMovieAdapter;
import ai.lenna.lennachatmodul.chat.model.ChatObject;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnMovie;

@Keep
public class ChatRespMovieVH extends BaseViewHolder {

    Context context;
    RecyclerView recyclerView;
    public ChatRespMovieVH(@NonNull View itemView) {
        super(itemView);
        this.recyclerView = (RecyclerView) itemView.findViewById(R.id.rvChatMovie);
        this.context = itemView.getContext();
    }

    @Override
    public void onBindView(ChatObject object, ArrayList<ChatObject> listObject, int position) {
        ArrayList<ChatColumnMovie> chatColumnMovies = object.getChatColumnMovies();
        ChatMovieAdapter adapter = new ChatMovieAdapter(chatColumnMovies);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        this.recyclerView.setAdapter(adapter);

        this.recyclerView.setOnFlingListener(null);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(this.recyclerView);
    }


}

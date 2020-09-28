package ai.lenna.lennachatmodul.chat.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.adapter.ChatSummaryAdapter;
import ai.lenna.lennachatmodul.chat.model.ChatObject;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnSummary;

@Keep
public class ChatRespSummaryVH extends BaseViewHolder {

    final ImageView image_view_summary;
    LinearLayout llForm;
    RecyclerView recyclerView;
    LinearLayout llsumary;
    Context context;

    public ChatRespSummaryVH(@NonNull View itemView) {
        super(itemView);
        this.image_view_summary = itemView.findViewById(R.id.image_view_summary);
        this.recyclerView = itemView.findViewById(R.id.rvSummary);

        this.context = itemView.getContext();
    }

    @Override
    public void onBindView(ChatObject object) {
        ArrayList<ChatColumnSummary> chatColumnSummaries = object.getChatColumnSummaries();
        ChatSummaryAdapter adapter = new ChatSummaryAdapter(chatColumnSummaries);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        this.recyclerView.setAdapter(adapter);
        if (object.getImageUrl().equals("")) {
            image_view_summary.setImageResource(R.drawable.imagenotfound);
        } else  {
            Picasso.get().load(object.getImageUrl()).into(image_view_summary);

        }

    }
}

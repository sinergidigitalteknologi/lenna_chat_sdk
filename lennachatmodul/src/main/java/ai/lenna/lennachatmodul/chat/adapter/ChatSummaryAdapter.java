package ai.lenna.lennachatmodul.chat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnSummary;

public class ChatSummaryAdapter extends RecyclerView.Adapter<ChatSummaryAdapter.ChatSummaryVh> {

    ArrayList<ChatColumnSummary> chatColumnCarousels;

    public ChatSummaryAdapter(ArrayList<ChatColumnSummary> chatColumnCarousels) {
        this.chatColumnCarousels = chatColumnCarousels;
    }

    @NonNull
    @Override
    public ChatSummaryVh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_summary, viewGroup, false);
        return new ChatSummaryVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatSummaryVh chatCarouselItemVH, int position) {

        chatCarouselItemVH.tvLabel.setText(chatColumnCarousels.get(position).getField());
        chatCarouselItemVH.tvValue.setText(chatColumnCarousels.get(position).getValue());


    }

    @Override
    public int getItemCount() {
        return chatColumnCarousels.size();
    }

    public class ChatSummaryVh extends RecyclerView.ViewHolder {
        TextView tvLabel, tvValue;

        public ChatSummaryVh(View itemView) {
            super(itemView);

            tvLabel = (TextView) itemView.findViewById(R.id.text_view_label);
            tvValue = (TextView) itemView.findViewById(R.id.text_view_value);

        }
    }
}

package ai.lenna.lennachatmodul.chat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnGrid;

@Keep
public class ChatGridAdapter extends RecyclerView.Adapter<ChatGridAdapter.ChatGridItemVH> {

    ArrayList<ChatColumnGrid> chatColumnGrids;
    ChatPostCallBack chatGridCallBack;

    public ChatGridAdapter(ArrayList<ChatColumnGrid> chatColumnGrids, ChatPostCallBack chatGridCallBack) {
        this.chatColumnGrids = chatColumnGrids;
        this.chatGridCallBack = chatGridCallBack;
    }

    @NonNull
    @Override
    public ChatGridItemVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_grid, viewGroup, false);
        return new ChatGridItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatGridItemVH chatGridItemVH,int i) {
        chatGridItemVH.tvDenom.setText(chatColumnGrids.get(i).getText());
        chatGridItemVH.tvDenomRupiah.setText(chatColumnGrids.get(i).getSubText());
        chatGridItemVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chatGridCallBack != null){
                    chatGridCallBack.onRowClick(String.valueOf(chatColumnGrids.get(i).getDefaultAction().getData()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatColumnGrids.size();
    }


    public class ChatGridItemVH extends RecyclerView.ViewHolder {
        TextView tvDenom, tvDenomRupiah;

        public ChatGridItemVH(View itemView) {
            super(itemView);

            tvDenom = (TextView) itemView.findViewById(R.id.text_view_denom);
            tvDenomRupiah = (TextView) itemView.findViewById(R.id.text_view_denom_rupiah);
        }
    }
}

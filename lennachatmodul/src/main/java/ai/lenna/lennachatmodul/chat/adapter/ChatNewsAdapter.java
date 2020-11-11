package ai.lenna.lennachatmodul.chat.adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.ChatActivity;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnNews;

@Keep
public class ChatNewsAdapter extends RecyclerView.Adapter<ChatNewsAdapter.ChatCarouselItemVH> {

    ArrayList<ChatColumnNews> chatColumnCarousels;

    public ChatNewsAdapter(ArrayList<ChatColumnNews> chatColumnCarousels) {
        this.chatColumnCarousels = chatColumnCarousels;
    }

    @NonNull
    @Override
    public ChatCarouselItemVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_news, viewGroup, false);
        return new ChatCarouselItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatCarouselItemVH chatCarouselItemVH, int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Picasso.get().load(chatColumnCarousels.get(position).getThumbnailUrl()).error(R.drawable.imagenotfound).into(chatCarouselItemVH.imageView);
        } else {
            ChatActivity.mPicasso.load(chatColumnCarousels.get(position).getThumbnailUrl()).error(R.drawable.imagenotfound).into(chatCarouselItemVH.imageView);
        }
        chatCarouselItemVH.tvTitle.setText(chatColumnCarousels.get(position).getTitle());
        chatCarouselItemVH.tvDescription.setText(chatColumnCarousels.get(position).getTitle());

        chatCarouselItemVH.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(chatColumnCarousels.get(position).getLink()));
                chatCarouselItemVH.imageView.getContext().startActivity(browserIntent);
            }
        });
        chatCarouselItemVH.tvDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(chatColumnCarousels.get(position).getLink()));
                chatCarouselItemVH.imageView.getContext().startActivity(browserIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return chatColumnCarousels.size();
    }

    public class ChatCarouselItemVH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription;
        ImageView imageView;
        CardView cardView;

        public ChatCarouselItemVH(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.text_view_date);
            tvDescription = (TextView) itemView.findViewById(R.id.text_view_description);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
            cardView = (CardView) itemView.findViewById(R.id.cvNews);

        }
    }
}

package ai.lenna.lennachatmodul.chat.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnMovie;
import ai.lenna.lennachatmodul.movie.MovieDetailActivity;

public class ChatMovieAdapter extends RecyclerView.Adapter<ChatMovieAdapter.ChatMovieItemVH> {

    ArrayList<ChatColumnMovie> chatColumnMovie;
    ChatPostCallBack chatGridCallBack;

    public ChatMovieAdapter(ArrayList<ChatColumnMovie> chatColumnMovie) {
        this.chatColumnMovie = chatColumnMovie;
    }

    @NonNull
    @Override
    public ChatMovieItemVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_movie, viewGroup, false);
        return new ChatMovieItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMovieItemVH chatMovieItemVH, int i) {
        Picasso.get().load(chatColumnMovie.get(i).getThumbnailImageUrl()).into(chatMovieItemVH.imageView);
        chatMovieItemVH.tvTitle.setText(chatColumnMovie.get(i).getTitle());
        int sizeAction = chatColumnMovie.get(i).getActions().size();
        if (sizeAction > 0){
            for (int pos = 0; pos < sizeAction; pos++){
                switch (pos) {
                    case 0:
                        chatMovieItemVH.textViewTime1.setVisibility(View.VISIBLE);
                        chatMovieItemVH.textViewTime1.setText(chatColumnMovie.get(i).getActions().get(pos).getLabel());
                        chatMovieItemVH.textViewTime1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(chatMovieItemVH.imageView.getContext(), MovieDetailActivity.class);
                                intent.putExtra("movie_detail", chatColumnMovie.get(i).getActions().get(0).getData());
                                intent.putExtra("flag","chat");
                                chatMovieItemVH.itemView.getContext().startActivity(intent);
                            }
                        });
                        break;
                }
            }
        }
    }
    

    @Override
    public int getItemCount() {
        return chatColumnMovie.size();
    }

    public class ChatMovieItemVH extends RecyclerView.ViewHolder {
        TextView tvTitle, textViewTime1, textViewTime2;
        ImageView imageView;

        public ChatMovieItemVH(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.text_view_title_movie);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
            textViewTime1 = (TextView) itemView.findViewById(R.id.text_view_time1);
            textViewTime2 = (TextView) itemView.findViewById(R.id.text_view_time2);

        }
    }
}

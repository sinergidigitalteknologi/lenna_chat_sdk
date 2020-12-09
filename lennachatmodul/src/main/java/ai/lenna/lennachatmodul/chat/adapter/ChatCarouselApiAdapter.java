package ai.lenna.lennachatmodul.chat.adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.ChatActivity;
import ai.lenna.lennachatmodul.chat.model.carousel.ChatActionCarousel;
import ai.lenna.lennachatmodul.chat.model.output.ChatColumnCarouselApi;

@Keep
public class ChatCarouselApiAdapter extends RecyclerView.Adapter<ChatCarouselApiAdapter.ChatCarouselItemVH> {

    ArrayList<ChatColumnCarouselApi> chatColumnCarousels;
    List<ChatActionCarousel> actions;
    ChatPostCallBack chatGridCallBack;

    public ChatCarouselApiAdapter(ArrayList<ChatColumnCarouselApi> chatColumnCarousels, ChatPostCallBack chatGridCallBack) {
        this.chatColumnCarousels = chatColumnCarousels;
        this.chatGridCallBack = chatGridCallBack;
    }

    @NonNull
    @Override
    public ChatCarouselApiAdapter.ChatCarouselItemVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_carousel, viewGroup, false);
        return new ChatCarouselApiAdapter.ChatCarouselItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatCarouselApiAdapter.ChatCarouselItemVH chatCarouselItemVH, int position) {
        if (!chatColumnCarousels.get(position).getThumbnailImageUrl().equals("")){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Picasso.get().load(chatColumnCarousels.get(position).getThumbnailImageUrl()).error(R.drawable.imagenotfound).into(chatCarouselItemVH.imageView);
            } else {
                ChatActivity.mPicasso.load(chatColumnCarousels.get(position).getThumbnailImageUrl()).error(R.drawable.imagenotfound).into(chatCarouselItemVH.imageView);
            }
        }
        chatCarouselItemVH.tvTitle.setText(Html.fromHtml(chatColumnCarousels.get(position).getTitle()));
        chatCarouselItemVH.tvDescription.setText(Html.fromHtml(chatColumnCarousels.get(position).getText()));


        int sizeAction = chatColumnCarousels.get(position).getActions().size();
        if (sizeAction > 0) {
            chatCarouselItemVH.linearLayoutButton.setVisibility(View.VISIBLE);
            Log.e("sizeAction", String.valueOf(sizeAction));
            for (int i = 0; i < sizeAction; i++) {
                switch (i) {
                    case 0:
                        Log.e("action", "0");
                        chatCarouselItemVH.textViewButton1.setVisibility(View.VISIBLE);
                        chatCarouselItemVH.textViewButton1.setText(chatColumnCarousels.get(position).getActions().get(i).getLabel());
                        chatCarouselItemVH.textViewButton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                actionButton(chatCarouselItemVH, chatColumnCarousels.get(position).getActions().get(0).getType(), position, 0);
                            }
                        });
                        break;
                    case 1:
                        Log.e("action", "1");
                        chatCarouselItemVH.textViewButton2.setVisibility(View.VISIBLE);
                        chatCarouselItemVH.textViewButton2.setText(chatColumnCarousels.get(position).getActions().get(i).getLabel());
                        chatCarouselItemVH.textViewButton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                actionButton(chatCarouselItemVH, chatColumnCarousels.get(position).getActions().get(1).getType(), position, 1);
                            }
                        });

                        break;
                    case 2:
                        Log.e("action", "2");
                        chatCarouselItemVH.textViewButton3.setVisibility(View.VISIBLE);
                        chatCarouselItemVH.textViewButton3.setText(chatColumnCarousels.get(position).getActions().get(i).getLabel());
                        chatCarouselItemVH.textViewButton3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                actionButton(chatCarouselItemVH, chatColumnCarousels.get(position).getActions().get(2).getType(), position, 2);
                            }
                        });
                        break;
                    case 3:
                        Log.e("action", "3");
                        chatCarouselItemVH.textViewButton4.setVisibility(View.VISIBLE);
                        chatCarouselItemVH.textViewButton4.setText(chatColumnCarousels.get(position).getActions().get(i).getLabel());
                        chatCarouselItemVH.textViewButton4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                actionButton(chatCarouselItemVH, chatColumnCarousels.get(position).getActions().get(3).getType(), position, 3);
                            }
                        });
                        break;
                }
            }
        }
    }

    private void actionButton(ChatCarouselApiAdapter.ChatCarouselItemVH chatCarouselItemVH, String typeAction, int position, int positionItem) {
        switch (typeAction) {
            case "postback":
                if (chatGridCallBack != null){
                    chatGridCallBack.onRowClick(chatColumnCarousels.get(position)
                            .getActions().get(positionItem).getData());
                }
                break;
            case "uri":
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(
                                chatColumnCarousels.get(position)
                                        .getActions().get(positionItem).getUri()));
                chatCarouselItemVH.imageView.getContext().startActivity(browserIntent);
                break;
            case "location":
                actions = chatColumnCarousels.get(position).getActions();
                String longlat = actions.get(positionItem).getData();
                String target = ",";
                String[] separated = longlat.split(Pattern.quote(target));
                String latitude = separated[0];
                String longitude = separated[1];
                String label = separated[2].replaceAll(" ","+");
                String mapUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " (" + label + ")";

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapUri));
                intent.setPackage("com.google.android.apps.maps");
                chatCarouselItemVH.itemView.getContext().startActivity(intent);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return chatColumnCarousels.size();
    }

    public class ChatCarouselItemVH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription;
        TextView textViewButton1, textViewButton2, textViewButton3, textViewButton4;
        ImageView imageView;
        LinearLayout linearLayoutButton;


        public ChatCarouselItemVH(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.text_view_title);
            tvDescription = (TextView) itemView.findViewById(R.id.text_view_description);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
            linearLayoutButton = (LinearLayout) itemView.findViewById(R.id.llButtonAction);
            textViewButton1 = (TextView) itemView.findViewById(R.id.textViewButton1);
            textViewButton2 = (TextView) itemView.findViewById(R.id.textViewButton2);
            textViewButton3 = (TextView) itemView.findViewById(R.id.textViewButton3);
            textViewButton4 = (TextView) itemView.findViewById(R.id.textViewButton4);

        }
    }
}

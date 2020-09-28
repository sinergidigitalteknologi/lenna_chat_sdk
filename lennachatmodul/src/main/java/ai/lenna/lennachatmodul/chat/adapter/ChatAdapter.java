package ai.lenna.lennachatmodul.chat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.ChatContract;
import ai.lenna.lennachatmodul.chat.model.ChatObject;
import ai.lenna.lennachatmodul.chat.model.output.action.ChatOutputAction;
import ai.lenna.lennachatmodul.chat.viewholder.ChatInputVH;
import ai.lenna.lennachatmodul.chat.viewholder.ChatRespButtonVH;
import ai.lenna.lennachatmodul.chat.viewholder.ChatRespCarouselVH;
import ai.lenna.lennachatmodul.chat.viewholder.ChatRespConfirmVH;
import ai.lenna.lennachatmodul.chat.viewholder.ChatRespGridVH;
import ai.lenna.lennachatmodul.chat.viewholder.ChatRespHtmlVH;
import ai.lenna.lennachatmodul.chat.viewholder.ChatRespImageVH;
import ai.lenna.lennachatmodul.chat.viewholder.ChatRespListVH;
import ai.lenna.lennachatmodul.chat.viewholder.ChatRespMovieVH;
import ai.lenna.lennachatmodul.chat.viewholder.ChatRespOpenAppVH;
import ai.lenna.lennachatmodul.chat.viewholder.ChatRespSummaryVH;
import ai.lenna.lennachatmodul.chat.viewholder.ChatRespTextVH;
import ai.lenna.lennachatmodul.chat.viewholder.ChatRespTravelVH;
import ai.lenna.lennachatmodul.chat.viewholder.ChatRespWeatherVH;
import ai.lenna.lennachatmodul.chat.viewholder.ChatResponseLoadingVH;

@Keep
public class ChatAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<ChatObject> chatObjects;
    private Context context;
    private final OnClickListener listener;
    private ChatContract.View view;
    private ChatOutputAction outputAction;
    private String subtype;

    public ChatAdapter(OnClickListener listener, ArrayList<ChatObject> chatObjects, ChatContract.View view, ChatOutputAction outputAction) {
        this.chatObjects = chatObjects;
        this.listener = listener;
        this.view = view;
        this.outputAction = outputAction;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();

        // Create the ViewHolder based on the viewType
        View itemView;

        switch (viewType) {
            case ChatObject.INPUT_OBJECT:
                itemView = inflater.inflate(R.layout.chat_input_layout, parent, false);
                return new ChatInputVH(itemView, view);
            case ChatObject.RESPONSE_OBJECT:
                itemView = inflater.inflate(R.layout.chat_response_layout, parent, false);
                return new ChatRespTextVH(itemView);
            case ChatObject.RESPONSE_CAROUSEL:
                itemView = inflater.inflate(R.layout.recyclerview_chat_carousel, parent, false);
                return new ChatRespCarouselVH(itemView, view);
            case ChatObject.RESPONSE_MOVIE:
                itemView = inflater.inflate(R.layout.recyclerview_chat_movie, parent, false);
                return new ChatRespMovieVH(itemView);
            case ChatObject.RESPONSE_IMAGE:
                itemView = inflater.inflate(R.layout.chat_response_image_layout, parent, false);
                return new ChatRespImageVH(itemView);
            case ChatObject.RESPONSE_HTML:
                itemView = inflater.inflate(R.layout.chat_response_html_layout, parent, false);
                return new ChatRespHtmlVH(itemView);
            case ChatObject.RESPONSE_GRID:
                itemView = inflater.inflate(R.layout.chat_response_grid_layout, parent, false);
                return new ChatRespGridVH(itemView, view);
            case ChatObject.RESPONSE_LIST:
                itemView = inflater.inflate(R.layout.chat_response_grid_layout, parent, false);
                return new ChatRespListVH(itemView, view);
            case ChatObject.RESPONSE_CONFIRM:
                itemView = inflater.inflate(R.layout.chat_response_confirm_layout, parent, false);
                return new ChatRespConfirmVH(itemView,view);
            case ChatObject.RESPONSE_BUTTON:
                itemView = inflater.inflate(R.layout.chat_response_button_layout, parent, false);
                return new ChatRespButtonVH(itemView,view);
            case ChatObject.RESPONSE_SUMMARY:
                itemView = inflater.inflate(R.layout.chat_response_summary, parent, false);
                return new ChatRespSummaryVH(itemView);
            case ChatObject.RESPONSE_WEATHER:
                itemView = inflater.inflate(R.layout.chat_response_weather_layout, parent, false);
                return new ChatRespWeatherVH(itemView);
            case ChatObject.RESPONSE_ACTION:
                itemView = inflater.inflate(R.layout.chat_response_open_apps_image, parent, false);
                return new ChatRespOpenAppVH(itemView,view);
            case ChatObject.RESPONSE_TRAVEL:
                itemView = inflater.inflate(R.layout.recyclerview_chat_travel, parent, false);
                return new ChatRespTravelVH(itemView, view);
            default:
                itemView = inflater.inflate(R.layout.chat_response_loading_layout, parent, false);
                return new ChatResponseLoadingVH(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBindView(chatObjects.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return chatObjects.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return chatObjects.size();

    }


    public interface OnClickListener {
        void onCarouselClick(String buttonName, String payload);
    }
}

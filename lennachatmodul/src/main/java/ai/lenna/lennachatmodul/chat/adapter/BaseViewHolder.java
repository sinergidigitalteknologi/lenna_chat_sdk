package ai.lenna.lennachatmodul.chat.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ai.lenna.lennachatmodul.chat.model.ChatObject;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {


    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }


    public abstract void onBindView(ChatObject object);

}

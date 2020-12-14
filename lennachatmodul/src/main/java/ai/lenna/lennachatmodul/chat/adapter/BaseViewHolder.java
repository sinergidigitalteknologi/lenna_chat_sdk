package ai.lenna.lennachatmodul.chat.adapter;

import android.view.View;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.chat.model.ChatObject;

@Keep
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {


    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }


    public abstract void onBindView(ChatObject object, ArrayList<ChatObject> listObject, int position);

}

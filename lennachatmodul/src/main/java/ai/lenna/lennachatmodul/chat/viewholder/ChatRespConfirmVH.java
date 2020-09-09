package ai.lenna.lennachatmodul.chat.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.ChatContract;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.model.ChatObject;


public class ChatRespConfirmVH extends BaseViewHolder implements ChatContract.Adapter {

    TextView textViewTitleConfirm;
    TextView textViewTidak;
    TextView textViewYa;
    Context context;

    private ChatContract.View view1;


    public ChatRespConfirmVH(@NonNull View itemView, ChatContract.View view) {
        super(itemView);
        this.view1 = view;

        this.textViewTitleConfirm = itemView.findViewById(R.id.text_view_title_confirm);
        this.textViewYa = itemView.findViewById(R.id.text_view_ya);
        this.textViewTidak = itemView.findViewById(R.id.text_view_tidak);
        this.context = itemView.getContext();
    }

    @Override
    public void onBindView(ChatObject object) {
        this.textViewTitleConfirm.setText(object.getTextTitleConfirm());

        this.textViewYa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view1.inputChat("ya");
            }
        });

        this.textViewTidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view1.inputChat("tidak");
            }
        });

    }


    @Override
    public void attachViewAdapter(ChatContract.View view) {
        this.view1 = view;

    }
}

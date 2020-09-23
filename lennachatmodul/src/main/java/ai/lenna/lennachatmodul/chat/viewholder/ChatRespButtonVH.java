package ai.lenna.lennachatmodul.chat.viewholder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.ChatContract;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.model.ChatObject;


public class ChatRespButtonVH extends BaseViewHolder implements ChatContract.Adapter {

    TextView tvTitle;
    TextView textViewButton1, textViewButton2, textViewButton3, textViewButton4;
    LinearLayout linearLayoutButton;
    Context context;

    private ChatContract.View view1;


    public ChatRespButtonVH(@NonNull View itemView, ChatContract.View view) {
        super(itemView);
        this.view1 = view;

        this.tvTitle = (TextView) itemView.findViewById(R.id.text_view_title);
        this.linearLayoutButton = (LinearLayout) itemView.findViewById(R.id.llButtonAction);
        this.textViewButton1 = (TextView) itemView.findViewById(R.id.textViewButton1);
        this.textViewButton2 = (TextView) itemView.findViewById(R.id.textViewButton2);
        this.textViewButton3 = (TextView) itemView.findViewById(R.id.textViewButton3);
        this.textViewButton4 = (TextView) itemView.findViewById(R.id.textViewButton4);
        this.context = itemView.getContext();
    }

    @Override
    public void onBindView(ChatObject object) {
        this.tvTitle.setText(object.getTextTitleButton());
        int sizeAction = object.getChatActionButtons().size();
        if (sizeAction > 0) {
            this.linearLayoutButton.setVisibility(View.VISIBLE);
            Log.e("sizeAction", String.valueOf(sizeAction));
            for (int i = 0; i < sizeAction; i++) {
                switch (i) {
                    case 0:
                        Log.e("action", "0");
                        this.textViewButton1.setVisibility(View.VISIBLE);
                        this.textViewButton1.setText(object.getChatActionButtons().get(i).getLabel());
                        this.textViewButton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                actionButton(object,object.getChatActionButtons().get(0).getType(), 0);
                            }
                        });
                        break;
                    case 1:
                        Log.e("action", "1");
                        this.textViewButton2.setVisibility(View.VISIBLE);
                        this.textViewButton2.setText(object.getChatActionButtons().get(i).getLabel());
                        this.textViewButton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                actionButton(object,object.getChatActionButtons().get(1).getType(), 1);
                            }
                        });

                        break;
                    case 2:
                        Log.e("action", "2");
                        this.textViewButton3.setVisibility(View.VISIBLE);
                        this.textViewButton3.setText(object.getChatActionButtons().get(i).getLabel());
                        this.textViewButton3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                actionButton(object,object.getChatActionButtons().get(2).getType(), 2);
                            }
                        });
                        break;
                    case 3:
                        Log.e("action", "3");
                        this.textViewButton4.setVisibility(View.VISIBLE);
                        this.textViewButton4.setText(object.getChatActionButtons().get(i).getLabel());
                        this.textViewButton4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                actionButton(object,object.getChatActionButtons().get(3).getType(), 3);
                            }
                        });
                        break;
                }
            }
        }
    }

    private void actionButton(ChatObject object, String typeAction, int positionItem) {
        switch (typeAction) {
            case "postback":
                view1.inputChat(object.getChatActionButtons().get(positionItem).getLabel());
                break;
        }
    }


    @Override
    public void attachViewAdapter(ChatContract.View view) {
        this.view1 = view;

    }
}

package ai.lenna.lennachatmodul.chat.viewholder;

import android.view.View;
import android.webkit.WebSettings;

import androidx.annotation.NonNull;


import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.model.ChatObject;
import vcm.github.webkit.proview.ProWebView;

public class ChatRespHtmlVH extends BaseViewHolder {

    private ProWebView webViewChat;

    public ChatRespHtmlVH(@NonNull View itemView) {
        super(itemView);
        this.webViewChat = itemView.findViewById(R.id.webViewChat);
    }

    @Override
    public void onBindView(ChatObject object) {
//        webViewChat.loadData(object.getHtml(), "text/html", "UTF-8");
        webViewChat.loadHtml(object.getHtml());


        WebSettings config = webViewChat.getSettings();
        config.setBuiltInZoomControls(false);
        config.setSupportZoom(false);

//        webViewChat.setVerticalScrollBarEnabled(false);


    }
}

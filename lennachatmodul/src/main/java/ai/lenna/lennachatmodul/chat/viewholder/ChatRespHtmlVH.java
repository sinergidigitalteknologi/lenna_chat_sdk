package ai.lenna.lennachatmodul.chat.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;


import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.model.ChatObject;
import vcm.github.webkit.proview.ProWebView;

@Keep
public class ChatRespHtmlVH extends BaseViewHolder {

//    private ProWebView webViewChat;
    private WebView webViewChat;


    public ChatRespHtmlVH(@NonNull View itemView) {
        super(itemView);
        this.webViewChat = itemView.findViewById(R.id.webViewChat);
    }

    @Override
    public void onBindView(ChatObject object) {
        webViewChat.loadData(object.getHtml(), "text/html", "UTF-8");
//        webViewChat.loadHtml(object.getHtml());
        webViewChat.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {
                    Log.d("Linkk","link");
                    view.getContext().startActivity(
                            new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    webViewChat.loadData(object.getHtml(), "text/html", "UTF-8");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        WebSettings config = webViewChat.getSettings();
        config.setBuiltInZoomControls(false);
        config.setSupportZoom(false);

//        webViewChat.setVerticalScrollBarEnabled(false);


    }
}

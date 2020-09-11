package ai.lenna.lennachatmodul.chat.viewholder;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.github.chrisbanes.photoview.PhotoView;

import com.squareup.picasso.Picasso;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.model.ChatObject;

public class ChatRespImageVH extends BaseViewHolder {

    private PhotoView imageView;

    public ChatRespImageVH(@NonNull View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.image_chat_response);
    }

    @Override
    public void onBindView(ChatObject object) {
        Picasso.get().load(object.getImage_original_url()).fit().centerCrop().into(this.imageView);
        this.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFullImage(object);


            }
        });
    }

    private void showFullImage(ChatObject object){
        final Dialog nagDialog = new Dialog(itemView.getContext(),android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        nagDialog.setCancelable(false);
        nagDialog.setContentView(R.layout.dialog_image_preview);
        ImageView ivBack = (ImageView) nagDialog.findViewById(R.id.image_view_arrow_back);
        PhotoView ivPreview = (PhotoView) nagDialog.findViewById(R.id.image_view_full);
        Picasso.get().load(object.getImage_original_url()).into(ivPreview);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nagDialog.dismiss();
            }
        });

        nagDialog.show();
    }
}

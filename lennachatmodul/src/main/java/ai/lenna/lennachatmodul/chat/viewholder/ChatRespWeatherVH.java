package ai.lenna.lennachatmodul.chat.viewholder;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.ChatActivity;
import ai.lenna.lennachatmodul.chat.adapter.BaseViewHolder;
import ai.lenna.lennachatmodul.chat.model.ChatObject;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnWeather;

@Keep
public class ChatRespWeatherVH extends BaseViewHolder {

    TextView textAreaCountryCode;

    TextView textlongDate;

    ImageView imageSiang0;
    TextView textSuhu0;
    ImageView imageMalam0;

    TextView textDayWeather, textMinMaxTemprature, textNightWeather;

    TextView textShorDate1, textShorDate2, textShorDate3, textShorDate4, textShorDate5;

    ImageView imageSiang1, imageSiang2, imageSiang3, imageSiang4, imageSiang5;

    TextView textSiang1, textSiang2, textSiang3, textSiang4, textSiang5;

    ImageView imageMalam1, imageMalam2, imageMalam3, imageMalam4, imageMalam5;

    TextView textMalam1, textMalam2, textMalam3, textMalam4, textMalam5;

    Context context;

    public ChatRespWeatherVH(@NonNull View itemView) {
        super(itemView);

        this.textAreaCountryCode = itemView.findViewById(R.id.textAreaCountryCode);
        this.textlongDate = itemView.findViewById(R.id.textlongDate);
        this.imageSiang0 = itemView.findViewById(R.id.imageSiang0);
        this.textSuhu0 = itemView.findViewById(R.id.textSuhu0);
        this.imageMalam0 = itemView.findViewById(R.id.imageMalam0);
        this.textDayWeather = itemView.findViewById(R.id.textDayWeather);
        this.textMinMaxTemprature = itemView.findViewById(R.id.textMinMaxTemprature);
        this.textNightWeather = itemView.findViewById(R.id.textNightWeather);
        this.textShorDate1 = itemView.findViewById(R.id.textShorDate1);
        this.textShorDate2 = itemView.findViewById(R.id.textShorDate2);
        this.textShorDate3 = itemView.findViewById(R.id.textShorDate3);
        this.textShorDate4 = itemView.findViewById(R.id.textShorDate4);
        this.textShorDate5 = itemView.findViewById(R.id.textShorDate5);

        this.imageSiang1 = itemView.findViewById(R.id.imageSiang1);
        this.imageSiang2 = itemView.findViewById(R.id.imageSiang2);
        this.imageSiang3 = itemView.findViewById(R.id.imageSiang3);
        this.imageSiang4 = itemView.findViewById(R.id.imageSiang4);
        this.imageSiang5 = itemView.findViewById(R.id.imageSiang5);

        this.textSiang1 = itemView.findViewById(R.id.textSiang1);
        this.textSiang2 = itemView.findViewById(R.id.textSiang2);
        this.textSiang3 = itemView.findViewById(R.id.textSiang3);
        this.textSiang4 = itemView.findViewById(R.id.textSiang4);
        this.textSiang5 = itemView.findViewById(R.id.textSiang5);

        this.imageMalam1 = itemView.findViewById(R.id.imageMalam1);
        this.imageMalam2 = itemView.findViewById(R.id.imageMalam2);
        this.imageMalam3 = itemView.findViewById(R.id.imageMalam3);
        this.imageMalam4 = itemView.findViewById(R.id.imageMalam4);
        this.imageMalam5 = itemView.findViewById(R.id.imageMalam5);

        this.textMalam1 = itemView.findViewById(R.id.textMalam1);
        this.textMalam2 = itemView.findViewById(R.id.textMalam2);
        this.textMalam3 = itemView.findViewById(R.id.textMalam3);
        this.textMalam4 = itemView.findViewById(R.id.textMalam4);
        this.textMalam5 = itemView.findViewById(R.id.textMalam5);

        this.context = itemView.getContext();
    }

    @Override
    public void onBindView(ChatObject object) {



        ArrayList<ChatColumnWeather> columnWeathers = object.getChatColumnWeathers();

        String areaCountryCode = object.getArea() +", "+object.getCountryCode();
        this.textAreaCountryCode.setText(areaCountryCode);
        this.textlongDate.setText(columnWeathers.get(0).getLongDate());
//        Picasso.get().load(String.valueOf(columnWeathers.get(0).getDayIconUrl())).into(this.imageSiang0);
        this.textSuhu0.setText(columnWeathers.get(0).getTemperature());
//        Picasso.get().load(String.valueOf(columnWeathers.get(0).getNightIconUrl())).into(this.imageMalam0);
        this.textDayWeather.setText(columnWeathers.get(0).getDayWeather());
        this.textNightWeather.setText(columnWeathers.get(0).getNightWeather());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Picasso.get().load(String.valueOf(columnWeathers.get(0).getDayIconUrl())).error(R.drawable.image_not_found).into(this.imageSiang0);
            Picasso.get().load(String.valueOf(columnWeathers.get(0).getNightIconUrl())).error(R.drawable.image_not_found).into(this.imageMalam0);
        } else {
            ChatActivity.mPicasso.load(String.valueOf(columnWeathers.get(0).getDayIconUrl())).error(R.drawable.image_not_found).into(this.imageSiang0);
            ChatActivity.mPicasso.load(String.valueOf(columnWeathers.get(0).getNightIconUrl())).error(R.drawable.image_not_found).into(this.imageMalam0);
        }

        this.textShorDate1.setText(columnWeathers.get(1).getShortDate());
        this.textShorDate2.setText(columnWeathers.get(2).getShortDate());
        this.textShorDate3.setText(columnWeathers.get(3).getShortDate());
        this.textShorDate4.setText(columnWeathers.get(4).getShortDate());
        this.textShorDate5.setText(columnWeathers.get(5).getShortDate());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Picasso.get().load(String.valueOf(columnWeathers.get(1).getDayIconUrl())).error(R.drawable.image_not_found).into(this.imageSiang1);
            Picasso.get().load(String.valueOf(columnWeathers.get(2).getDayIconUrl())).error(R.drawable.image_not_found).into(this.imageSiang2);
            Picasso.get().load(String.valueOf(columnWeathers.get(3).getDayIconUrl())).error(R.drawable.image_not_found).into(this.imageSiang3);
            Picasso.get().load(String.valueOf(columnWeathers.get(4).getDayIconUrl())).error(R.drawable.image_not_found).into(this.imageSiang4);
            Picasso.get().load(String.valueOf(columnWeathers.get(5).getDayIconUrl())).error(R.drawable.image_not_found).into(this.imageSiang5);
        } else {
            ChatActivity.mPicasso.load(String.valueOf(columnWeathers.get(2).getDayIconUrl())).error(R.drawable.image_not_found).into(this.imageSiang2);
            ChatActivity.mPicasso.load(String.valueOf(columnWeathers.get(3).getDayIconUrl())).error(R.drawable.image_not_found).into(this.imageSiang3);
            ChatActivity.mPicasso.load(String.valueOf(columnWeathers.get(4).getDayIconUrl())).error(R.drawable.image_not_found).into(this.imageSiang4);
            ChatActivity.mPicasso.load(String.valueOf(columnWeathers.get(5).getDayIconUrl())).error(R.drawable.image_not_found).into(this.imageSiang5);
        }

        String txtSiang1 = columnWeathers.get(1).getMaxTemperature()+" "+columnWeathers.get(1).getMinTemperature();
        this.textSiang1.setText(txtSiang1);
        String txtSiang2 = columnWeathers.get(2).getMaxTemperature()+" "+columnWeathers.get(1).getMinTemperature();
        this.textSiang2.setText(txtSiang2);
        String txtSiang3 = columnWeathers.get(3).getMaxTemperature()+" "+columnWeathers.get(1).getMinTemperature();
        this.textSiang3.setText(txtSiang3);
        String txtSiang4 = columnWeathers.get(4).getMaxTemperature()+" "+columnWeathers.get(1).getMinTemperature();
        this.textSiang4.setText(txtSiang4);
        String txtSiang5 = columnWeathers.get(5).getMaxTemperature()+" "+columnWeathers.get(1).getMinTemperature();
        this.textSiang5.setText(txtSiang5);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Picasso.get().load(String.valueOf(columnWeathers.get(1).getNightIconUrl())).error(R.drawable.image_not_found).into(this.imageMalam1);
            Picasso.get().load(String.valueOf(columnWeathers.get(2).getNightIconUrl())).error(R.drawable.image_not_found).into(this.imageMalam2);
            Picasso.get().load(String.valueOf(columnWeathers.get(3).getNightIconUrl())).error(R.drawable.image_not_found).into(this.imageMalam3);
            Picasso.get().load(String.valueOf(columnWeathers.get(4).getNightIconUrl())).error(R.drawable.image_not_found).into(this.imageMalam4);
            Picasso.get().load(String.valueOf(columnWeathers.get(5).getNightIconUrl())).error(R.drawable.image_not_found).into(this.imageMalam5);
        } else {
            ChatActivity.mPicasso.load(String.valueOf(columnWeathers.get(1).getNightIconUrl())).error(R.drawable.image_not_found).into(this.imageMalam1);
            ChatActivity.mPicasso.load(String.valueOf(columnWeathers.get(2).getNightIconUrl())).error(R.drawable.image_not_found).into(this.imageMalam2);
            ChatActivity.mPicasso.load(String.valueOf(columnWeathers.get(3).getNightIconUrl())).error(R.drawable.image_not_found).into(this.imageMalam3);
            ChatActivity.mPicasso.load(String.valueOf(columnWeathers.get(4).getNightIconUrl())).error(R.drawable.image_not_found).into(this.imageMalam4);
            ChatActivity.mPicasso.load(String.valueOf(columnWeathers.get(5).getNightIconUrl())).error(R.drawable.image_not_found).into(this.imageMalam5);
        }

        String txtMalam1 = columnWeathers.get(1).getMaxTemperature()+" "+columnWeathers.get(1).getMinTemperature();
        this.textMalam1.setText(txtMalam1);
        String txtMalam2 = columnWeathers.get(2).getMaxTemperature()+" "+columnWeathers.get(1).getMinTemperature();
        this.textMalam2.setText(txtMalam2);
        String txtMalam3 = columnWeathers.get(3).getMaxTemperature()+" "+columnWeathers.get(1).getMinTemperature();
        this.textMalam3.setText(txtMalam3);
        String txtMalam4 = columnWeathers.get(4).getMaxTemperature()+" "+columnWeathers.get(1).getMinTemperature();
        this.textMalam4.setText(txtMalam4);
        String txtMalam5 = columnWeathers.get(5).getMaxTemperature()+" "+columnWeathers.get(1).getMinTemperature();
        this.textMalam5.setText(txtMalam5);
    }
}

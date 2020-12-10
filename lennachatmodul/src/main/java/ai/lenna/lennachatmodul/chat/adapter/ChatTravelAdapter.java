package ai.lenna.lennachatmodul.chat.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import ai.lenna.lennachatmodul.R;
import ai.lenna.lennachatmodul.chat.ChatActivity;
import ai.lenna.lennachatmodul.chat.model.column.ChatColumnAirline;
import okhttp3.OkHttpClient;

import static ai.lenna.lennachatmodul.network.ApiBuilder.getImageHttpClient;

@Keep
public class ChatTravelAdapter extends RecyclerView.Adapter<ChatTravelAdapter.ChatCarouselItemVH> {

    ArrayList<ChatColumnAirline> data;
    ChatPostCallBack chatGridCallBack;

    public ChatTravelAdapter(ArrayList<ChatColumnAirline> data, ChatPostCallBack chatGridCallBack) {
        this.data = data;
        this.chatGridCallBack = chatGridCallBack;
    }

    @NonNull
    @Override
    public ChatCarouselItemVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_travel, viewGroup, false);
        return new ChatCarouselItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatCarouselItemVH chatCarouselItemVH, int i) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Picasso.get().load(data.get(i).getAirlineIconUrl()).error(R.drawable.imagenotfound).into(chatCarouselItemVH.imageView);
        } else {
            ChatActivity.mPicasso.load(data.get(i).getAirlineIconUrl()).error(R.drawable.imagenotfound).into(chatCarouselItemVH.imageView);
        }

        String airline = data.get(i).getAirline() +" "+data.get(i).getFlightNumber();
        chatCarouselItemVH.textViewAirline.setText(airline);
        chatCarouselItemVH.textViewDepartTime.setText(data.get(i).getShortDepartTime());
        chatCarouselItemVH.textViewDepartDate.setText(reformateDate(data.get(i).getShortDepartDate()));
        chatCarouselItemVH.textViewArriveTime.setText(data.get(i).getShortArriveTime());
        chatCarouselItemVH.textViewArriveDate.setText(reformateDate(data.get(i).getShortArriveDate()));
        chatCarouselItemVH.textViewOriginCity.setText(data.get(i).getOriginCity());
        chatCarouselItemVH.textViewAirportName.setText(data.get(i).getOriginAirport());
        chatCarouselItemVH.textViewDestinationCity.setText(data.get(i).getDestinationCity());
        chatCarouselItemVH.textViewDestinationAirport.setText(data.get(i).getDestinationAirport());
        chatCarouselItemVH.textViewActionButton.setText(data.get(i).getPrice());

        chatCarouselItemVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chatGridCallBack != null){
                    chatGridCallBack.onRowClick(data.get(i).getRawPrice());
                }
            }
        });
    }

    private String reformateDate(String dateString){
        String[] arrayString = dateString.split("-");

        int year = Integer.parseInt(arrayString[0]);
        int monthOfYear = Integer.parseInt(arrayString[1]);
        int dayOfMonth = Integer.parseInt(arrayString[2]);
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        String myFormat = "dd MMM";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        return sdf.format(calendar.getTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ChatCarouselItemVH extends RecyclerView.ViewHolder {
        TextView textViewAirline;
        TextView textViewDepartTime;
        TextView textViewDepartDate;
        TextView textViewArriveTime;
        TextView textViewArriveDate;
        TextView textViewOriginCity;
        TextView textViewAirportName;
        TextView textViewDestinationCity;
        TextView textViewDestinationAirport;
        TextView textViewActionButton;

        ImageView imageView;

        public ChatCarouselItemVH(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image_view);
            textViewAirline = (TextView) itemView.findViewById(R.id.textViewAirline);
            textViewDepartTime = (TextView) itemView.findViewById(R.id.textViewDepartTime);
            textViewDepartDate = (TextView) itemView.findViewById(R.id.textViewDepartDate);
            textViewArriveTime = (TextView) itemView.findViewById(R.id.textViewArriveTime);

            textViewArriveDate = (TextView) itemView.findViewById(R.id.textViewArriveDate);
            textViewOriginCity = (TextView) itemView.findViewById(R.id.textViewOriginCity);

            textViewAirportName = (TextView) itemView.findViewById(R.id.textViewAirportName);
            textViewDestinationCity = (TextView) itemView.findViewById(R.id.textViewDestinationCity);
            textViewDestinationAirport = (TextView) itemView.findViewById(R.id.textViewDestinationAirport);
            textViewActionButton = (TextView) itemView.findViewById(R.id.textViewActionButton);

        }
    }
}

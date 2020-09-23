package ai.lenna.lennachatmodul.chat.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ai.lenna.lennachatmodul.R;

public class QuickButtonAdapter extends RecyclerView.Adapter<QuickButtonAdapter.MyViewHolder> {


    Activity context;
    QuickButtonCallBack quickButtonCallBack;
    ArrayList<String> data = new ArrayList<>();
    int row_index = -1;

    public QuickButtonAdapter(Activity context, ArrayList<String> data, QuickButtonCallBack quickButtonCallBack) {
        this.context = context;
        this.data = data;
        this.quickButtonCallBack = quickButtonCallBack;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quick_button, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewQuickButton.setText(data.get(position));
        holder.llQuickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (quickButtonCallBack != null){
                    quickButtonCallBack.onRowClick(data.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewQuickButton;
        LinearLayout llQuickButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewQuickButton = (TextView) itemView.findViewById(R.id.textViewQuickButton);
            llQuickButton = (LinearLayout) itemView.findViewById(R.id.llQuickButton);
        }
    }
}

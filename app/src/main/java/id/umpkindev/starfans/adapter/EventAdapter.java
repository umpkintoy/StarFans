package id.umpkindev.starfans.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.umpkindev.starfans.R;

//view untuk list di recycleview event

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    private List<String> eventurl;
    private OnEventClickListener onEventClickListener;
    public void setOnEventClickListener(OnEventClickListener onEventClickListener) {
        this.onEventClickListener = onEventClickListener;
    }

    public EventAdapter(List<String> url) {
        eventurl = url;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.event_info_item, parent, false);
        return new MyViewHolder(view);
    }

    //library load url gambar ke imageview
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get()
                .load(eventurl.get(position))
                .placeholder(R.drawable.event1)
                .into(holder.eventposter);

    }

    @Override
    public int getItemCount() {
        return eventurl.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView eventposter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onEventClickListener.onEventClick("");
                }
            });
            eventposter = itemView.findViewById(R.id.event_item);
        }
    }
}

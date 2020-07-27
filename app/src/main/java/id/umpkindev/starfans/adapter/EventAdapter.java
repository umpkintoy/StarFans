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
import id.umpkindev.starfans.models.EventModel;

//view untuk list di recycleview di page event list

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    //private List<String> eventurl;
    private List<EventModel> eventModels;

    private OnEventClickListener onEventClickListener;
    public void setOnEventClickListener(OnEventClickListener onEventClickListener) {
        this.onEventClickListener = onEventClickListener;
    }

    public EventAdapter(List<EventModel> events ) {
        eventModels = events;
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
    //picasso untuk menampilkan gambar dari url ke imageview
        final EventModel eventModel = eventModels.get(position);
        Picasso.get()
                .load(eventModel.poster())
                .placeholder(R.drawable.event1)
                .into(holder.eventposter);
        holder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEventClickListener.onEventClick(eventModel.getId()); //mengirimkan id dari firestore ketika di click
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView eventposter;
        private View itemview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemview = itemView;

            eventposter = itemView.findViewById(R.id.event_item);
        }
    }
}

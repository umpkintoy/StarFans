package id.umpkindev.starfans.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.umpkindev.starfans.R;
import id.umpkindev.starfans.models.FantakenModel;

public class FantakenAdapter extends RecyclerView.Adapter<FantakenAdapter.ViewFantakenHolder> {

    private List<FantakenModel> fantakenModels;
    private OnFantakenClickListener onFantakenClickListener;

    public void setFansTakenListener(OnFantakenClickListener onFantakenClickListener) {
        this.onFantakenClickListener = onFantakenClickListener;
    }

    public FantakenAdapter(List<FantakenModel> fantakens ) {
        fantakenModels = fantakens;
    }

    @NonNull
    @Override
    public ViewFantakenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fantaken_item, parent, false);
        return new ViewFantakenHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewFantakenHolder holder, int position) {
        final FantakenModel fantakenModel = fantakenModels.get(position);
        Picasso.get()
                .load(fantakenModel.poster())
                .placeholder(R.drawable.fantaken1)
                .into(holder.fantakenpic);
        holder.fantakenmain.setText(fantakenModel.getTitle());
        holder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFantakenClickListener.onFantakenClick(fantakenModel.getId()); //mengirimkan id dari firestore ketika di click
            }
        });
    }

    @Override
    public int getItemCount() {
        return fantakenModels.size();
    }

    public class ViewFantakenHolder extends RecyclerView.ViewHolder {

        private ImageView fantakenpic;
        private TextView fantakenmain;
        private View itemview;

        public ViewFantakenHolder(@NonNull View itemView) {
            super(itemView);
            itemview = itemView;

            fantakenpic = itemView.findViewById(R.id.fantaken_item);
            fantakenmain = itemView.findViewById(R.id.fantaken_title_main);
        }
    }
}

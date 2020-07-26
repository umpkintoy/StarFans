package id.umpkindev.starfans.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.umpkindev.starfans.R;

public class FantakenAdapter extends RecyclerView.Adapter<FantakenAdapter.ViewFantakenHolder> {

    private List<String> fantakenurl;
    private OnFantakenClickListener onFantakenClickListener;
    public void setOnFantakenClickListener(OnFantakenClickListener onFantakenClickListener) {
        this.onFantakenClickListener = onFantakenClickListener;
    }

    public FantakenAdapter(List<String> url) {
        fantakenurl = url;
    }


    @NonNull
    @Override
    public FantakenAdapter.ViewFantakenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_fantaken_list, parent, false);
        return new FantakenAdapter.ViewFantakenHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FantakenAdapter.ViewFantakenHolder holder, int position) {
        Picasso.get()
                .load(fantakenurl.get(position))
                .placeholder(R.drawable.fantaken1)
                .into(holder.fantakenpic);
    }

    @Override
    public int getItemCount() {
        return fantakenurl.size();
    }

    public class ViewFantakenHolder extends RecyclerView.ViewHolder {

        private ImageView fantakenpic;

        public ViewFantakenHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OnFantakenClickListener.onFantakenClick("");
                }
            });
        }
    }
}

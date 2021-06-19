package com.example.rajaongkir.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rajaongkir.R;
import com.example.rajaongkir.dao.HistoryContact;
import com.example.rajaongkir.entity.DataFeedback;
import com.example.rajaongkir.ui.HistoryActivity;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.viewHolder>{
    Context context;
    List<DataFeedback> list;
    HistoryContact.view mView;

    public HistoryAdapter(Context context, List<DataFeedback> list, HistoryContact.view mView){
        this.context = context;
        this.list = list;
        this.mView = mView;
    }

    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_feedback,parent,false);
        return new viewHolder(view);
    }

    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final DataFeedback item = list.get(position);
        holder.tvName.setText(item.getName());
        holder.tvExpedisi.setText(item.getExpedisi());
        holder.tvFeedback.setText(item.getFeedback());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.editData(item);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mView.deleteData(item);
                return true;
            }
        });
    }

    public int getItemCount() {
        return  list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvExpedisi, tvFeedback, id;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvExpedisi = itemView.findViewById(R.id.tvExpedisi);
            tvFeedback = itemView.findViewById(R.id.tvFeedback);
            cardView = itemView.findViewById(R.id.cv_item);
        }

    }
}

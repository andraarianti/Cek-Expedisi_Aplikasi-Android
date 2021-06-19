package com.example.rajaongkir.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rajaongkir.R;
import com.example.rajaongkir.data.cost.CostsItem;

import java.util.List;

public class ExpedisiAdapter extends RecyclerView.Adapter<ExpedisiAdapter.ViewHolder> {

    List<CostsItem> items;

    public ExpedisiAdapter(List<CostsItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ExpedisiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemsRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_ongkir,parent,false);
        return new ExpedisiAdapter.ViewHolder(itemsRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpedisiAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_tipeService,tv_tipeServiceDesc,tv_estimasi,tv_harga;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_tipeService = itemView.findViewById(R.id.tvType);
            tv_tipeServiceDesc = itemView.findViewById(R.id.tvTypeDesc);
            tv_estimasi = itemView.findViewById(R.id.tvEst);
            tv_harga = itemView.findViewById(R.id.tvPrice);

        }

        public void bind(CostsItem items){
            tv_tipeService.setText(items.getService());
            tv_tipeServiceDesc.setText(items.getDescription());
            tv_estimasi.setText(items.getCost().get(0).getEtd());
            tv_harga.setText("Rp " + items.getCost().get(0).getValue());
        }
    }
}

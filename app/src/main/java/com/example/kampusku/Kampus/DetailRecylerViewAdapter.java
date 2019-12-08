package com.example.kampusku.Kampus;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kampusku.R;

import java.util.List;

public class DetailRecylerViewAdapter extends RecyclerView.Adapter<DetailRecylerViewAdapter.ViewHolder> {

    private Context context;
    private List<ResultDetail> results;

    public DetailRecylerViewAdapter(Context context,List<ResultDetail> results) {
        this.context = context;
        this.results = results;

    }
    @NonNull
    @Override
    public DetailRecylerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_detail, viewGroup, false);
        DetailRecylerViewAdapter.ViewHolder holder = new DetailRecylerViewAdapter.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailRecylerViewAdapter.ViewHolder holder, final int position) {
        ResultDetail result = results.get(position);
        holder.Prodi.setText(result.getNama_prodi());
        holder.Deskripsi.setText(result.getTentang_prodi());
        holder.Fakultas.setText(result.getNama_fakultas());
        holder.UKT.setText(String.valueOf(result.getBiaya()));

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Prodi;
        TextView Deskripsi;
        TextView Fakultas;
        TextView UKT;


        public ViewHolder(View itemView) {
            super(itemView);
            Prodi = itemView.findViewById(R.id.prodi);
            Deskripsi = itemView.findViewById(R.id.tentang_prodi);
            Fakultas = itemView.findViewById(R.id.fakultas);
            UKT = itemView.findViewById(R.id.biaya);
        }
    }
}

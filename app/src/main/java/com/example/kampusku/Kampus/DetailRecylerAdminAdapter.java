package com.example.kampusku.Kampus;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kampusku.Admin.ProdiActivity;
import com.example.kampusku.Admin.UpdateProdi;
import com.example.kampusku.R;

import java.util.List;

public class DetailRecylerAdminAdapter extends RecyclerView.Adapter<DetailRecylerAdminAdapter.ViewHolder> {

    private Context context;
    private List<ResultDetail> results;

    public DetailRecylerAdminAdapter(Context context, List<ResultDetail> results) {
        this.context = context;
        this.results = results;

    }
    @NonNull
    @Override
    public DetailRecylerAdminAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_detail, viewGroup, false);
        DetailRecylerAdminAdapter.ViewHolder holder = new DetailRecylerAdminAdapter.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailRecylerAdminAdapter.ViewHolder holder, final int position) {
        final ResultDetail result = results.get(position);
        holder.Prodi.setText(result.getNama_prodi());
        holder.Deskripsi.setText(result.getTentang_prodi());
        holder.Fakultas.setText(result.getNama_fakultas());
        holder.UKT.setText(String.valueOf(result.getBiaya()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), UpdateProdi.class);
                mIntent.putExtra("id", results.get(position).getId_prodi());
                mIntent.putExtra("id_univ", results.get(position).getId_univ());
                mIntent.putExtra("nama_prodi", results.get(position).getNama_prodi());
                mIntent.putExtra("tentang", results.get(position).getTentang_prodi());
                mIntent.putExtra("biaya",results.get(position).getBiaya());
                context.startActivity(mIntent);
            }
        });

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

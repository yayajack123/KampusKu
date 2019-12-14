package com.example.kampusku.Daftar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.kampusku.R;

import java.util.List;


public class DaftarAdapter extends RecyclerView.Adapter<DaftarAdapter.ViewHolder> {


    private Context context;
    private List<ResultDaftar> results;

    public DaftarAdapter(Context context, List<ResultDaftar> results) {
        this.context = context;
        this.results = results;

    }
    @NonNull
    @Override
    public DaftarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_daftar, viewGroup, false);
        DaftarAdapter.ViewHolder holder = new DaftarAdapter.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarAdapter.ViewHolder holder, int position) {
        final ResultDaftar result = results.get(position);
        holder.Univ.setText(result.getNama_univ());
        holder.Fee.setText(String.valueOf(result.getFee()));
        holder.Prodi.setText(result.getNama_prodi());
        holder.biaya.setText(String.valueOf(result.getBiaya()));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Univ;
        TextView Fee;
        TextView Prodi;
        TextView biaya;


        public ViewHolder(View itemView) {
            super(itemView);
            Univ = itemView.findViewById(R.id.univ);
            Fee = itemView.findViewById(R.id.fee);
            Prodi = itemView.findViewById(R.id.prodi);
            biaya = itemView.findViewById(R.id.biaya);
        }
    }
}

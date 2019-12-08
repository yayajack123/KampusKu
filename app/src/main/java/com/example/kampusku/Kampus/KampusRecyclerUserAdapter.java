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

public class KampusRecyclerUserAdapter  extends RecyclerView.Adapter<KampusRecyclerUserAdapter.ViewHolder> {

    private Context context;
    private List<ResultKampus> results;

    public KampusRecyclerUserAdapter(Context context,List<ResultKampus> results) {
        this.context = context;
        this.results = results;

    }

    @NonNull
    @Override
    public KampusRecyclerUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_kampus_user, viewGroup, false);
        KampusRecyclerUserAdapter.ViewHolder holder = new KampusRecyclerUserAdapter.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull KampusRecyclerUserAdapter.ViewHolder holder, final int position) {
        ResultKampus result = results.get(position);
        holder.textViewKategori.setText(result.getKampus());
        holder.textViewAlamat.setText(result.getAlamat());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailActivity.class);
                mIntent.putExtra("id", results.get(position).getId());
                mIntent.putExtra("nama_univ", results.get(position).getKampus());
                mIntent.putExtra("tentang", results.get(position).getTentang());
                mIntent.putExtra("lokasi", results.get(position).getAlamat());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewKategori;
        TextView textViewAlamat;


        public ViewHolder(View itemView) {
            super(itemView);
            textViewKategori = itemView.findViewById(R.id.kampus);
            textViewAlamat = itemView.findViewById(R.id.alamat);
        }
    }
}

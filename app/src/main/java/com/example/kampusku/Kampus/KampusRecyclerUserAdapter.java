package com.example.kampusku.Kampus;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
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
        context = viewGroup.getContext();
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
        RequestOptions myOptions = new RequestOptions()
                .override(100, 100);
        Glide.with(context)
                .asBitmap()
                .apply(myOptions)
                .load("https://guarded-woodland-53288.herokuapp.com/image/unud.jpg")
                .into(holder.kampus);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewKategori;
        TextView textViewAlamat;
        ImageView kampus;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewKategori = itemView.findViewById(R.id.kampus);
            textViewAlamat = itemView.findViewById(R.id.alamat);
            kampus = itemView.findViewById(R.id.gambar_kampus);
        }
    }
}

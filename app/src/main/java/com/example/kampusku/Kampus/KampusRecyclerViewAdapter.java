package com.example.kampusku.Kampus;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.kampusku.Admin.ProdiActivity;
import com.example.kampusku.R;

import java.util.List;

public class KampusRecyclerViewAdapter extends RecyclerView.Adapter<KampusRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<ResultKampus> results;

    public KampusRecyclerViewAdapter(Context context,List<ResultKampus> results) {
        this.context = context;
        this.results = results;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_kampus, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ResultKampus result = results.get(position);
        holder.textViewKategori.setText(result.getKampus());
        holder.textViewAlamat.setText(result.getAlamat());
        holder.edit_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent mIntent = new Intent(view.getContext(), UpdateKampus.class);
            mIntent.putExtra("id", results.get(position).getId());
            mIntent.putExtra("nama_univ", results.get(position).getKampus());
            mIntent.putExtra("tentang", results.get(position).getTentang());
            mIntent.putExtra("lokasi", results.get(position).getAlamat());
            view.getContext().startActivity(mIntent);
        }
    });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), ProdiActivity.class);
                mIntent.putExtra("id", results.get(position).getId());
                mIntent.putExtra("nama_univ", results.get(position).getKampus());
                view.getContext().startActivity(mIntent);
            }
        });

        RequestOptions myOptions = new RequestOptions()
                .override(100, 100);
        Glide.with(context)
                .asBitmap()
                .apply(myOptions)
                .load(result.getUrl())
                .into(holder.kampus);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewKategori;
        TextView textViewAlamat;
        ImageView edit_btn;
        ImageView kampus;


        public ViewHolder(View itemView) {
            super(itemView);
            textViewKategori = itemView.findViewById(R.id.kampus);
            textViewAlamat = itemView.findViewById(R.id.alamat);
            edit_btn = itemView.findViewById(R.id.edit);
            kampus = itemView.findViewById(R.id.gambar_kampus);
        }
    }
}

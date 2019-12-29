package com.example.kampusku.Daftar;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kampusku.ApiHelper.BaseApiHelper;
import com.example.kampusku.ApiHelper.UtilsApi;
import com.example.kampusku.R;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DaftarAdminAdapter extends RecyclerView.Adapter<DaftarAdminAdapter.ViewHolder> {


    private Context context;
    private List<ResultDaftar> results;
    int id_dafar;

    public DaftarAdminAdapter(Context context, List<ResultDaftar> results) {
        this.context = context;
        this.results = results;

    }

    @NonNull
    @Override
    public DaftarAdminAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_daftar_admin, viewGroup, false);
        DaftarAdminAdapter.ViewHolder holder = new DaftarAdminAdapter.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarAdminAdapter.ViewHolder holder, int position) {
        final ResultDaftar result = results.get(position);
        id_dafar = result.getId_daftar();
        holder.nama.setText(result.getName());
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
        TextView nama;

        public ViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            Univ = itemView.findViewById(R.id.univ);
            Fee = itemView.findViewById(R.id.fee);
            Prodi = itemView.findViewById(R.id.prodi);
            biaya = itemView.findViewById(R.id.biaya);

        }
    }
}


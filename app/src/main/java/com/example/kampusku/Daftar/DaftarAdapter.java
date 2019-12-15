package com.example.kampusku.Daftar;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kampusku.ApiHelper.BaseApiHelper;
import com.example.kampusku.ApiHelper.UtilsApi;
import com.example.kampusku.R;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DaftarAdapter extends RecyclerView.Adapter<DaftarAdapter.ViewHolder> {


    private Context context;
    private List<ResultDaftar> results;
    BaseApiHelper mApiService;
    int id_dafar;
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
        id_dafar = result.getId_daftar();
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
        ImageView mDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            Univ = itemView.findViewById(R.id.univ);
            Fee = itemView.findViewById(R.id.fee);
            Prodi = itemView.findViewById(R.id.prodi);
            biaya = itemView.findViewById(R.id.biaya);
            mDelete = itemView.findViewById(R.id.del_daftar);
            mApiService = UtilsApi.getAPIService();
            mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final SweetAlertDialog sDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
                    sDialog.setTitle("Hapus Data");
                    sDialog.setContentText("Ingin menghapus data ?");
                    sDialog.setConfirmButton("Ya", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            mApiService.DeleteDaftar(id_dafar).
                                    enqueue(new Callback<ResponseBody>() {
                                        @Override
                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            if (response.isSuccessful()){
                                                Log.i("debug", "onResponse: BERHASIL");
                                                Toast.makeText(context, "BERHASIL MENGHAPUS", Toast.LENGTH_SHORT).show();
                                                Intent mIntent = new Intent(context, Daftar.class);
                                                context.startActivity(mIntent);
                                            } else {
                                                Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
                                                Log.i("debug", "onResponse: GA BERHASIL");
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                                            Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                                            Toast.makeText(context, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            sDialog.dismissWithAnimation();
                        }
                    });
                    sDialog.setCancelButton("Tidak", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    });
                    sDialog.show();
                }
            });

        }
        }
    }


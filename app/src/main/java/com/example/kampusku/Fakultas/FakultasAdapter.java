package com.example.kampusku.Fakultas;

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
import com.example.kampusku.Daftar.Daftar;
import com.example.kampusku.Daftar.ResultDaftar;
import com.example.kampusku.Kampus.UpdateKampus;
import com.example.kampusku.R;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FakultasAdapter extends RecyclerView.Adapter<FakultasAdapter.ViewHolder> {


    private Context context;
    private List<ResultFakultas> results;
    BaseApiHelper mApiService;
    int id_fakultas;
    public FakultasAdapter(Context context, List<ResultFakultas> results) {
        this.context = context;
        this.results = results;

    }
    @NonNull
    @Override
    public FakultasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_fakultas, viewGroup, false);
        FakultasAdapter.ViewHolder holder = new FakultasAdapter.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FakultasAdapter.ViewHolder holder, final int position) {
        final ResultFakultas result = results.get(position);
        id_fakultas = result.getId();
        holder.fak.setText(result.getNama_fakultas());
        holder.mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), UpdateFakultas.class);
                mIntent.putExtra("id", results.get(position).getId());
                mIntent.putExtra("nama_fakultas", results.get(position).getNama_fakultas());
                view.getContext().startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView fak;
        ImageView mDelete;
        ImageView mEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            fak = itemView.findViewById(R.id.fakultas);
            mDelete = itemView.findViewById(R.id.delete);
            mEdit = itemView.findViewById(R.id.edit);
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
                            mApiService.DeleteFakultas(id_fakultas).
                                    enqueue(new Callback<ResponseBody>() {
                                        @Override
                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            if (response.isSuccessful()){
                                                Log.i("debug", "onResponse: BERHASIL");
                                                Toast.makeText(context, "BERHASIL MENGHAPUS", Toast.LENGTH_SHORT).show();
                                                Intent mIntent = new Intent(context, Fakultas.class);
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


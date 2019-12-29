package com.example.kampusku.Fakultas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kampusku.Admin.AdminActivity;
import com.example.kampusku.ApiHelper.BaseApiHelper;
import com.example.kampusku.ApiHelper.UtilsApi;
import com.example.kampusku.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahFakultas extends AppCompatActivity {

    public static final String URL = "https://kampusjack.000webhostapp.com/api/";
    EditText fakultas;
    Button simpan;
    ProgressDialog loading;
    BaseApiHelper mApiService;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_fakultas);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        fakultas = (EditText) findViewById(R.id.fakultas);
        simpan = (Button) findViewById(R.id.tambah);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestFakultas();
            }
        });
    }

    private void requestFakultas() {
        mApiService.requestFakultas(fakultas.getText().toString()).
                enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: BERHASIL");
                            loading.dismiss();
                            Toast.makeText(mContext, "BERHASIL MENAMBAHKAN", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(mContext, Fakultas.class));
                        } else {
                            Toast.makeText(mContext, "Gagal", Toast.LENGTH_SHORT).show();
                            Log.i("debug", "onResponse: GA BERHASIL");
                            loading.dismiss();
                        }
                    }


                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}

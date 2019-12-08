package com.example.kampusku.Kampus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kampusku.Admin.AdminActivity;
import com.example.kampusku.ApiHelper.BaseApiHelper;
import com.example.kampusku.ApiHelper.UtilsApi;
import com.example.kampusku.R;

import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateKampus extends AppCompatActivity {

    EditText edtNama, edtTentang, edtLokasi;
    Button btUpdate, btDelete;
    Context mContext;
    BaseApiHelper mApiService;
    int intValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kampus);

        edtNama = (EditText) findViewById(R.id.update_nama);
        edtTentang = (EditText) findViewById(R.id.update_tentang);
        edtLokasi = (EditText) findViewById(R.id.update_lokasi);
        Intent mIntent = getIntent();
        intValue = mIntent.getIntExtra("id", 0);
        String test = String.valueOf(intValue);
        Log.d("jaja", "onCreate: "+test);
        edtNama.setText(mIntent.getStringExtra("nama_univ"));
        edtTentang.setText(mIntent.getStringExtra("tentang"));
        edtLokasi.setText(mIntent.getStringExtra("lokasi"));
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        initComponents();
    }

    private void initComponents() {
        btUpdate = (Button) findViewById(R.id.update_kampus);
        btDelete = (Button) findViewById(R.id.hapus_kampus);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateKampus();
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteKampus();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(getBaseContext(), AdminActivity.class));
                finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void updateKampus(){
        mApiService.kampusUpdate(intValue,edtNama.getText().toString(),
                edtTentang.getText().toString(),
                edtLokasi.getText().toString()).
                enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: BERHASIL");
                            Toast.makeText(mContext, "BERHASIL UPDATE", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(mContext, AdminActivity.class));
                        } else {
                            Toast.makeText(mContext, "Gagal", Toast.LENGTH_SHORT).show();
                            Log.i("debug", "onResponse: GA BERHASIL");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void deleteKampus(){
        mApiService.kampusDelete(intValue).
                enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: BERHASIL");
                            Toast.makeText(mContext, "BERHASIL MENGHAPUS", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(mContext, AdminActivity.class));
                        } else {
                            Toast.makeText(mContext, "Gagal", Toast.LENGTH_SHORT).show();
                            Log.i("debug", "onResponse: GA BERHASIL");
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

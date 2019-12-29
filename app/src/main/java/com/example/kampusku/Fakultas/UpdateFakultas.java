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
import android.widget.Toast;

import com.example.kampusku.Admin.AdminActivity;
import com.example.kampusku.ApiHelper.BaseApiHelper;
import com.example.kampusku.ApiHelper.UtilsApi;
import com.example.kampusku.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateFakultas extends AppCompatActivity {


    EditText fakultas;
    Button btUpdate;
    Context mContext;
    ProgressDialog loading;
    BaseApiHelper mApiService;
    int intValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_fakultas);

        fakultas = (EditText) findViewById(R.id.fakultas);
        btUpdate = (Button) findViewById(R.id.update);
        Intent mIntent = getIntent();
        intValue = mIntent.getIntExtra("id", 0);
        String test = String.valueOf(intValue);
        Log.d("jaja", "onCreate: "+test);
        fakultas.setText(mIntent.getStringExtra("nama_fakultas"));
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                uptFakultas();
            }
        });
    }

    private void uptFakultas() {
        mApiService.updateFakultas(intValue,fakultas.getText().toString()).
                enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: BERHASIL");
                            Toast.makeText(mContext, "BERHASIL UPDATE", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(mContext, Fakultas.class));
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

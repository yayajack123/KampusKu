package com.example.kampusku.Admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kampusku.ApiHelper.BaseApiHelper;
import com.example.kampusku.ApiHelper.UtilsApi;
import com.example.kampusku.Fakultas.GetFakultas;
import com.example.kampusku.Fakultas.ResultFakultas;
import com.example.kampusku.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TambahProdi extends AppCompatActivity {

    public static final String URL = "https://guarded-woodland-53288.herokuapp.com/api/";
    List<String> sIds = new ArrayList<String>();
    EditText nProdi,tTentang,biaya;
    Spinner fakultas;
    Button simpan;
    ProgressDialog loading;
    BaseApiHelper mApiService;
    Context mContext;
    int id_univ;
    int id_fakultas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_prodi);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        nProdi = (EditText) findViewById(R.id.prodi);
        tTentang = (EditText) findViewById(R.id.tentang);
        biaya = (EditText) findViewById(R.id.biaya);
        fakultas = (Spinner) findViewById(R.id.spiner_fakultas);
        simpan = (Button) findViewById(R.id.simpan_prodi);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestProdi();
            }
        });
        Intent mIntent = getIntent();
        id_univ = mIntent.getIntExtra("id", 0);

        initSpinnerFakultas();

        fakultas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                id_fakultas = fakultas.getSelectedItemPosition() + 1;
                Toast.makeText(getBaseContext(), "Kamu memilih fakultas dengan id " + id_fakultas, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void requestProdi() {
        mApiService.InsertProdi(nProdi.getText().toString(),
                tTentang.getText().toString(), Integer.parseInt(biaya.getText().toString()),
                id_fakultas,id_univ).
                enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: BERHASIL");
                            loading.dismiss();
                            Toast.makeText(mContext, "BERHASIL REGISTRASI", Toast.LENGTH_SHORT).show();

                            Intent mIntent = new Intent(getBaseContext(), ProdiActivity.class);
                            mIntent.putExtra("id", id_univ);
                            startActivity(mIntent);
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

    private void initSpinnerFakultas() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseApiHelper api = retrofit.create(BaseApiHelper.class);
        Call<GetFakultas> call = api.getFakultas();
        Log.e("PROGRESSSS", "SUDAH SAMPAI SINI");
        call.enqueue(new Callback<GetFakultas>() {
            @Override
            public void onResponse(Call<GetFakultas> call, Response<GetFakultas> response) {
                if (response.isSuccessful()) {
                List<ResultFakultas> fakultasitem = response.body().getResult();
                    Log.e("masuk", "onResponse: 11");
                List<String> listSpinner = new ArrayList<String>();
                for (int i = 0; i < fakultasitem.size(); i++){
                    listSpinner.add(fakultasitem.get(i).getNama_fakultas());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                        android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                fakultas.setAdapter(adapter);
            } else {
                Toast.makeText(getBaseContext(), "Gagal mengambil data fakultas", Toast.LENGTH_SHORT).show();
            }

            }

            @Override
            public void onFailure(Call<GetFakultas> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

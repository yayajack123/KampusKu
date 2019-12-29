package com.example.kampusku.Fakultas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.kampusku.Admin.Dashboard;
import com.example.kampusku.ApiHelper.BaseApiHelper;
import com.example.kampusku.ApiHelper.UtilsApi;
import com.example.kampusku.BottomActivity;
import com.example.kampusku.Daftar.Daftar;
import com.example.kampusku.Daftar.DaftarAdapter;
import com.example.kampusku.Daftar.GetDaftar;
import com.example.kampusku.Daftar.ResultDaftar;
import com.example.kampusku.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.kampusku.Kampus.DetailActivity.URL;

public class Fakultas extends AppCompatActivity {

    private List<ResultFakultas> results = new ArrayList<>();
    RecyclerView recyclerView;
    BaseApiHelper mApiService;
    FakultasAdapter viewAdapter;
    FloatingActionButton add;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fakultas);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_fakultas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Fakultas Info");
        mApiService = UtilsApi.getAPIService();
        viewAdapter = new FakultasAdapter(this, results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);

        add = (FloatingActionButton) findViewById(R.id.tambah);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Fakultas.this,TambahFakultas.class);
                startActivity(i);
            }
        });
        Log.e("m", "mantap");
        loadDataFak();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(getBaseContext(), Dashboard.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadDataFak() {
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
                Log.e("PROGRESSSS", "SUDAH SAMPAI SINI2");
                results = response.body().getResult();
                Log.e("anjay", "onResponse: "+results );
                Log.e("ERROR", "asa" + results.size());
                viewAdapter = new FakultasAdapter(Fakultas.this, results);
                recyclerView.setAdapter(viewAdapter);
            }

            @Override
            public void onFailure(Call<GetFakultas> call, Throwable t) {

            }
        });
    }
}

package com.example.kampusku.Admin;

import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.kampusku.ApiHelper.BaseApiHelper;
import com.example.kampusku.ApiHelper.UtilsApi;
import com.example.kampusku.Kampus.DetailRecylerAdminAdapter;
import com.example.kampusku.Kampus.GetDetail;
import com.example.kampusku.Kampus.ResultDetail;
import com.example.kampusku.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProdiActivity extends AppCompatActivity {

    public static final String URL = "https://kampusjack.000webhostapp.com/api/";
    private List<ResultDetail> results = new ArrayList<>();
    private DetailRecylerAdminAdapter viewAdapter;
    BaseApiHelper mApiService;
    FloatingActionButton tambah_prodi;
    private static String EXTRA = "extra";
    RecyclerView recyclerView;
    TextView edtTentang, edtLokasi;
    int intValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodi);
        tambah_prodi = (FloatingActionButton) findViewById(R.id.test1);
        tambah_prodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getBaseContext(), TambahProdi.class);
                mIntent.putExtra("id", intValue);
                startActivity(mIntent);
            }
        });
        Intent mIntent = getIntent();
        intValue = mIntent.getIntExtra("id", 0);
        String gas = mIntent.getStringExtra("nama_univ");
        String test = String.valueOf(intValue);
        Log.d("jaja", "onCreate: "+test);
        recyclerView = (RecyclerView) findViewById(R.id.rv_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(gas);
        mApiService = UtilsApi.getAPIService();
        viewAdapter = new DetailRecylerAdminAdapter(this, results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);
        Log.e("m", "mantap");
        loadDataDetail();
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

    private void loadDataDetail() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseApiHelper api = retrofit.create(BaseApiHelper.class);
        Call<GetDetail> call = api.getDetail(intValue);
        Log.e("PROGRESSSS", "SUDAH SAMPAI SINI");
        call.enqueue(new Callback<GetDetail>() {
            @Override
            public void onResponse(Call<GetDetail> call, Response<GetDetail> response) {
                Log.e("PROGRESSSS", "SUDAH SAMPAI SINI2");
                String value = response.body().getStatus();
                results = response.body().getResult();
                Log.e("anjay", "onResponse: "+results );
                Log.e("ERROR", "asa" + results.size());
                viewAdapter = new DetailRecylerAdminAdapter(ProdiActivity.this, results);
                recyclerView.setAdapter(viewAdapter);
            }

            @Override
            public void onFailure(Call<GetDetail> call, Throwable t) {

            }
        });
    }
}

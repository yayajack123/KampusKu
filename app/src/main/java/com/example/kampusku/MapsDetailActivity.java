package com.example.kampusku;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kampusku.Admin.AdminActivity;
import com.example.kampusku.ApiHelper.BaseApiHelper;
import com.example.kampusku.ApiHelper.UtilsApi;
import com.example.kampusku.Kampus.GetKampus;
import com.example.kampusku.Kampus.KampusRecyclerUserAdapter;
import com.example.kampusku.Kampus.KampusRecyclerViewAdapter;
import com.example.kampusku.Kampus.ResultKampus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsDetailActivity extends AppCompatActivity {

    private String city;
    private String kata;
    public static final String URL = "https://kampusjack.000webhostapp.com/api/";
    private List<ResultKampus> results = new ArrayList<>();
    private KampusRecyclerUserAdapter viewAdapter;
    BaseApiHelper mApiService;
    private static String EXTRA = "extra";
    String nama_univ = "";
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_detail);
        city = getIntent().getStringExtra("City");

        TextView kota = (TextView) findViewById(R.id.city_id);
        kota.setText(city);

        String mystring = city;
        String[] arr = mystring.split(" ", 0);

        String firstWord = arr[0];
        kata = firstWord;
        Log.d("kota", "first word : "+firstWord);

        Log.d("logmapsdetail","CITYDETAIL :"+city);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_search);
        mApiService = UtilsApi.getAPIService();
        viewAdapter = new KampusRecyclerUserAdapter(this, results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);
        Log.e("m", "mantap");
        loadDataKategori();
    }

    private void loadDataKategori() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BaseApiHelper api = retrofit.create(BaseApiHelper.class);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        Call<GetKampus> call = api.SearchKampus(kata);
        Log.e("PROGRESSSS", "SUDAH SAMPAI SINI");
        call.enqueue(new Callback<GetKampus>() {
            @Override
            public void onResponse(Call<GetKampus> call, Response<GetKampus> response) {
                Log.e("PROGRESSSS", "SUDAH SAMPAI SINI2");
                progressBar.setVisibility(View.GONE);
                String value = response.body().getStatus();
                results = response.body().getResult();
                Log.e("ERROR", "asa" + results.size());
                viewAdapter = new KampusRecyclerUserAdapter(MapsDetailActivity.this, results);
                recyclerView.setAdapter(viewAdapter);

            }

            @Override
            public void onFailure(Call<GetKampus> call, Throwable t) {
                Log.e("ERROR", "GOBLOk" );
            }
        });
    }
}

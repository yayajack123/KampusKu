package com.example.kampusku.Admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.kampusku.MyFirebaseMessagingService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.kampusku.ApiHelper.BaseApiHelper;
import com.example.kampusku.ApiHelper.UtilsApi;
import com.example.kampusku.Kampus.GetKampus;
import com.example.kampusku.Kampus.KampusRecyclerViewAdapter;
import com.example.kampusku.Kampus.ResultKampus;
import com.example.kampusku.Kampus.TambahKampus;
import com.example.kampusku.R;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminActivity extends AppCompatActivity {

    public static final String URL = "https://kampusjack.000webhostapp.com/api/";
    private List<ResultKampus> results = new ArrayList<>();
    private KampusRecyclerViewAdapter viewAdapter;
    BaseApiHelper mApiService;
    private static String EXTRA = "extra";
    String nama_univ = "";
    RecyclerView recyclerView;
    ProgressBar progressBar;
    FloatingActionButton gaskan;
    NestedScrollView test;
    SharedPreferences sharedPreferences;
    boolean session = false;
    Button btnLogout;
    String token;
    Integer id_user;
    Integer admin;
    final String SHARED_PREFERENCES_NAME = "shared_preferences";
    final String SESSION_STATUS = "session";
    public final static String TAG_TOKEN = "token";
    public final static Integer TAG_ID = 0;
    public final static Integer TAG_ADMIN = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        gaskan = (FloatingActionButton) findViewById(R.id.tambah);
        gaskan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), TambahKampus.class));
                finish();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.rv_admin);
        mApiService = UtilsApi.getAPIService();
        viewAdapter = new KampusRecyclerViewAdapter(this, results);
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
        Call<GetKampus> call = api.getKampus();
        Log.e("PROGRESSSS", "SUDAH SAMPAI SINI");
        call.enqueue(new Callback<GetKampus>() {
            @Override
            public void onResponse(Call<GetKampus> call, Response<GetKampus> response) {
                Log.e("PROGRESSSS", "SUDAH SAMPAI SINI2");
                progressBar.setVisibility(View.GONE);
                String value = response.body().getStatus();
                results = response.body().getResult();
                Log.e("ERROR", "asa" + results.size());
                viewAdapter = new KampusRecyclerViewAdapter(AdminActivity.this, results);
                recyclerView.setAdapter(viewAdapter);

            }

            @Override
            public void onFailure(Call<GetKampus> call, Throwable t) {
                Log.e("ERROR", "GOBLOk" );
            }
        });
    }
}

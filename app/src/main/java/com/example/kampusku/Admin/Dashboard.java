package com.example.kampusku.Admin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kampusku.ApiHelper.BaseApiHelper;
import com.example.kampusku.ApiHelper.UtilsApi;
import com.example.kampusku.Fakultas.Fakultas;
import com.example.kampusku.Image.GambarKampus;
import com.example.kampusku.Kampus.KampusRecyclerViewAdapter;
import com.example.kampusku.Kampus.ResultKampus;
import com.example.kampusku.Kampus.TambahKampus;
import com.example.kampusku.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    FloatingActionButton gaskan;
    SharedPreferences sharedPreferences;
    boolean session = false;
    Button btnLogout;
    String token;
    Integer id_user;
    Integer admin;
    CardView kampus,fakultas,gambar,daftar;
    final String SHARED_PREFERENCES_NAME = "shared_preferences";
    final String SESSION_STATUS = "session";
    public final static String TAG_TOKEN = "token";
    public final static Integer TAG_ID = 0;
    public final static Integer TAG_ADMIN = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
        sharedPreferences = this.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(SESSION_STATUS, false);
        token = sharedPreferences.getString(TAG_TOKEN, null);
        id_user = sharedPreferences.getInt(String.valueOf(TAG_ID),0);
        admin = sharedPreferences.getInt(String.valueOf(TAG_ADMIN),0);
        Log.d("id user", "id: "+id_user);
        btnLogout = (Button) findViewById(R.id.logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Menghapus Status login dan kembali ke Login Activity
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(SESSION_STATUS);
                editor.remove(TAG_TOKEN);
                editor.remove(String.valueOf(TAG_ID));
                editor.remove(String.valueOf(TAG_ADMIN));
                editor.apply();
                startActivity(new Intent(getBaseContext(), AdminLoginActivity.class));
                finish();
            }
        });

        kampus = (CardView) findViewById(R.id.kampus_card);
        kampus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), AdminActivity.class));
            }
        });

        gambar = (CardView) findViewById(R.id.gambar_card);
        gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), GambarKampus.class));
            }
        });

        daftar = (CardView) findViewById(R.id.list_card);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Pendaftar.class));
            }
        });

        fakultas = (CardView) findViewById(R.id.fakultas_card);
        fakultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Fakultas.class));
            }
        });


    }
}

package com.example.kampusku;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kampusku.Admin.AdminActivity;
import com.example.kampusku.Admin.AdminLoginActivity;
import com.example.kampusku.ApiHelper.BaseApiHelper;
import com.example.kampusku.ApiHelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.Types.NULL;

public class LoginActivity extends AppCompatActivity {
    TextView regis,to_admin;
    EditText etEmail;
    EditText etPassword;
    CircularProgressButton btnLogin;
    ProgressDialog loading;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences2;
    Context mContext;
    BaseApiHelper mApiService;
    boolean session = false;
    TokenManager tokenManager;
    String token;
    Integer id_user;
    Integer admin;
    final String SHARED_PREFERENCES_NAME = "shared_preferences";
    final String SHARED_PREFERENCES_test = "shared_preferences";
    final String SESSION_STATUS = "session";
    public final static String TAG_TOKEN = "token";
    public final static Integer TAG_ID = 0;
    public final static Integer TAG_ADMIN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mContext = this;
        mApiService = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(SESSION_STATUS, false);
        token = sharedPreferences.getString(TAG_TOKEN, null);
        id_user = sharedPreferences.getInt(String.valueOf(TAG_ID),0);
        admin = sharedPreferences.getInt(String.valueOf(TAG_ADMIN),0);
        Log.d("asasa", "onCreate: admin  "+admin);
        if (admin==1){
            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
            intent.putExtra(TAG_TOKEN, token);
            intent.putExtra(String.valueOf(TAG_ID),id_user);
            intent.putExtra(String.valueOf(TAG_ADMIN),admin);
            finish();
            startActivity(intent);
        }
        if(id_user!=NULL){
            Intent intent = new Intent(LoginActivity.this, BottomActivity.class);
            intent.putExtra(TAG_TOKEN, token);
            intent.putExtra(String.valueOf(TAG_ID),id_user);
            intent.putExtra(String.valueOf(TAG_ADMIN),admin);
            finish();
            startActivity(intent);
        }


        initComponents();
    }


    private void initComponents() {
        etEmail = (EditText) findViewById(R.id.iemail);
        etPassword = (EditText) findViewById(R.id.ipassword);
        btnLogin = (CircularProgressButton) findViewById(R.id.blogin);
        regis = (TextView) findViewById(R.id.regis);
        to_admin = (TextView) findViewById(R.id.pindah);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                Login();
            }
        });

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, RegisterActivity.class));
            }
        });

        to_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, AdminLoginActivity.class));
            }
        });
    }


    private void Login() {
        mApiService.loginRequest(etEmail.getText().toString(), etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("status").equals("success")) {
                                    // Jika login berhasil maka data nama yang ada di response API
                                    // akan diparsing ke activity selanjutnya.

                                    String sukses = jsonRESULTS.getJSONObject("data").getString("token");
                                    Log.d("wanjay", "onResponse: "+sukses);
                                    String name = jsonRESULTS.getJSONObject("user").getString("name");
                                    Integer id = jsonRESULTS.getJSONObject("user").getInt("id");
                                    Log.d("id_user", "onResponse: "+id);
                                    Integer is_admin = jsonRESULTS.getJSONObject("user").getInt("is_admin");
                                    Log.d("admin", "onResponse: "+is_admin);
                                    token = sukses;
                                    if (id!=NULL){
                                        admin = is_admin;
                                        Log.d("admin", "onanjay "+admin);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putBoolean(SESSION_STATUS, true);
                                        editor.putString(TAG_TOKEN, token);
                                        editor.putInt(String.valueOf(TAG_ADMIN),admin);
                                        editor.putInt(String.valueOf(TAG_ID), jsonRESULTS.getJSONObject("user").getInt("id"));
                                        editor.apply();
                                        Toast.makeText(mContext, "ID ANDA " + id, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(mContext, BottomActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Toast.makeText(mContext, "Mungkin anda admin" , Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    // Jika login gagal
                                    Toast.makeText(mContext, "EEROOR", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }

                });
    }

}

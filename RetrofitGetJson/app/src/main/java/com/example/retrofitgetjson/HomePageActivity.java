package com.example.retrofitgetjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomePageActivity extends AppCompatActivity {

    ProgressBar progressBar;
    private static final String BASE_URL = "http://app.ar.co.th/";
    private TextView txId, tvName, tvRole;
    private TextView txtShowModule, txtShowTraget, txtShowUsername, txtShowPass;
    String getModule, getTraget, getUsername, getPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        progressBar = findViewById(R.id.progressBar);
        txId = findViewById(R.id.txId);
        tvName = findViewById(R.id.tvName);
        tvRole = findViewById(R.id.tvRole);

        txtShowModule = findViewById(R.id.txtShowModule);
        txtShowTraget = findViewById(R.id.txtShowTraget);
        txtShowUsername = findViewById(R.id.txtShowUsername);
        txtShowPass = findViewById(R.id.txtShowPass);

        getModule = getIntent().getStringExtra("Module");
        getTraget = getIntent().getStringExtra("Traget");
        getUsername = getIntent().getStringExtra("Username");
        getPassword = getIntent().getStringExtra("Password");

        txtShowModule.setText(getModule);
        txtShowTraget.setText(getTraget);
        txtShowUsername.setText(getUsername);
        txtShowPass.setText(getPassword);

        GetJsonResponse();
    }

    private void  GetJsonResponse() {
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface requestApiIntf = retrofit.create(ApiInterface.class);
        Call<GetApi> call = requestApiIntf.getPost();

        call.enqueue(new Callback<GetApi>() {
            @Override
            public void onResponse(Call<GetApi> call, Response<GetApi> response) {
                if (response.isSuccessful()) {
                    txId.setText("Unique ID: " + response.body().getEntries().getUser().getUniqueID());
                    tvName.setText("Name: " + response.body().getEntries().getUser().getName());
                    tvRole.setText("Role: " + response.body().getEntries().getUser().getRole());
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<GetApi> call, Throwable t) {

            }
        });
    }
}
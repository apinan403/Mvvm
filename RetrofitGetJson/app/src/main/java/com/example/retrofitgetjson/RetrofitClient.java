package com.example.retrofitgetjson;

import com.example.retrofitgetjson.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

//    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static final String BASE_URL = "http://app.ar.co.th/";
    private static Retrofit retrofit = null;

    public  static ApiInterface getRetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }
}

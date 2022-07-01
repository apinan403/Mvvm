package com.example.retrofitgetjson;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("AppStoreSystem/files/project/arra_new/1_Authentication/ASaleLogin/login.json")
    Call<GetApi> getPost();

    @POST("posts")
    Call<Post> createUser(@Body Post post);
}

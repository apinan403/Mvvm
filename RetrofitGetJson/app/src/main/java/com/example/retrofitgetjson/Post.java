package com.example.retrofitgetjson;

import android.service.autofill.UserData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    private String module;

    private String traget;

    private UserData data;

    public Post(String module, String traget) {
        this.module = module;
        this.traget = traget;
    }

    public String getModule() { return module; }

    public String getTraget() { return traget; }

}

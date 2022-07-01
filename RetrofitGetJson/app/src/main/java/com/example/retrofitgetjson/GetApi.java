package com.example.retrofitgetjson;

import android.service.autofill.UserData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class GetApi {

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("entries")
    @Expose
    private Entries entries;

    public GetApi(Integer status, String message, Entries entries) {
        this.status = status;
        this.message = message;
        this.entries = entries;
    }

    public Integer getStatus() { return status; }

    public String getMessage() { return message; }

    public Entries getEntries() { return entries; }

}


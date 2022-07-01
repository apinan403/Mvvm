package com.example.retrofitgetjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("uniqueID")
    @Expose
    private Integer uniqueID;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("role")
    @Expose
    private String role;

    public User(Integer uniqueID, String name, String role) {
        this.uniqueID = uniqueID;
        this.name = name;
        this.role = role;
    }

    public Integer getUniqueID() {
        return uniqueID;
    }

    public String getName() { return name; }

    public String getRole() {
        return role;
    }

 }

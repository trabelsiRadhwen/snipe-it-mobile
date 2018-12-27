package com.example.radhwen.snipeit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("rows")
    @Expose
    private List<UserRows> userRows;

    public int getTotal() {
        return total;
    }

    public List<UserRows> getUserRows() {
        return userRows;
    }
}

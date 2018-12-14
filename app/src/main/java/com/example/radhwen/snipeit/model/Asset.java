package com.example.radhwen.snipeit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Asset {

    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("rows")
    @Expose
    private List<Rows> rows;

    public int getTotal() {
        return total;
    }

    public List<Rows> getRows() {
        return rows;
    }
}

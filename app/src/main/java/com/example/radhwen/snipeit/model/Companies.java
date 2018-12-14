package com.example.radhwen.snipeit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Companies {

    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("rows")
    @Expose
    private List<CompanieRows> companieRows;

    public int getTotal() {
        return total;
    }

    public List<CompanieRows> getCompanieRows() {
        return companieRows;
    }
}

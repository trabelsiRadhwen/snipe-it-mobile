package com.example.radhwen.snipeit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Model implements Serializable {

    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("rows")
    @Expose
    private List<ModelRows> modelRows;

    public Model() {
    }

    public Model(int total, List<ModelRows> modelRows) {
        super();
        this.total = total;
        this.modelRows = modelRows;
    }

    public int getTotal() {
        return total;
    }

    public List<ModelRows> getModelRows() {
        return modelRows;
    }
}

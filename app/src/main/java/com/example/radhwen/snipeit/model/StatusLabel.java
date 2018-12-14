package com.example.radhwen.snipeit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class StatusLabel implements Serializable {

    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("rows")
    @Expose
    private List<StatusLabelRows> statusLabelRows;

    public int getTotal() {
        return total;
    }

    public List<StatusLabelRows> getStatusLabelRows() {
        return statusLabelRows;
    }
}

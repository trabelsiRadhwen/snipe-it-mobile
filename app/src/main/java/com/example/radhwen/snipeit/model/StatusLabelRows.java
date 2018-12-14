package com.example.radhwen.snipeit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StatusLabelRows implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("status_type")
    @Expose
    private String statusType;

    @SerializedName("status_meta")
    @Expose
    private String statusMeta;

    public StatusLabelRows() {
    }

    public StatusLabelRows(int id, String name, String statusType, String statusMeta) {
        super();
        this.id = id;
        this.name = name;
        this.statusType = statusType;
        this.statusMeta = statusMeta;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatusType() {
        return statusType;
    }

    public String getStatusMeta() {
        return statusMeta;
    }

    public String toString() {
        return name;
    }
}

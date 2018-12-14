package com.example.radhwen.snipeit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompanieRows implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("assets_count")
    @Expose
    private Integer assetsCount;

    @SerializedName("licences_count")
    @Expose
    private Integer licencesCount;

    public CompanieRows(String name) {
        this.name = name;
    }

    public CompanieRows(int id, String name, Integer assetsCount, Integer licencesCount) {
        this.id = id;
        this.name = name;
        this.assetsCount = assetsCount;
        this.licencesCount = licencesCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAssetsCount() {
        return assetsCount;
    }

    public Integer getLicencesCount() {
        return licencesCount;
    }

    public String toString() {
        return name;
    }
}

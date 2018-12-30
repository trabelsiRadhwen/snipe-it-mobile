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

    @SerializedName("licenses_count")
    @Expose
    private Integer licencesCount;

    @SerializedName("accessories_count")
    @Expose
    private Integer accessoriesCount;

    public CompanieRows(String name) {
        this.name = name;
    }

    public CompanieRows(Integer id, String name, Integer assetsCount, Integer licencesCount) {
        this.id = id;
        this.name = name;
        this.assetsCount = assetsCount;
        this.licencesCount = licencesCount;
    }

    public Integer getId() {
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

    public void setAssetsCount(Integer assetsCount) {
        this.assetsCount = assetsCount;
    }

    public void setLicencesCount(Integer licencesCount) {
        this.licencesCount = licencesCount;
    }

    public Integer getAccessoriesCount() {
        return accessoriesCount;
    }

    public void setAccessoriesCount(Integer accessoriesCount) {
        this.accessoriesCount = accessoriesCount;
    }

    public String toString() {
        return name;
    }
}

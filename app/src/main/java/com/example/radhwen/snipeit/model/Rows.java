package com.example.radhwen.snipeit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Rows {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("image")
    @Expose
    private String image;

    private int mImageResourceId = NO_IMAGE_PROVIDER;

    private static final int NO_IMAGE_PROVIDER = -1;

    @SerializedName("asset_tag")
    @Expose
    private String tag;

    @SerializedName("model")
    @Expose
    private ModelRows model;

    @SerializedName("category")
    @Expose
    private CategoryRows category;

    @SerializedName("company")
    @Expose
    private CompanieRows company;

    @SerializedName("status_label")
    @Expose
    private StatusLabelRows statusLabel;


    public Rows(String name, ModelRows model, CompanieRows company, StatusLabelRows statusLabel) {
        this.name = name;
        this.model = model;
        this.company = company;
        this.statusLabel = statusLabel;
    }

    public Rows(Integer id, String name, String image, String tag, ModelRows model, CategoryRows category, CompanieRows company, StatusLabelRows statusLabel) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.tag = tag;
        this.model = model;
        this.category = category;
        this.company = company;
        this.statusLabel = statusLabel;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public ModelRows getModel() {
        return model;
    }

    public CategoryRows getCategory() {
        return category;
    }

    public StatusLabelRows getStatusLabel() {
        return statusLabel;
    }

    public CompanieRows getCompany() {
        return company;
    }

    public String getImage() {
        return image;
    }

    public boolean hasImage()
    {
        return mImageResourceId != NO_IMAGE_PROVIDER;
    }
}

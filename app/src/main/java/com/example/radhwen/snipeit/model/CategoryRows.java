package com.example.radhwen.snipeit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CategoryRows implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("category_type")
    @Expose
    private String categoryType;

    public CategoryRows() {
    }

    public CategoryRows(int id, String name, String categoryType) {
        super();
        this.id = id;
        this.name = name;
        this.categoryType = categoryType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public String toString() {
        return name;
    }
}

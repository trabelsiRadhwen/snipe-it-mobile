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

    @SerializedName("asset_tag")
    @Expose
    private String tag;

    @SerializedName("serial")
    @Expose
    private String serial;

    @SerializedName("notes")
    @Expose
    private String notes;

    @SerializedName("order_number")
    @Expose
    private String orderNumber;

    @SerializedName("warranty_months")
    @Expose
    private String warrantyMonths;

    @SerializedName("purchase_cost")
    @Expose
    private String purchaseCost;

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

    public Rows(String name, String serial, String notes, String orderNumber, String purchaseCost, ModelRows model, CategoryRows category, CompanieRows company, StatusLabelRows statusLabel) {
        this.name = name;
        this.serial = serial;
        this.notes = notes;
        this.orderNumber = orderNumber;
        this.purchaseCost = purchaseCost;
        this.model = model;
        this.category = category;
        this.company = company;
        this.statusLabel = statusLabel;
    }

    public Rows(String name, String tag, String serial, String notes, String orderNumber, String warrantyMonths, String purchaseCost, ModelRows model, CategoryRows category, CompanieRows company, StatusLabelRows statusLabel) {
        this.name = name;
        this.tag = tag;
        this.serial = serial;
        this.notes = notes;
        this.orderNumber = orderNumber;
        this.warrantyMonths = warrantyMonths;
        this.purchaseCost = purchaseCost;
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

    public String getSerial() {
        return serial;
    }

    public String getNotes() {
        return notes;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getWarrantyMonths() {
        return warrantyMonths;
    }

    public String getPurchaseCost() {
        return purchaseCost;
    }

    public String getImage() {
        return image;
    }

}

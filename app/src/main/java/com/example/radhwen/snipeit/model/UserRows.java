package com.example.radhwen.snipeit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserRows implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("avatar")
    @Expose
    private String image;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("employee_num")
    @Expose
    private String employeeNumber;

    @SerializedName("jobtitle")
    @Expose
    private String jobTitle;

    @SerializedName("company")
    @Expose
    private CompanieRows company;

    @SerializedName("rows")
    @Expose
    private List<Rows> rows;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public CompanieRows getCompany() {
        return company;
    }

    public List<Rows> getRows() {
        return rows;
    }
}

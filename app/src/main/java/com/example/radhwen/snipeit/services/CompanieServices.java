package com.example.radhwen.snipeit.services;

import com.example.radhwen.snipeit.model.CompanieRows;
import com.example.radhwen.snipeit.model.Companies;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CompanieServices {

    @GET("companies")
    Call<Companies> getCompanies(@Header("Authorization") String api_key );

    @POST("companies")
    Call<CompanieRows> addCompany(@Header("Authorization") String api_key,
                                  @Query("name") String name);

    @FormUrlEncoded
    @PUT("companies/{id}")
    Call<CompanieRows> updateCompany(@Header("Authorization") String api_key,
                                     @Path("id") int id,
                                     @Field("name") String name);
}

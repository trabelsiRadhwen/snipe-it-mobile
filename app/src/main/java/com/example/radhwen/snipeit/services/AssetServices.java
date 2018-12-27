package com.example.radhwen.snipeit.services;

import com.example.radhwen.snipeit.model.Asset;
import com.example.radhwen.snipeit.model.CompanieRows;
import com.example.radhwen.snipeit.model.ModelRows;
import com.example.radhwen.snipeit.model.Rows;
import com.example.radhwen.snipeit.model.StatusLabelRows;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AssetServices {

    @GET("hardware")
    Call<Asset> getAssets(@Header("Authorization") String api_key);

    //@FormUrlEncoded
    @POST("hardware")
    Call<Rows> createAsset(@Header("Authorization") String api_key,
                              @Query("name") String name,
                              //@Query("asset_tag") String tag,
                              @Query("model_id") int model,
                              @Query("company_id") int company,
                              @Query("status_id") int status);

    @FormUrlEncoded
    @PUT("hardware/{id}")
    Call<Rows> updateAsset(@Header("Authorization") String api_key,
                           @Path("id") int id,
                           @Field("name") String name,
                           @Field("model_id") int model,
                           @Field("company_id") int company,
                           @Field("status_id") int status);

    @DELETE("hardware/{id}")
    Call<Rows> deleteAsset(@Header("Authorization") String api_key,
                           @Path("id") int id);
}
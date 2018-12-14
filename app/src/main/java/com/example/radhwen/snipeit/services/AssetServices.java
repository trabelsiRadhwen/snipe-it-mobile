package com.example.radhwen.snipeit.services;

import com.example.radhwen.snipeit.model.Asset;
import com.example.radhwen.snipeit.model.CompanieRows;
import com.example.radhwen.snipeit.model.ModelRows;
import com.example.radhwen.snipeit.model.Rows;
import com.example.radhwen.snipeit.model.StatusLabelRows;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AssetServices {

    @GET("hardware")
    Call<Asset> getAssets(@Header("Authorization") String api_key);

    @POST("hardware")
    Call<Rows> createAsset(@Header("Authorization") String api_key,
                           @Query("name") String name,
                           @Query("name") ModelRows model,
                           @Query("name") CompanieRows company,
                           @Query("name") StatusLabelRows status);
}

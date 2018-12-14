package com.example.radhwen.snipeit.services;

import com.example.radhwen.snipeit.model.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ModelServices {

    @GET("models")
    Call<Model> getModels(@Header("Authorization") String api_key);
}

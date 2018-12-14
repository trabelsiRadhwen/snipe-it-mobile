package com.example.radhwen.snipeit.services;

import com.example.radhwen.snipeit.model.StatusLabel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface StatusLabelServices {

    @GET("statuslabels")
    Call<StatusLabel> getStatus(@Header("Authorization") String api_key);
}

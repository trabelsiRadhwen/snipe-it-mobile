package com.example.radhwen.snipeit.services;

import com.example.radhwen.snipeit.model.Asset;
import com.example.radhwen.snipeit.model.User;
import com.example.radhwen.snipeit.model.UserRows;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface UserServices {

    @GET("users")
    Call<User> getUsers(@Header("Authorization") String api_key);

    @GET("users/{id}/assets")
    Call<Asset> getAssignedAssets(@Header("Authorization") String api_key,
                                  @Path("id") int id);
}

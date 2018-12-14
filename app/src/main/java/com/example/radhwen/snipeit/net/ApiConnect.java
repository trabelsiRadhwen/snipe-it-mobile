package com.example.radhwen.snipeit.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConnect {

    public static final String BASE_URL = "http://192.168.1.8:8080/api/v1/";

    private static Retrofit retrofit = null;

    public static final Retrofit getClient() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

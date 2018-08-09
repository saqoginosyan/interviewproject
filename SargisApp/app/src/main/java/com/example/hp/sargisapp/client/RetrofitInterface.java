package com.example.hp.sargisapp.client;

import com.example.hp.sargisapp.api.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("/api/")
    Call<ApiResponse> fetchUsers(@Query("results") int results);
}
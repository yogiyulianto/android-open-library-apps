package com.example.openlibraryapps.api;

import com.example.openlibraryapps.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookApiService {
    @GET("subjects/sport.json")
    Call<ResponseModel> getSportBooks(
            @Query("details") boolean details
    );
}

package com.example.manue.recipes;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {
    //Base url for all requests
    String BASE_URL = "https://www.food2fork.com/";

    //My API Key is 769f12f7ded3094cf5919f018b570c7f
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //Get request with API key already added
    @GET("api/search?key=769f12f7ded3094cf5919f018b570c7f&sort=r&count=3")

    //Symbol get method
    Call<RecipeRespond> getRecipe();
}

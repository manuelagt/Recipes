package com.example.manue.recipes;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Recipe> recipes = new ArrayList<>();
    private Adapter mAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestData();

        mAdapter = new Adapter(getSupportFragmentManager(),recipes);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mAdapter);

    }
    private void requestData() {
        ApiService service = ApiService.retrofit.create(ApiService.class);
        Call<RecipeRespond> call = service.getRecipe();

        call.enqueue(new Callback<RecipeRespond>() {
            @Override
            public void onResponse(Call<RecipeRespond> call, Response<RecipeRespond> response) {

                if (response.body() != null) {

                    recipes.addAll(Arrays.asList(response.body().recipes));
                    mAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<RecipeRespond> call, Throwable t) {
                Log.d("error",t.toString());
            }
        });


    }
}

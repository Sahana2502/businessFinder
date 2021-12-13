package com.rbc.yelp.services.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.rbc.yelp.R;
import com.rbc.yelp.services.YelpApi;
import com.rbc.yelp.services.YelpRetrofit;
import com.rbc.yelp.services.adapters.CategoryAdapter;
import com.rbc.yelp.services.models.Business;
import com.rbc.yelp.services.models.CategorizedBusiness;
import com.rbc.yelp.services.models.Category;
import com.rbc.yelp.services.models.SearchResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private TextInputEditText searchTerm, inputLocation;
    private RecyclerView mainCategoryRecycler, RecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button searchButton;
    private List<CategorizedBusiness> categorizedBusinessList;
    private CategoryAdapter mainRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        searchTerm = findViewById(R.id.search_term);
        inputLocation = findViewById(R.id.location);

        searchButton = findViewById(R.id.search_button);


        /*initialize parent Adapter;
         *
         **/
        mainCategoryRecycler = findViewById(R.id.main_recycler);
        layoutManager = new LinearLayoutManager(this);
        mainCategoryRecycler.setLayoutManager(layoutManager);

        initializeSearchButton();
    }


    private void initializeSearchButton() {
        searchButton.setOnClickListener(view -> searchRestaurants(searchTerm.getText().toString(), inputLocation.getText().toString()));
    }


    private void searchRestaurants(String searchTerm, String location) {

        //Remove focus and Hide keyboard once a request is submitted
        inputLocation.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(inputLocation.getWindowToken(), 0);

        Retrofit retrofit = new YelpRetrofit().getRetrofitInstance();

        YelpApi yelpApi = retrofit.create(YelpApi.class);

        /*
         * Check if the location field is not empty*/
        if (location.trim().length() > 0) {
            yelpApi.search(searchTerm, location)
                    .enqueue(new Callback<SearchResult>() {
                        @Override
                        public void onResponse(@NonNull Call<SearchResult> call, @NonNull Response<SearchResult> response) {
                            if (!response.isSuccessful()) {
                                Log.d(TAG, "Error: Response code " + response.code());
                                return;
                            }

                            //Check if response is not empty
                            if (response.body().getTotal() != 0) {
                                createSections(response);
                            } else {
                                categorizedBusinessList.clear();
                                mainRecyclerAdapter.notifyDataSetChanged();
                                displayErrorMessage("Oops! Nothing found");

                            }
                        }

                        @Override
                        public void onFailure(Call<SearchResult> call, Throwable t) {
                            Log.d(TAG, t.getMessage());
                        }
                    });
        } else
            inputLocation.setError("Required!");

    }

    /* Display error message if location is not entered*/
    private void displayErrorMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void createSections(Response<SearchResult> response) {

        TreeMap<String, List<Business>> treeMap = new TreeMap<>();
        List<Business> businessList = response.body().getBusinesses();
        categorizedBusinessList = new ArrayList<>();

        for (Business business : businessList) {
            List<Category> categories = business.getCategories();
            for (Category category : categories) {
                if (!treeMap.containsKey(category.getAlias())) {
                    List<Business> businesses = new ArrayList<>();
                    businesses.add(business);
                    treeMap.put(category.getAlias(), businesses);
                } else {
                    treeMap.get(category.getAlias()).add(business);
                }
            }
        }

        for (Map.Entry<String, List<Business>> map : treeMap.entrySet()) {
            categorizedBusinessList.add(new CategorizedBusiness(map.getKey(), treeMap.get(map.getKey())));
        }
        displayResults(categorizedBusinessList);

    }

    private void displayResults(List<CategorizedBusiness> categorizedBusinessList) {
        mainRecyclerAdapter = new CategoryAdapter(this, categorizedBusinessList);
        mainCategoryRecycler.setAdapter(mainRecyclerAdapter);
    }


}
package com.akshay.movieapp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.akshay.movieapp.adapter.MoviesRecyclerViewAdapter;
import com.akshay.movieapp.databinding.ActivityMainBinding;
import com.akshay.movieapp.model.Movie;
import com.akshay.movieapp.model.MovieResult;
import com.akshay.movieapp.network.NetworkUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private LinearLayoutManager mLayoutManager;
    private MoviesRecyclerViewAdapter mMoviesRecyclerViewAdapter;
    private ArrayList<Movie> mMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mBinding.rvMovieList.setLayoutManager(mLayoutManager);
        mMovies = new ArrayList<>();
        mMoviesRecyclerViewAdapter = new MoviesRecyclerViewAdapter(this,mMovies);
        mBinding.rvMovieList.setAdapter(mMoviesRecyclerViewAdapter);

        fetchMovies();

    }

    private void fetchMovies() {
        NetworkUtils.getMovieData(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                Log.d("Debug","Response code = "+response.code());
                MovieResult result = response.body();
                Log.d("Debug","number of movies= "+result.getResults());

                mMovies.addAll(result.getResults());
                mMoviesRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Log.d("Debug","error ",t);
            }
        });
    }
}

package com.akshay.movieapp.network;

import com.akshay.movieapp.model.MovieResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by akshaymathur on 1/17/18.
 */

public interface MovieInterface {

    @GET("now_playing")
    Call<MovieResult> getNowPlaying(@QueryMap HashMap<String, String> queryParams);
}

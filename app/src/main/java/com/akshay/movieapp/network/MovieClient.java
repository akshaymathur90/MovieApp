package com.akshay.movieapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by akshaymathur on 1/17/18.
 */

public class MovieClient {

    private static Retrofit sRetrofit;
    private static MovieClient sMovieClient;
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static MovieInterface sMovieInterface;
    private MovieClient(){
        sRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sMovieInterface = sRetrofit.create(MovieInterface.class);

    }

    public static MovieClient getMovieClient(){

        if(sMovieClient == null){
            sMovieClient = new MovieClient();
        }

        return sMovieClient;
    }

    public MovieInterface getMovieInterface(){
        return sMovieInterface;
    }
}

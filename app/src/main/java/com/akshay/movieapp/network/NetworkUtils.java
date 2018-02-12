package com.akshay.movieapp.network;

import android.net.Uri;

import com.akshay.movieapp.model.MovieResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by akshaymathur on 1/17/18.
 */

public class NetworkUtils {

    public static void getMovieData(Callback<MovieResult> callback){

        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("api_key","a07e22bc18f5cb106bfe4cc1f83ad8ed");

        MovieInterface movieInterface = MovieClient.getMovieClient().getMovieInterface();
        Call<MovieResult> movieResponseCall = movieInterface.getNowPlaying(queryParams);

        movieResponseCall.enqueue(callback);
    }

    public static void getData() {

        try {
            Uri uri = Uri.parse("http://").buildUpon().appendQueryParameter("id","55555").build();
            URL url = new URL(uri.toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.connect();
            httpURLConnection.setRequestMethod("GET");

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line=null;
            while ((line = bufferedReader.readLine())!=null){
                builder.append(line);
            }


        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}

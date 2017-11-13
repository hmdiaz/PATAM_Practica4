package com.carmona.climas;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by expocodetech on 13/4/17.
 */

public interface JsonPlaceHolderAPI {

    @GET("forecast")
    Call<Climas> getClimas(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String appid, @Query("units") String units, @Query("lang") String lang);
}

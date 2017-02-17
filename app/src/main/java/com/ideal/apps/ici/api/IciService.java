package com.ideal.apps.ici.api;


import com.ideal.apps.ici.model.Place;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IciService {
    @GET("places")
    List<Place> queryPlaces(@Query("radius") int radius,
                            @Query("lat") double latitude,
                            @Query("lng") double longitude,
                            @Query("category") Place.Category category);
    @GET("places/{placeId}")
    Place getPlace(@Path("placeId") String placeId);
}

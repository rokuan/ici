package com.ideal.apps.ici.api;


import com.ideal.apps.ici.api.message.AuthenticationMessage;
import com.ideal.apps.ici.api.message.SignUpMessage;
import com.ideal.apps.ici.api.result.SignUpResult;
import com.ideal.apps.ici.api.result.TokenResult;
import com.ideal.apps.ici.model.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IciService {
    /* USERS */
    @POST("users/signup")
    Call<SignUpResult> signup(@Body SignUpMessage message);

    @POST("users/authenticate")
    Call<TokenResult> authenticate(@Body AuthenticationMessage message);

    @POST("users/signout")
    Call<Void> signout(@Header(Constants.Headers.USER_TOKEN) String token);


    /* PLACES */
    @GET("places")
    Call<List<Place>> queryPlaces(@Query(Constants.Parameters.PLACE_RADIUS) int radius,
                            @Query(Constants.Parameters.PLACE_LATITUDE) double latitude,
                            @Query(Constants.Parameters.PLACE_LONGITUDE) double longitude,
                            @Query(Constants.Parameters.PLACE_CATEGORY) Place.Category category);

    @GET("places/{" + Constants.Path.PLACE_ID + "}")
    Call<Place> getPlace(@Path(Constants.Path.PLACE_ID) String placeId);

    @GET("places/myplaces")
    Call<List<Place>> getMyPlaces(@Header(Constants.Headers.USER_TOKEN) String jwt);
}

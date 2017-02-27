package com.ideal.apps.ici.api;


import com.ideal.apps.ici.api.message.AuthenticationMessage;
import com.ideal.apps.ici.api.message.CreateEventMessage;
import com.ideal.apps.ici.api.message.CreatePlaceMessage;
import com.ideal.apps.ici.api.message.SignUpMessage;
import com.ideal.apps.ici.api.message.UpdateEventMessage;
import com.ideal.apps.ici.api.message.UpdatePlaceMessage;
import com.ideal.apps.ici.api.result.SignUpResult;
import com.ideal.apps.ici.api.result.SimpleResult;
import com.ideal.apps.ici.api.result.TokenResult;
import com.ideal.apps.ici.model.Event;
import com.ideal.apps.ici.model.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IciService {
    //========== USERS
    @POST("users/signup")
    Call<SignUpResult> signup(@Body SignUpMessage message);

    @POST("users/authenticate")
    Call<TokenResult> authenticate(@Body AuthenticationMessage message);

    @POST("users/signout")
    Call<Void> signout(@Header(Constants.Headers.USER_TOKEN) String token);


    //========== PLACES
    @GET("places/query")
    Call<List<Place>> queryPlaces(@Query(Constants.Parameters.PLACE_RADIUS) int radius,
                            @Query(Constants.Parameters.PLACE_LATITUDE) double latitude,
                            @Query(Constants.Parameters.PLACE_LONGITUDE) double longitude,
                            @Query(Constants.Parameters.PLACE_CATEGORY) Place.Category category);

    @GET("places/myplaces")
    Call<List<Place>> getMyPlaces(@Header(Constants.Headers.USER_TOKEN) String jwt);

    @GET("places/{" + Constants.Path.PLACE_ID + "}")
    Call<Place> getPlace(@Header(Constants.Headers.USER_TOKEN) String jwt, @Path(Constants.Path.PLACE_ID) String placeId);

    @POST("places/add")
    Call<SimpleResult> addPlace(@Header(Constants.Headers.USER_TOKEN) String jwt, @Body CreatePlaceMessage place);

    @PUT("places/{" + Constants.Path.PLACE_ID + "}")
    Call<SimpleResult> updatePlace(@Header(Constants.Headers.USER_TOKEN) String jwt, @Path(Constants.Path.PLACE_ID) String placeId, @Body UpdatePlaceMessage place);

    @DELETE("places/{" + Constants.Path.PLACE_ID + "}")
    Call<Void> deletePlace(@Header(Constants.Headers.USER_TOKEN) String jwt, @Path(Constants.Path.PLACE_ID) String placeId);


    //========== EVENTS
    @GET("events/{" + Constants.Path.EVENT_ID + "}")
    Call<Event> getEvent(@Header(Constants.Headers.USER_TOKEN) String jwt, @Path(Constants.Path.EVENT_ID) String eventId);

    @POST("events/add")
    Call<SimpleResult> addEvent(@Header(Constants.Headers.USER_TOKEN) String jwt, @Body CreateEventMessage event);

    @PUT("events/update")
    Call<SimpleResult> updateEvent(@Header(Constants.Headers.USER_TOKEN) String jwt, @Body UpdateEventMessage event);

    @DELETE("events/{" + Constants.Path.EVENT_ID + "}")
    Call<SimpleResult> deleteEvent(@Header(Constants.Headers.USER_TOKEN) String jwt, @Path(Constants.Path.EVENT_ID) String eventId);
}

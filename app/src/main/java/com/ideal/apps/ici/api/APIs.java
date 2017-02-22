package com.ideal.apps.ici.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIs {
    static private final IciService ICI_SERVICE = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IciService.class);

    static public IciService getIciService(){
        return ICI_SERVICE;
    }
}

package com.ideal.apps.ici.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.ideal.apps.ici.api.APIs;
import com.ideal.apps.ici.api.IciService;
import com.ideal.apps.ici.api.SimpleCallback;
import com.ideal.apps.ici.api.message.AuthenticationMessage;
import com.ideal.apps.ici.api.result.TokenResult;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountService extends Service {
    public class AccountBinder extends Binder {
        public AccountService getService(){
            return AccountService.this;
        }
    }

    private static final IciService ICI_API = APIs.getIciService();
    private AccountBinder binder = new AccountBinder();
    private boolean authenticated = false;
    private String token;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public boolean isAuthenticated(){
        return authenticated;
    }

    public void signIn(String login, String password, final SimpleCallback<String> callback){
        AuthenticationMessage message = new AuthenticationMessage(login, password);
        ICI_API.authenticate(message).enqueue(new Callback<TokenResult>() {
            @Override
            public void onResponse(Call<TokenResult> call, Response<TokenResult> response) {
                TokenResult body = response.body();
                if(body.isSuccess()) {
                    authenticated = true;
                    token = body.getResult();
                }
                callback.process(body);
            }

            @Override
            public void onFailure(Call<TokenResult> call, Throwable t) {
                authenticated = false;
                callback.onFailure(t);
            }
        });
    }
}

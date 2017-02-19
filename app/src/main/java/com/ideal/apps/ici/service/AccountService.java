package com.ideal.apps.ici.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.ideal.apps.ici.api.APIs;
import com.ideal.apps.ici.api.IciService;

public class AccountService extends Service {
    public class AccountBinder extends Binder {
        public AccountService getService(){
            return AccountService.this;
        }
    }

    private static final IciService ICI_API = APIs.getIciService();
    private AccountBinder binder = new AccountBinder();

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
        return false;
    }
}

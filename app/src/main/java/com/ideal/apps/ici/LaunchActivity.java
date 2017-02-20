package com.ideal.apps.ici;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ideal.apps.ici.service.AccountService;

public class LaunchActivity extends Activity {
    private boolean bound = false;
    private AccountService service;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            AccountService.AccountBinder binder = (AccountService.AccountBinder) iBinder;
            service = binder.getService();
            bound = true;
            redirect();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            if(bound){
                service = null;
            }
            bound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_launch);
        final Intent serviceIntent = new Intent(this, AccountService.class);
        startService(serviceIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final Intent serviceIntent = new Intent(this, AccountService.class);
        bindService(serviceIntent, connection, BIND_AUTO_CREATE);
    }

    protected void redirect(){
        Class<?> targetActivity;

        if(service.isAuthenticated()){
            targetActivity = PlaceActivity.class;
        } else {
            targetActivity = MainActivity.class;
        }

        Intent i = new Intent(this, targetActivity);
        startActivity(i);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(bound){
            unbindService(connection);
        }
    }
}

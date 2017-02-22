package com.ideal.apps.ici;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.ideal.apps.ici.api.IciService;
import com.ideal.apps.ici.api.SimpleCallback;
import com.ideal.apps.ici.api.result.TokenResult;
import com.ideal.apps.ici.service.AccountService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.login_text)
    protected EditText loginView;
    @BindView(R.id.password_text)
    protected EditText passwordView;

    private boolean bound = false;
    private AccountService service;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            AccountService.AccountBinder binder = (AccountService.AccountBinder) iBinder;
            service = binder.getService();
            bound = true;
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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent serviceIntent = new Intent(this, AccountService.class);
        bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(bound) {
            unbindService(connection);
            bound = false;
        }
    }

    @OnClick(R.id.connect_button)
    public void connect(){
        String username = loginView.getText().toString();
        String password = passwordView.getText().toString();

        if(username.isEmpty()){
            // TODO:
            loginView.setError("Login cannot be empty");
            return;
        }
        if(password.isEmpty()){
            passwordView.setError("Password cannot be empty");
            return;
        }

        if(bound) {
            service.signIn(username, password, new SimpleCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Intent i = new Intent(MainActivity.this, PlaceActivity.class);
                    startActivity(i);
                }

                @Override
                public void onFailure(String error) {
                    Snackbar.make(findViewById(android.R.id.content), "Authentication failed", Snackbar.LENGTH_LONG);
                }
            });
        }
    }
}

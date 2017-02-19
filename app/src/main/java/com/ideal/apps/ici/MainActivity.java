package com.ideal.apps.ici;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.login_text)
    protected EditText login;
    @BindView(R.id.password_text)
    protected EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Intent mapIntent = new Intent(this, PlaceActivity.class);
        startActivity(mapIntent);
    }

    @OnClick(R.id.connect_button)
    public void connect(){
        if(login.getText().toString().isEmpty()){
            // TODO:
            login.setError("Login cannot be empty");
            return;
        }
        if(password.getText().toString().isEmpty()){
            password.setError("Password cannot be empty");
            return;
        }
        Intent i = new Intent(this, PlaceActivity.class);
        this.startActivity(i);
    }
}

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
    }

    @OnClick(R.id.connect_button)
    public void connect(){
        if(login.getText().toString().isEmpty()){
            // TODO:
            return;
        }
        if(password.getText().toString().isEmpty()){
            return;
        }
        Intent i = new Intent(this, MapsActivity.class);
        this.startActivity(i);
    }
}

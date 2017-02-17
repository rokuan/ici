package com.ideal.apps.ici;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent mapIntent = new Intent(this, MapActivity.class);
        Intent mapIntent = new Intent(this, PlaceActivity.class);
        startActivity(mapIntent);
    }
}

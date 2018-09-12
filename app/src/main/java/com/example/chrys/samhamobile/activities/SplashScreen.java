package com.example.chrys.samhamobile.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chrys.samhamobile.R;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal

                Intent i = new Intent(SplashScreen.this, TelaPrincipal.class);
                startActivity(i);

                // Fecha esta activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}

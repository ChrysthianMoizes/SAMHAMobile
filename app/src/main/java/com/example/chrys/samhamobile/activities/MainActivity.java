package com.example.chrys.samhamobile.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.chrys.samhamobile.R;

public class MainActivity extends AppCompatActivity {

    private Button btnAluno;
    private Button btnProfessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        defineEvents();
    }

    private void defineEvents() {

        btnProfessor.setOnClickListener(view -> {
            Intent i = new Intent(this, AuthActivity.class);
            startActivity(i);
        });

        btnAluno.setOnClickListener(view -> {
            Intent i = new Intent(this, AlunoActivity.class);
            startActivity(i);
        });
    }

    public void getViews(){
        btnAluno = findViewById(R.id.btnAluno);
        btnProfessor = findViewById(R.id.btnProfessor);

    }
}

package com.example.chrys.samhamobile.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chrys.samhamobile.R;

public class ProfessorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
    }

    /*botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });*/
}

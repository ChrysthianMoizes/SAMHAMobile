package com.br.chrys.samhamobile.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.br.chrys.samhamobile.R;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private Button btnAluno;
    private Button btnProfessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        defineEvents();
        EventBus.getDefault().register(this);
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

    @Override
    public void onStop(){
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receberNotificacao(RemoteMessage mensagem){
        Toast.makeText(this, "Notificação recebida: " + mensagem.getNotification().getBody(),
                Toast.LENGTH_LONG).show();;
    }
}

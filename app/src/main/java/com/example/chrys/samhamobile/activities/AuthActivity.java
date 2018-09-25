package com.example.chrys.samhamobile.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.chrys.samhamobile.R;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private Toolbar toolbar;
    private Button btnAuth;
    private EditText edtEmail;
    private EditText edtSenha;
    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        getViews();

        auth = FirebaseAuth.getInstance();

        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });

        btnAuth.setOnClickListener(view -> {
            new Autenticar().execute(edtEmail.getText().toString(), edtSenha.getText().toString());
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // Usuário Logado
                    Intent it = new Intent(AuthActivity.this, ProfessorActivity.class);
                    Bundle params = new Bundle();
                    params.putString("user id", user.getUid() );
                    params.putString("email", user.getEmail());
                    it.putExtras(params);
                    AuthActivity.this.startActivity(it);
                } else {
                    // Usuário não logado
                    Toast.makeText(AuthActivity.this, "Sem usuário logado", Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    public void getViews(){
        btnAuth = findViewById(R.id.btnAuth);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        toolbar = findViewById(R.id.toolbar_auth);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        progressBar = findViewById(R.id.progressBarAuth);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            auth.removeAuthStateListener(mAuthListener);
        }
    }

    private class Autenticar extends AsyncTask<String, Void, Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(String... strings) {

            Task<AuthResult> authResultTask = auth.signInWithEmailAndPassword(strings[0], strings[1]);

            try {
                AuthResult res = Tasks.await(authResultTask);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(authResultTask.isSuccessful()){
                //Toast.makeText(AuthActivity.this, "Usuário autenticado", Toast.LENGTH_SHORT).show();
                return true;
            }else{
                //Toast.makeText(AuthActivity.this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean resposta) {
            super.onPostExecute(resposta);
            progressBar.setVisibility(View.GONE);
        }
    }


}
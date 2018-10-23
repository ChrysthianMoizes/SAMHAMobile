package com.br.chrys.samhamobile.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.br.chrys.samhamobile.R;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.ExecutionException;

public class AlterarSenhaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnAlterar;
    private EditText edtNovaSenha;
    private EditText edtSenha;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);
        getViews();

        btnAlterar.setOnClickListener(view -> {
            String email = getIntent().getExtras().getString("email");
            String senha = edtSenha.getText().toString();
            String novaSenha = edtNovaSenha.getText().toString();

            if(senha.length() == 0)
                Toast.makeText(AlterarSenhaActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            else if(novaSenha.length() < 6)
                Toast.makeText(AlterarSenhaActivity.this, "Insira uma Nova Senha válida", Toast.LENGTH_SHORT).show();
            else
                new AlterarSenha().execute(email, senha, novaSenha);
        });

    }

    public void getViews(){
        btnAlterar = findViewById(R.id.btnAlterar);
        edtSenha = findViewById(R.id.edtSenha);
        edtNovaSenha = findViewById(R.id.edtNovaSenha);
        toolbar = findViewById(R.id.toolbar_alterar_senha);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.alterar_senha);
        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });
       progressBar = findViewById(R.id.progressBarAlterar);
    }

    private class AlterarSenha extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(String... strings) {

            FirebaseAuth auth = FirebaseAuth.getInstance();
            Task<AuthResult> authResultTask = null;

            try {

                authResultTask = auth.signInWithEmailAndPassword(strings[0], strings[1]);
                AuthResult res = Tasks.await(authResultTask);

                Task<Void> voidTask = auth.getCurrentUser().updatePassword(strings[2]);
                Tasks.await(voidTask);
                return voidTask.isSuccessful();

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return false;

        }

        @Override
        protected void onPostExecute(Boolean resposta) {
            super.onPostExecute(resposta);
            progressBar.setVisibility(View.GONE);
            if(!resposta){
                Toast.makeText(AlterarSenhaActivity.this, "Erro ao alterar senha", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(AlterarSenhaActivity.this, "Senha alterada!", Toast.LENGTH_SHORT).show();
            }
            edtSenha.setText("");
            edtNovaSenha.setText("");
        }
    }
}

package com.example.chrys.samhamobile.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;

import com.example.chrys.samhamobile.R;
import com.example.chrys.samhamobile.dominio.Aula;
import com.example.chrys.samhamobile.fragments.MessageFragment;
import com.example.chrys.samhamobile.manager.ManagerAula;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class ProfessorActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private NumberPicker numberPickerAno;
    private NumberPicker numberPickerSemestre;
    private Button btnBuscar;
    private Toolbar toolbar;

    private ManagerAula managerAulas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        managerAulas = new ManagerAula();
        getViews();
        preencherNumberPickers();
        defineEvents();
        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });
    }

    public void getViews(){
        btnBuscar = findViewById(R.id.btnBuscarProfessor);
        progressBar = findViewById(R.id.progressBarProfessor);
        toolbar = findViewById(R.id.toolbar_professor);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        numberPickerAno = findViewById(R.id.npAnoProfessor);
        numberPickerSemestre = findViewById(R.id.npSemestreProfessor);
        setSupportActionBar(toolbar);
    }

    public void preencherNumberPickers(){

        final Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);

        numberPickerAno.setMinValue(2018);
        numberPickerAno.setMaxValue(ano + 1);
        numberPickerAno.setValue(ano);
        numberPickerAno.setWrapSelectorWheel(false);

        numberPickerSemestre.setMinValue(1);
        numberPickerSemestre.setMaxValue(2);
        numberPickerSemestre.setWrapSelectorWheel(false);

        if(mes > 6)
            numberPickerSemestre.setValue(2);
        else
            numberPickerSemestre.setValue(1);
    }

    public void defineEvents() {

        btnBuscar.setOnClickListener(view -> {

            int ano = numberPickerAno.getValue();
            int semestre = numberPickerSemestre.getValue();
            String email = getIntent().getExtras().getString("email");

            new BuscaAulasProfessor(this).execute(String.valueOf(ano), String.valueOf(semestre), "1");

        });
    }

    public void exibirDialogProfessorSemAulas(){
        MessageFragment dialog = new MessageFragment();
        dialog.setTitulo(R.string.erro_aulas_professor);
        dialog.setMensagem(R.string.message_aulas_professor);
        dialog.show(getSupportFragmentManager(), "aulas_professor");
    }

    public void exibirDialogErroAoConectarAoServidor(){
        MessageFragment dialog = new MessageFragment();
        dialog.setTitulo(R.string.erro_servidor);
        dialog.setMensagem(R.string.message_erro);
        dialog.show(getSupportFragmentManager(), "erro_servidor");
    }

    private class BuscaAulasProfessor extends AsyncTask<String, Void, List<Aula>> {

        private Intent intent;
        private ProfessorActivity tela;

        public BuscaAulasProfessor(ProfessorActivity tela) {
            intent = new Intent(tela, HorariosActivity.class);
            this.tela = tela;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Aula> doInBackground(String... strings) {
            return managerAulas.obterAulasProfessor(strings[0], strings[1], strings[2]);
        }

        @Override
        protected void onPostExecute(List<Aula> aulas) {
            super.onPostExecute(aulas);
            progressBar.setVisibility(View.GONE);
            identificarResposta(aulas);
        }

        protected void identificarResposta(List<Aula> aulas){

            if(aulas != null){

                if(!aulas.isEmpty()){
                    String email = tela.getIntent().getExtras().getString("email");
                    String user_id = tela.getIntent().getExtras().getString("user_id");
                    intent.putExtra("turma", "M20");
                    // obter nome do professor
                    intent.putExtra("email", email);
                    intent.putExtra("user_id", user_id);
                    intent.putExtra("aulas", (Serializable) aulas);

                    tela.startActivity(intent);

                }else{
                    exibirDialogProfessorSemAulas();
                }

            }else{
                exibirDialogErroAoConectarAoServidor();
            }

        }
    }

    /*botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });*/
}

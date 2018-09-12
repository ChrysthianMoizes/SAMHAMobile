package com.example.chrys.samhamobile.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import com.example.chrys.samhamobile.R;
import com.example.chrys.samhamobile.dominio.Aula;
import com.example.chrys.samhamobile.dominio.Turma;
import com.example.chrys.samhamobile.fragments.MessageFragment;
import com.example.chrys.samhamobile.manager.ManagerAula;
import com.example.chrys.samhamobile.manager.ManagerTurma;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class TelaPrincipal extends AppCompatActivity {

    private ManagerAula managerAulas;
    private ManagerTurma managerTurma;

    private Button btnBuscar;
    private Button btnTurma;
    private Spinner spnTurmas;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private NumberPicker numberPickerAno;
    private NumberPicker numberPickerSemestre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        managerAulas = new ManagerAula();
        managerTurma = new ManagerTurma();
        getViews();
        preencherNumberPickers();
        onClick();
    }

    public void preencherNumberPickers(){
        final Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        numberPickerAno.setMinValue(ano - 5);
        numberPickerAno.setMaxValue(ano + 1);
        numberPickerAno.setValue(ano);

        numberPickerSemestre.setMinValue(1);
        numberPickerSemestre.setMaxValue(2);
    }

    public void onClick(){

        btnBuscar.setOnClickListener(view -> {
            int ano = numberPickerAno.getValue();
            int semestre = numberPickerSemestre.getValue();
            Turma turma = (Turma) spnTurmas.getSelectedItem();

            if(turma != null){
                String idTurma = String.valueOf(turma.getId());
                new BuscaAulas(this).execute(String.valueOf(ano), String.valueOf(semestre), idTurma);
            }else{
                exibirDialogSemTurmasAtivas();
            }
        });

        btnTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obterTurmas();
            }
        });

    }

    public void obterTurmas() {
        int ano = numberPickerAno.getValue();
        int semestre = numberPickerSemestre.getValue();
        new BuscaTurmasAtivasAnoSemestre(this).execute(String.valueOf(ano), String.valueOf(semestre));
    }

    public void getViews(){
        btnBuscar = findViewById(R.id.btnBuscar);
        btnTurma = findViewById(R.id.btnTurma);
        spnTurmas = findViewById(R.id.spnTurmas);
        progressBar = findViewById(R.id.progressBar);
        toolbar = findViewById(R.id.toolbar_inicial);
        numberPickerAno = findViewById(R.id.npAno);
        numberPickerSemestre = findViewById(R.id.npSemestre);
        setSupportActionBar(toolbar);
    }

    public void exibirDialogSemTurmasAtivas(){
        MessageFragment dialog = new MessageFragment();
        dialog.setTitulo(R.string.erro_turmas_ativas);
        dialog.setMensagem(R.string.message_turmas_ativas);
        dialog.show(getSupportFragmentManager(), "turmas_ativas");
    }

    public void exibirDialogTurmaSemAulas(){
        MessageFragment dialog = new MessageFragment();
        dialog.setTitulo(R.string.erro_aulas_turma);
        dialog.setMensagem(R.string.message_aulas_turma);
        dialog.show(getSupportFragmentManager(), "aulas_turma");
    }

    public void exibirDialogErroAoConectarAoServidor(){
        MessageFragment dialog = new MessageFragment();
        dialog.setTitulo(R.string.erro_servidor);
        dialog.setMensagem(R.string.message_erro);
        dialog.show(getSupportFragmentManager(), "erro_servidor");
}

    private class BuscaAulas extends AsyncTask<String, Void, List<Aula>>{

        private Intent intent;
        private TelaPrincipal tela;

        public BuscaAulas(TelaPrincipal tela) {
            intent = new Intent(tela, TelaConsulta.class);
            this.tela = tela;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Aula> doInBackground(String... strings) {
            return managerAulas.obterAulas(strings[0], strings[1], strings[2]);
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

                    String turma = spnTurmas.getSelectedItem().toString();

                    intent.putExtra("turma", turma);
                    intent.putExtra("aulas", (Serializable) aulas);

                    tela.startActivity(intent);

                }else
                    exibirDialogTurmaSemAulas();
            }else
                exibirDialogErroAoConectarAoServidor();
        }
    }

    private class BuscaTurmasAtivasAnoSemestre extends AsyncTask<String, Void, List<Turma>>{

        private TelaPrincipal tela;

        public BuscaTurmasAtivasAnoSemestre(TelaPrincipal tela) {
            this.tela = tela;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Turma> doInBackground(String... strings) {
            return managerTurma.obterTurmas(strings[0], strings[1]);
        }

        @Override
        protected void onPostExecute(List<Turma> turmas) {
            super.onPostExecute(turmas);
            progressBar.setVisibility(View.GONE);
            identificarResposta(turmas);
        }

        protected void identificarResposta(List<Turma> turmas){

            if(turmas != null){

                ArrayAdapter adapter = new ArrayAdapter<>(tela, R.layout.support_simple_spinner_dropdown_item, turmas);
                spnTurmas.setAdapter(adapter);

                if(turmas.isEmpty())
                    exibirDialogSemTurmasAtivas();

            }else
                exibirDialogErroAoConectarAoServidor();
        }
    }
}

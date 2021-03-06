package com.br.chrys.samhamobile.activities;

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
import android.widget.Toast;

import com.br.chrys.samhamobile.R;
import com.br.chrys.samhamobile.dominio.Aula;
import com.br.chrys.samhamobile.dominio.Turma;
import com.br.chrys.samhamobile.fragments.MessageFragment;
import com.br.chrys.samhamobile.manager.Constantes;
import com.br.chrys.samhamobile.manager.ManagerAula;
import com.br.chrys.samhamobile.manager.ManagerTurma;

import java.io.Serializable;
import java.util.List;

public class AlunoActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_aluno);
        managerAulas = new ManagerAula();
        managerTurma = new ManagerTurma();
        getViews();
        Util.preencherNumberPickers(numberPickerAno, numberPickerSemestre);
        defineEvents();
        obterTurmas();
    }

    public void defineEvents(){

        numberPickerAno.setOnScrollListener((numberPicker, i) -> {
            btnBuscar.setEnabled(false);
        });

        numberPickerSemestre.setOnScrollListener((numberPicker, i) -> {
            btnBuscar.setEnabled(false);
        });

        btnBuscar.setOnClickListener(view -> {

            int ano = numberPickerAno.getValue();
            int semestre = numberPickerSemestre.getValue();
            Turma turma = (Turma) spnTurmas.getSelectedItem();

            if(turma != null){
                String idTurma = String.valueOf(turma.getId());
                new BuscaAulasTurma(this).execute(String.valueOf(ano), String.valueOf(semestre), idTurma);
            }else{
                exibirDialogSemTurmasAtivas();
            }
        });

        btnTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obterTurmas();
                btnBuscar.setEnabled(true);
            }
        });

        spnTurmas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(adapterView.getContext(), "Turma selecionada: " + item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        toolbar.setNavigationOnClickListener(view -> {
            finish();
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
        toolbar = findViewById(R.id.toolbar_aluno);
        toolbar.setNavigationIcon(R.drawable.ic_back);
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

    private class BuscaAulasTurma extends AsyncTask<String, Void, List<Aula>>{

        private Intent intent;
        private AlunoActivity tela;

        public BuscaAulasTurma(AlunoActivity tela) {
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
            return managerAulas.obterAulasTurma(strings[0], strings[1], strings[2]);
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
                    intent.putExtra("user", Constantes.ALUNO);
                    intent.putExtra("titulo", turma);
                    intent.putExtra("aulas", (Serializable) aulas);

                    tela.startActivity(intent);

                }else
                    exibirDialogTurmaSemAulas();
            }else
                Util.exibirDialogErroAoConectarAoServidor(getSupportFragmentManager());
        }
    }

    private class BuscaTurmasAtivasAnoSemestre extends AsyncTask<String, Void, List<Turma>>{

        private AlunoActivity tela;

        public BuscaTurmasAtivasAnoSemestre(AlunoActivity tela) {
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
                ArrayAdapter adapter = new ArrayAdapter<>(tela, android.R.layout.simple_expandable_list_item_1, android.R.id.text1, turmas);
                spnTurmas.setAdapter(adapter);

                if(turmas.isEmpty())
                    exibirDialogSemTurmasAtivas();

            }else
                Util.exibirDialogErroAoConectarAoServidor(getSupportFragmentManager());
        }
    }
}

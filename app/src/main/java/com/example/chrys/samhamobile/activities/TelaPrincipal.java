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
import android.widget.ProgressBar;
import android.widget.Spinner;
import com.example.chrys.samhamobile.R;
import com.example.chrys.samhamobile.dominio.Aula;
import com.example.chrys.samhamobile.dominio.Turma;
import com.example.chrys.samhamobile.fragments.ErroFragment;
import com.example.chrys.samhamobile.manager.ManagerAula;
import com.example.chrys.samhamobile.manager.ManagerTurma;

import java.io.Serializable;
import java.util.List;

public class TelaPrincipal extends AppCompatActivity {

    private ManagerAula managerAulas;
    private ManagerTurma managerTurma;

    private Button btnBuscar;
    private Spinner spnAno;
    private Spinner spnSemestre;
    private Spinner spnTurmas;
    private Toolbar toolbar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        managerAulas = new ManagerAula();
        managerTurma = new ManagerTurma();
        getViews();
        onClick();
        setarAdapterSpinners();
    }

    public void onClick(){

        btnBuscar.setOnClickListener(view -> {
            String ano = spnAno.getSelectedItem().toString();
            String semestre = spnSemestre.getSelectedItem().toString();
            Turma turma = (Turma) spnTurmas.getSelectedItem();

            if(turma != null){
                String idTurma = String.valueOf(turma.getId());
                new BuscaAulas(this).execute(ano, semestre, idTurma);
            }else{
                exibirDialogSemTurmasAtivas();
            }
        });

        spnAno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                obterTurmas();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnSemestre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                obterTurmas();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void obterTurmas(){
        String ano = spnAno.getSelectedItem().toString();
        String semestre = spnSemestre.getSelectedItem().toString();
        new BuscaTurmasAtivasAnoSemestre(this).execute(ano, semestre);
    }

    public void setarAdapterSpinners(){

        ArrayAdapter<String> adapter = null;

        String[] semestres = new String[]{"1", "2"};
        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, semestres);
        spnSemestre.setAdapter(adapter);

        String[] anos = new String[]{"2018", "2019", "2020", "2021", "2022", "2023"};
        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, anos);
        spnAno.setAdapter(adapter);
    }

    public void getViews(){
        btnBuscar = findViewById(R.id.btnBuscar);
        spnAno = findViewById(R.id.spnAno);
        spnSemestre = findViewById(R.id.spnSemestre);
        spnTurmas = findViewById(R.id.spnTurmas);
        progressBar = findViewById(R.id.progressBar);
        toolbar = findViewById(R.id.toolbar_inicial);
        setSupportActionBar(toolbar);
    }

    public void exibirDialogSemTurmasAtivas(){
        ErroFragment dialog = new ErroFragment();
        dialog.setTitulo(R.string.erro_turmas_ativas);
        dialog.setMensagem(R.string.message_turmas_ativas);
        dialog.show(getSupportFragmentManager(), "turmas_ativas");
    }

    public void exibirDialogTurmaSemAulas(){
        ErroFragment dialog = new ErroFragment();
        dialog.setTitulo(R.string.erro_aulas_turma);
        dialog.setMensagem(R.string.message_aulas_turma);
        dialog.show(getSupportFragmentManager(), "aulas_turma");
    }

    public void exibirDialogErroAoConectarAoServidor(){
        ErroFragment dialog = new ErroFragment();
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

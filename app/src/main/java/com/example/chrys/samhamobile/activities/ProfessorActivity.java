package com.example.chrys.samhamobile.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.chrys.samhamobile.R;
import com.example.chrys.samhamobile.dominio.Aula;
import com.example.chrys.samhamobile.fragments.MessageFragment;
import com.example.chrys.samhamobile.manager.ManagerAula;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class ProfessorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ProgressBar progressBar;
    private NumberPicker numberPickerAno;
    private NumberPicker numberPickerSemestre;
    private Button btnBuscar;

    private ManagerAula managerAulas;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_professor);
        managerAulas = new ManagerAula();
        settingsMenu();
        getViews();
        Util.preencherNumberPickers(numberPickerAno, numberPickerSemestre);
        defineEvents();
    }

    public void getViews(){
        btnBuscar = findViewById(R.id.btnBuscarProfessor);
        progressBar = findViewById(R.id.progressBarProfessor);
        numberPickerAno = findViewById(R.id.npAnoProfessor);
        numberPickerSemestre = findViewById(R.id.npSemestreProfessor);
    }

    public void defineEvents() {

        btnBuscar.setOnClickListener(view -> {

            int ano = numberPickerAno.getValue();
            int semestre = numberPickerSemestre.getValue();
            String email = getIntent().getExtras().getString("email");

            new BuscaAulasProfessor(this).execute(String.valueOf(ano), String.valueOf(semestre), email);

        });

    }

    public void settingsMenu(){

        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_professor);
        navigationView.setNavigationItemSelectedListener(this);

        email = getIntent().getExtras().getString("email");

        View header_view = navigationView.inflateHeaderView(R.layout.nav_header_menu);
        TextView emailProfessor = header_view.findViewById(R.id.email_professor);
        emailProfessor.setText(email);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_password) {
            Intent it = new Intent(ProfessorActivity.this, AlterarSenhaActivity.class);
            it.putExtras(getIntent().getExtras());
            ProfessorActivity.this.startActivity(it);
        } else if (id == R.id.nav_exit) {
            FirebaseAuth.getInstance().signOut();
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void exibirDialogProfessorSemAulas(){
        MessageFragment dialog = new MessageFragment();
        dialog.setTitulo(R.string.erro_aulas_professor);
        dialog.setMensagem(R.string.message_aulas_professor);
        dialog.show(getSupportFragmentManager(), "aulas_professor");
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

                if(aulas.size() > 1){
                    String user_id = tela.getIntent().getExtras().getString("user_id");

                    Aula aula = aulas.get(0);
                    String professor = aula.getProf();
                    aulas.remove(0);

                    intent.putExtra("titulo", professor);
                    intent.putExtra("email", email);
                    intent.putExtra("user_id", user_id);
                    intent.putExtra("aulas", (Serializable) aulas);
                    intent.putExtra("user", 1);
                    tela.startActivity(intent);

                }else
                    exibirDialogProfessorSemAulas();
            }else
                Util.exibirDialogErroAoConectarAoServidor(getSupportFragmentManager());
        }
    }
}

package com.example.chrys.samhamobile.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;

import com.example.chrys.samhamobile.R;

import java.util.Calendar;

public class ProfessorActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private NumberPicker numberPickerAno;
    private NumberPicker numberPickerSemestre;
    private Button btnBuscar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        getViews();
        preencherNumberPickers();

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

            /*Turma turma = (Turma) spnTurmas.getSelectedItem();

            if (turma != null) {
                String idTurma = String.valueOf(turma.getId());
                new BuscaAulas(this).execute(String.valueOf(ano), String.valueOf(semestre), idTurma);
            } else {
                exibirDialogSemTurmasAtivas();
            }*/

        });
    }

    /*botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });*/
}

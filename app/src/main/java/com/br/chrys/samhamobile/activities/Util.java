package com.br.chrys.samhamobile.activities;

import android.support.v4.app.FragmentManager;
import android.widget.NumberPicker;

import com.br.chrys.samhamobile.R;
import com.br.chrys.samhamobile.fragments.MessageFragment;

import java.util.Calendar;

public class Util {

    public static void preencherNumberPickers(NumberPicker numberPickerAno, NumberPicker numberPickerSemestre){

        final Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);

        numberPickerAno.setMinValue(2018);
        numberPickerAno.setMaxValue(ano);
        numberPickerAno.setValue(ano);
        numberPickerAno.setWrapSelectorWheel(false);

        numberPickerSemestre.setMinValue(1);
        numberPickerSemestre.setMaxValue(1);

        numberPickerSemestre.setWrapSelectorWheel(false);

        if(mes > 6) {
            numberPickerSemestre.setMaxValue(2);
            numberPickerSemestre.setValue(2);
        }else
            numberPickerSemestre.setValue(1);

        numberPickerAno.setOnClickListener(view -> {
        });

        numberPickerSemestre.setOnClickListener(view -> {
        });

    }

    public static void exibirDialogErroAoConectarAoServidor(FragmentManager fm){
        MessageFragment dialog = new MessageFragment();
        dialog.setTitulo(R.string.erro_servidor);
        dialog.setMensagem(R.string.message_erro);
        dialog.show(fm, "erro_servidor");
    }

}

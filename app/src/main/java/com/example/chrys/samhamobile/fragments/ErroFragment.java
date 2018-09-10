package com.example.chrys.samhamobile.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.chrys.samhamobile.R;

public class ErroFragment extends DialogFragment {

    private int title;
    private int mensagem;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.fragment_erro, null));

        TextView t = getActivity().findViewById(R.id.titulo);
        TextView m = getActivity().findViewById(R.id.mensagem);

        t.setText(title);
        m.setText(mensagem);

        builder.setPositiveButton(R.string.botao_ok_entendi, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dismiss();
            }
        });

        return builder.create();
    }

    public void setTitulo(int titulo) {
        this.title = titulo;
    }

    public void setMensagem(int mensagem) {
        this.mensagem = mensagem;
    }

}

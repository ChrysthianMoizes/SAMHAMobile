package com.example.chrys.samhamobile.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chrys.samhamobile.R;
import com.example.chrys.samhamobile.activities.HorariosActivity;
import com.example.chrys.samhamobile.dominio.Aula;
import com.example.chrys.samhamobile.manager.Constantes;

import java.util.List;

public class HorariosFragment extends Fragment {

    private int posicao;
    private List<Aula> aulas;
    private int user;

    public HorariosFragment(){

    }

    public static Fragment newInstance(int position, List<Aula> lista, int user) {
        HorariosFragment fragment = new HorariosFragment();
        fragment.setPosicao(position);
        fragment.setAulas(lista);
        fragment.setUser(user);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View containerExterno = inflater.inflate(R.layout.fragment_horarios, container, false);
        TextView nome_turno = containerExterno.findViewById(R.id.titulo_turno);
        nome_turno.setText(getResources().getStringArray(R.array.turnos)[posicao]);

        String[] dias = getResources().getStringArray(R.array.days);

        for(int dia = 0; dia < dias.length; dia++){
            String dia_id = "dia_" + dia;
            int d = getActivity().getResources().getIdentifier(dia_id, "id", getActivity().getPackageName());
            TextView dia_nome = containerExterno.findViewById(d);
            dia_nome.setText(dias[dia]);
        }

        for(int col = 0; col < 6; col++){
            createColumn(containerExterno, col, obterTurno(posicao));
        }

        return containerExterno;
    }

    public void createColumn(View containerExterno, int col, String[] aulas){

        String aula_id = "aula_" + col;
        int coluna_id = getActivity().getResources().getIdentifier(aula_id, "id", getActivity().getPackageName());

        View coluna = containerExterno.findViewById(coluna_id);
        TextView horario = coluna.findViewById(R.id.day);
        horario.setText(aulas[col]);

        for(int row = 0; row < 5; row++){
            createRow(coluna, row, col);
        }
    }

    public void createRow(View coluna, int row, int col){

        String celula_horario = "celula_" + row;

        int celula_id = getActivity().getResources().getIdentifier(celula_horario, "id", getActivity().getPackageName());
        View celula = coluna.findViewById(celula_id);

        TextView aula = celula.findViewById(R.id.texto_celula);
        aula.setText("");
        if(user == Constantes.ALUNO){
            preencherCelulaAulaTurma(aula, row, col);
        }else{
            preencherCelulaAulaProfessor(aula, row, col);
        }

    }

    public void preencherCelulaAulaTurma(TextView celula, int row, int col){

        for(Aula aula : aulas){
            if((aula.getNumero() - aula.getTurno()) == col && aula.getDia() == row){
                String texto = aula.getAlocacao().getDisciplina().getSigla() + "\n" + aula.getAlocacao().getProfessor1().obterPrimeiroNome();
                celula.setText(texto);
                if(aula.getAlocacao().getProfessor2() != null){
                    celula.setText(texto + "\n" + aula.getAlocacao().getProfessor2().obterPrimeiroNome());
                }
            }
        }
    }

    public void preencherCelulaAulaProfessor(TextView celula, int row, int col){

        for(Aula aula : aulas){
            if((aula.getNumero() - aula.getTurno()) == col && aula.getDia() == row){
                String texto = aula.getOferta().getTurma().getNome() + "\n" + aula.getAlocacao().getDisciplina().getSigla();
                celula.setText(texto);
            }
        }
    }

    public String[] obterTurno(int posicao){

        switch (posicao) {

            case 0:
                return getResources().getStringArray(R.array.times_matutino);
            case 1:
                return getResources().getStringArray(R.array.times_vespertino);
            case 2:
                return getResources().getStringArray(R.array.times_noturno);
            default:
                return null;
        }
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public void setUser(int user) {
        this.user = user;
    }
}

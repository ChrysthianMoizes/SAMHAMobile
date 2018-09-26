package com.example.chrys.samhamobile.manager;

import com.example.chrys.samhamobile.connection.JSONAulasService;
import com.example.chrys.samhamobile.dominio.Alocacao;
import com.example.chrys.samhamobile.dominio.Aula;
import com.example.chrys.samhamobile.dominio.Disciplina;
import com.example.chrys.samhamobile.dominio.Professor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ManagerAula {

    public ManagerAula() {
    }

    public List obterAulasTurma(String ano, String semestre, String id_turma){

        try {

            JSONArray array = JSONAulasService.obterJSONAulasTurma(ano, semestre, id_turma);

            if(array.length() > 0)
                return transformarJSONEmListaAulas(array);

            return new ArrayList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List obterAulasProfessor(String ano, String semestre, String email){

        try {

            JSONArray array = JSONAulasService.obterJSONAulasTurma(ano, semestre, email);

            if(array.length() > 0)
                return transformarJSONEmListaAulas(array);

            return new ArrayList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List transformarJSONEmListaAulas(JSONArray array) throws JSONException {

        List aulas = new ArrayList();

        for(int i = 0; i < array.length(); i++){

            Aula aula = new Aula();

            JSONObject a = array.getJSONObject(i);

            aula.setId(a.getInt("id_aula"));
            aula.setDia(a.getInt("dia"));
            aula.setNumero(a.getInt("numero"));
            aula.setTurno(a.getInt("turno"));

            Alocacao alocacao = new Alocacao();
            JSONObject al = a.getJSONObject("alocacao");
            alocacao.setId(al.getInt("id_alocacao"));

            Disciplina disciplina = new Disciplina();
            JSONObject d = al.getJSONObject("disciplina");
            disciplina.setId(d.getInt("id_disciplina"));
            disciplina.setSigla(d.getString("sigla"));

            Professor professor1 = new Professor();
            JSONObject p1 = al.getJSONObject("professor1");
            professor1.setId(p1.getInt("id_professor1"));
            professor1.setNome(p1.getString("nome_professor1"));

            JSONObject p2 = al.getJSONObject("professor2");

            if(p2.has("id_professor2") && p2.has("nome_professor2")){
                Professor professor2 = new Professor();
                professor2.setId(p2.getInt("id_professor2"));
                professor2.setNome(p2.getString("nome_professor2"));
                alocacao.setProfessor2(professor2);
            }

            alocacao.setDisciplina(disciplina);
            alocacao.setProfessor1(professor1);

            aula.setAlocacao(alocacao);
            System.out.println(aula);
            aulas.add(aula);

        }

        return aulas;
    }

    public List obterAulasTurno(int turno, List<Aula> listaAulas){

        if(turno == 0){
            turno = 0;
        }else if(turno == 1){
            turno = 6;
        }else{
            turno = 12;
        }

        List aulas = new ArrayList<>();

        for(Aula aula: listaAulas){
            if(aula.getTurno() == turno){
                aulas.add(aula);
            }
        }

        return aulas;
    }
}

package com.example.chrys.samhamobile.manager;

import com.example.chrys.samhamobile.connection.JSONAulasService;
import com.example.chrys.samhamobile.dominio.Aula;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ManagerAula {

    public ManagerAula() {
    }

    public List obterAulas(String ano, String semestre, String id_turma){

        try {

            JSONObject json = JSONAulasService.obterJSONAulas(ano, semestre, id_turma);
            return transformarJSONObjetos(json);

        } catch (Exception e) {
            e.printStackTrace();
            return transformarJSONObjetos(null);
        }
    }

    public List transformarJSONObjetos(JSONObject json){

        return new ArrayList();
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

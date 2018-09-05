package com.example.chrys.samhamobile.manager;

import com.example.chrys.samhamobile.connection.JSONTurmasService;
import com.example.chrys.samhamobile.dominio.Turma;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManagerTurma {

    public ManagerTurma() {
    }

    public List obterTurmas(String ano, String semestre){

        try {
            JSONArray array = JSONTurmasService.obterJSONTurmas(ano, semestre);
            return transformarJSONEmListaTurmas(array);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List transformarJSONEmListaTurmas(JSONArray array) throws JSONException {

        List turmas = new ArrayList();

        for(int i = 0; i < array.length(); i++){

            Turma turma = new Turma();

            JSONObject t = array.getJSONObject(i);

            turma.setId(t.getInt("id"));
            turma.setNome(t.getString("nome"));

            turmas.add(turma);
        }

        Collections.sort(turmas);
        return turmas;
    }
}

package com.example.chrys.samhamobile.connection;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class JSONAulasService extends HttpConnection {

    public static JSONArray obterJSONAulasTurma(String ano, String semestre, String turma_id) throws Exception {

        HttpURLConnection connection = prepareConection();
        connection.setRequestProperty("tipo", "aulas_turma");

        OutputStream out = connection.getOutputStream();

        JSONObject json = new JSONObject();

        json.put("ano", ano);
        json.put("semestre", semestre);
        json.put("turma_id", turma_id);

        out.write(json.toString().getBytes("UTF-8"));

        String retorno = connect(connection);
        if(retorno.equals(""))
            throw new Exception("Erro na resposta do servidor");

        JSONArray array = new JSONArray(retorno);
        return array;

    }

    public static JSONArray obterJSONAulasProfessor(String ano, String semestre, String email) throws Exception {

        HttpURLConnection connection = prepareConection();
        connection.setRequestProperty("tipo", "aulas_professor");

        OutputStream out = connection.getOutputStream();

        JSONObject json = new JSONObject();

        json.put("ano", ano);
        json.put("semestre", semestre);
        json.put("email", email);

        out.write(json.toString().getBytes("UTF-8"));

        String retorno = connect(connection);
        if(retorno.equals(""))
            throw new Exception("Erro na resposta do servidor");

        JSONArray array = new JSONArray(retorno);
        return array;

    }
}

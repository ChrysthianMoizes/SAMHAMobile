package com.example.chrys.samhamobile.connection;

import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class JSONAulasService extends HttpConnection {

    public static JSONObject obterJSONAulas(String ano, String semestre, String turma_id) throws Exception {

        HttpURLConnection connection = prepareConection();
        OutputStream out = connection.getOutputStream();

        connection.setRequestProperty("tipo", "aulas_turma");

        JSONObject json = new JSONObject();

        json.put("ano", ano);
        json.put("semestre", semestre);
        json.put("turma_id", turma_id);

        out.write(json.toString().getBytes("UTF-8"));

        String retorno = connect(connection);
        if(retorno.equals(""))
            throw new Exception("Erro na resposta do servidor");

        JSONObject jsonR = new JSONObject(retorno);
        return jsonR;

    }
}

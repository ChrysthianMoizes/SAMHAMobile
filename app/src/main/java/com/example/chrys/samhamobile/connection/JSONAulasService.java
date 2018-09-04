package com.example.chrys.samhamobile.connection;

import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class JSONAulasService extends HttpConnection {

    public static JSONObject obterJSONAulas(String ano, String semestre, String turma_id) throws Exception {

        String url = "http://192.168.0.5:8080/WebServiceCadernoDigital/Servico";

        HttpURLConnection connection = prepareConection(url);
        OutputStream out = connection.getOutputStream();

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

package com.example.chrys.samhamobile.connection;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;

public class JSONTurmasService extends HttpConnection {

    public static JSONObject obterJSONTurmas(String ano, String semestre) throws Exception {

        String url = "http://192.168.0.5:8080/WebServiceCadernoDigital/Servico";

        HttpURLConnection connection = prepareConection(url);
        OutputStream out = connection.getOutputStream();

        JSONObject json = new JSONObject();
        json.put("ano", ano);
        json.put("semestre", semestre);

        out.write(json.toString().getBytes("UTF-8"));

        String retorno = connect(connection);
        if(retorno.equals(""))
            throw new Exception("Erro na resposta do servidor");

        JSONObject jsonR = new JSONObject(retorno);
        return jsonR;

    }
}

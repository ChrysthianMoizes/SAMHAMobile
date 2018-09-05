package com.example.chrys.samhamobile.connection;

import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class JSONTurmasService extends HttpConnection {

    public static JSONObject obterJSONTurmas(String ano, String semestre) throws Exception {

        HttpURLConnection connection = prepareConection();
        OutputStream out = connection.getOutputStream();

        connection.setRequestProperty("tipo", "turmas_ativas");

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

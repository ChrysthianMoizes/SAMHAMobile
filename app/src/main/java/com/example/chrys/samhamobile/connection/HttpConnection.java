package com.example.chrys.samhamobile.connection;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {

    protected static int readTimeOut = 15000;
    protected static int conectTimeOut = 15000;
    //200.137.77.137
    //172.16.41.94
    protected static String url = "http://172.16.41.94:8080/WebServiceSAMHA/Services";

    protected static HttpURLConnection prepareConection() throws IOException {

        URL apiEnd = new URL(url);
        HttpURLConnection conexao;

        conexao = (HttpURLConnection) apiEnd.openConnection();
        conexao.setRequestMethod("POST");
        conexao.setReadTimeout(readTimeOut);
        conexao.setConnectTimeout(conectTimeOut);

        return conexao;
    }


    protected static String connect(HttpURLConnection connection) throws IOException {

        InputStream is;

        connection.connect();

        int codigoResposta = connection.getResponseCode();

        if(codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST){
            is = connection.getInputStream();
        }else{
            is = connection.getErrorStream();
        }

        String retorno = converterInputStreamToString(is);

        is.close();
        connection.disconnect();
        return retorno;
    }

    protected static String converterInputStreamToString(InputStream is){

        StringBuffer buffer = new StringBuffer();

        try{
            BufferedReader br;
            String linha;

            br = new BufferedReader(new InputStreamReader(is));
            while((linha = br.readLine())!=null){
                buffer.append(linha);
            }

            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return buffer.toString();
    }


}

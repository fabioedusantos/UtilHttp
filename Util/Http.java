package fabio.prof.testews.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http {
    private static final int tempoLimite = 30; //segundos

    private static String request(String location, String param, String type){
        if(param == null){
            param = "";
        }

        try{
            if(!type.equals("POST") && param.length() > 0) {
                location = location + "?" + param;
            }

            URL url = new URL(location);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader br;
            StringBuilder sb = new StringBuilder();
            String line;

            connection.setRequestMethod(type);
            connection.setReadTimeout(tempoLimite * 1000);

            if(type.equals("POST")) {
                connection.setDoOutput(true);
                Writer writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(param);
                writer.flush();
                writer.close();
            }

            if(connection.getResponseCode() >= 200 && connection.getResponseCode() <= 299){
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else{
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            while((line = br.readLine()) != null){
                sb.append(line);
            }
            br.close();

            return sb.toString();
        } catch (Exception e){
            Log.e("teste", "Http/request; Location: " + location
                    + "; Param: " + param + "; Message: " + e.getMessage());
        }
        return null;
    }

    public static String get(String location, String getParam){
        return request(location, getParam, "GET");
    }

    public static String post(String location, String getParam){
        return request(location, getParam, "POST");
    }

    public static String put(String location, String getParam){
        return request(location, getParam, "PUT");
    }

    public static String delete(String location, String getParam){
        return request(location, getParam, "DELETE");
    }

}
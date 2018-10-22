package fabio.prof.testews.util;

import android.util.Log;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpParam {
    private HashMap<String, String> param = new HashMap<String, String>();

    public HttpParam(){}

    public HttpParam add(String alias, String conteudo){
        try {
            param.put(alias, URLEncoder.encode(conteudo, "UTF-8"));
        } catch (Exception e) {
            Log.e("teste", e.getMessage());
        }
        return this;
    }
    public String getParam() throws Exception{
        String ret = "";
        int i = 0;

        for(Map.Entry<String, String> linha : param.entrySet()){
            String alias = linha.getKey();
            String conteudo = linha.getValue();

            if(i < param.size() - 1){
                ret += alias + "=" + conteudo + "&";
            } else{
                ret += alias + "=" + conteudo;
            }
            i++;
        }

        return ret;
    }
}

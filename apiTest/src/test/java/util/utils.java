package util;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Properties;

public class utils {


    public static String getAPIMethod(String URI, String parameter) throws IOException, URISyntaxException {
        Properties prop = null;
        try {
            readConfigFile cf = new readConfigFile();
            prop = cf.readPropertyFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String baseURL = prop.getProperty("app.petStore_baseURL");

        //Creating a client
        CloseableHttpClient client = HttpClients.createDefault();

        //Create a request
        HttpGet request = new HttpGet(baseURL+URI);

        if(!parameter.equalsIgnoreCase("NA")){
            if(parameter.contains("{")){
               parameter = parameter.replace("{","").replace("}","");
                String[] param = parameter.split("=");
                String key = param[0];
                String value = param[1];
                System.out.println("Key Value is :" + key + "  Value is :" + value);
                request = new HttpGet(baseURL+URI+"/"+value);
                java.net.URI uri = new URIBuilder(request.getURI())
                        .build();
                System.out.println(uri);
                ((HttpRequestBase) request).setURI(uri);

            }else {
                String[] param = parameter.split("=");
                String key = param[0];
                String value = param[1];
                System.out.println("Key Value is :" + key + "  Value is :" + value);
                java.net.URI uri = new URIBuilder(request.getURI())
                        .addParameter(key, value)
                        .build();
                System.out.println(uri);
                ((HttpRequestBase) request).setURI(uri);
            }
        }

        //adding header
        request.addHeader("Content-Type","application/json");

        //Execute request
        CloseableHttpResponse response = client.execute(request);

        int responseCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code is "+responseCode);

        String responseString = EntityUtils.toString(response.getEntity(),"UTF-8");

        System.out.println(responseString);
        client.close();
        String output = "Response Code~"+responseCode+"|"+"Response String~"+responseString;
        return output;


    }


    public static void postAPIMethod(String URI, String body)
            throws ClientProtocolException, IOException {


        Properties prop = null;
        try {
            readConfigFile cf = new readConfigFile();
            prop = cf.readPropertyFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String baseURL = prop.getProperty("app.petStore_baseURL");

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(baseURL+URI);

        //String json = body;
        System.out.println(body);
        StringEntity entity = new StringEntity(body);
        httpPost.setEntity(entity);
        //httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        int responseCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code is "+responseCode);

        String responseString = EntityUtils.toString(response.getEntity(),"UTF-8");

        System.out.println(responseString);
        client.close();
    }

    public String getValuefromMap(Map<String, Object[]> map, String value, int location){
        String retunVal = "";
        for (String key: map.keySet()) {
            if(key.equalsIgnoreCase(value)){
                Object[] v = map.get(key);
                retunVal = v[location].toString();
            }
        }
        return retunVal;
    }

    public int getValuefromMap(Map<String, Object[]> map, String value){
        int retunVal = 0;
        for (String key: map.keySet()) {
            if(key.equalsIgnoreCase(value)){
                Object[] v = map.get(key);
                retunVal = v.length;
            }
        }
        return retunVal;
    }


}

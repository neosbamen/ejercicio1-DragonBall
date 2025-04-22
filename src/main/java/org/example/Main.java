package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {

        String location="https://pokeapi.co/api/v2/pokemon/ditto";

        try {

            URL url = new URL(location);
            HttpsURLConnection connection=(HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode()!= HttpURLConnection.HTTP_OK){

                throw new RuntimeException("Not page found");
            }else {
                String line;
                StringBuilder stringBuilder =new StringBuilder();
                BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));

                while ((line=reader.readLine())!=null){
                    stringBuilder.append(line);
                }
                reader.close();

                JsonObject personajes= JsonParser.parseString(stringBuilder.toString()).getAsJsonObject();
                JsonArray totalPersonajes=personajes.getAsJsonArray("items");
                for (JsonElement obj:totalPersonajes) {
                    JsonObject objetoPersonaje=obj.getAsJsonObject();

                    if (objetoPersonaje.get("name").getAsString().equals("ditto")){
                        System.out.println(objetoPersonaje.get("name"));
                    }
                    /*System.out.println(objetoPersonaje.get("name"));*/
                }
            }



        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }





    }
}
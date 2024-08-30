package Juego;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class JsonObjeto {


    public static void guardarObjetos(JSONArray jsonArray, String archivo) {

        try {
            FileWriter file = new FileWriter(archivo + ".json");
            file.write(jsonArray.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String descargarObjetos(String archivo) {
        String contenido="" ;
        try {
            contenido = new String(Files.readAllBytes(Paths.get(archivo + ".json")));
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return contenido;
    }
}


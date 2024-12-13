/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;
import org.json.JSONObject;

/*
TODO
.- add locking to the files
 */
/**
 *
 * @author moustafa
 */
public class JSONUtils {

    public static JSONArray readFromFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        String t = content.toString();
        JSONArray j = new JSONArray(t);
        return j;
    }

    public static void writeToFile(String filePath, JSONArray jsonArray) throws IOException {
        try (FileWriter writer = new FileWriter(filePath);) {
            writer.write(jsonArray.toString(4)); //indent of 4 spaces
        }
    }

    //could be more generic
    public static ArrayList loadArrayList(JSONObject jsonObject, String key) {
        ArrayList<String> list = new ArrayList<>();

        JSONArray membersArray = jsonObject.getJSONArray(key);
        for (int i = 0; i < membersArray.length(); i++) {
            list.add(membersArray.getString(i));
        }

        return list;
    }

    public static <C extends Collection<String>> C 
        loadCollection(JSONObject jsonObject, String key, Supplier<C> collectionSupplier) {
            
        C collection = collectionSupplier.get();
        
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        for (int i = 0; i < jsonArray.length(); i++) {
            collection.add(jsonArray.getString(i));
        }
        return collection;
    }

}

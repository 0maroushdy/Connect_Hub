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

/*
TODO
add locking to the files
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

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import org.json.*;


/**
 *
 * @author Abdelrahman
 */
public class UserDatabase {
    
    private static UserDatabase user_database;
    private ArrayList <User> users;
    private static int uniqueCounter;
    
    private UserDatabase(){
        this.users = new ArrayList<>();
    }
    
    public int getUniqueCounter(){
        return UserDatabase.uniqueCounter;
    }
    
    
    public static synchronized UserDatabase getInstance(){
        if(user_database == null){
            user_database = new UserDatabase();
            UserDatabase.uniqueCounter = 1;
        }
        return user_database;
    }
    
    public ArrayList <String> getUserIds(){
        ArrayList <String> ids = new ArrayList<>();
        for(User user:users){
            ids.add(user.getUserId());
        }
        return ids;
    }
    
    public ArrayList <User> getUsers(){
        return this.users;
    }
    
    public boolean addUser(User user) throws NoSuchAlgorithmException{
       if(user != null){
        this.users.add(user);
       uniqueCounter++;
       return true;}
       
       return false;
    }
    
    public void saveUsersToFile(String filePath){
       /* StringBuilder jsonBuilder = new StringBuilder();

        jsonBuilder.append("[\n");
        for (int i = 0; i < this.users.size(); i++) {
            User user = this.users.get(i);
            jsonBuilder.append("  {\n");
            jsonBuilder.append("    \"UserId\": \"").append(escapeJson(user.getUserId())).append("\",\n");
            jsonBuilder.append("    \"Email\": \"").append(escapeJson(user.getUserEmail())).append("\",\n");
            jsonBuilder.append("    \"Username\": ").append(user.getUsername()).append("\n");
            jsonBuilder.append("    \"Password\": ").append(user.getPassword()).append("\n");
            jsonBuilder.append("    \"DateOfBirth\": ").append(user.getUserDateOfBirth()).append("\n");
            jsonBuilder.append("    \"Status\": ").append(user.getUserStatus()).append("\n");
            jsonBuilder.append("  }");

            if (i < users.size() - 1) {
                jsonBuilder.append(",");
            }
            jsonBuilder.append("\n");
        }
        jsonBuilder.append("]");

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonBuilder.toString());
            System.out.println("Users saved successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save users to file: " + e.getMessage());
        } */
       
       
       
       
    }

    // Utility method to escape JSON special characters
    /*private static String escapeJson(String text) {
        return text.replace("\"", "\\\"").replace("\\", "\\\\");
    } */
    
    }
    
    
   

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import org.json.*;
import java.io.FileWriter;
import java.io.IOException;


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
    
    public String generateUserHashedPassword(String password) throws NoSuchAlgorithmException{
       MessageDigest mssg = MessageDigest.getInstance("SHA-256");
       byte [] hashedBytes = mssg.digest(password.getBytes());
       StringBuilder hexadecimalString = new StringBuilder();
       for(byte b:hashedBytes){
           String hexadecimal = Integer.toHexString(0xff & b);
           if(hexadecimal.length() == 1){
               hexadecimalString.append('0');
           }
           hexadecimalString.append(hexadecimal);
       }
       return hexadecimalString.toString();
   }
    
     public boolean userLogin(String userId,String password) throws NoSuchAlgorithmException{  
       for(User user:this.users){
           if(user.getUserId().equals(userId) && user.getUserPassword().equals(generateUserHashedPassword(password))){
               user.setUserStatus("online");
               saveUsersToFile("UserDatabase.txt");
               return true;
           }
       }
       return false;
  }
    
    public void saveUsersToFile(String filePath){
       JSONArray jsonArray = new JSONArray();
       for(User user:this.users){
           jsonArray.put(user.toJSON());
       }
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonArray.toString(4)); // Indented with 4 spaces
            System.out.println("User data saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
    
    
   

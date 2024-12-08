/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.UserPackage;

import static Files.FILEPATHS.USERFILE;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import org.json.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Abdelrahman
 */
public final class UserDatabase {

    private static UserDatabase user_database;
    private  ArrayList <User> users;
    private int uniqueCounter;

    private UserDatabase() {
        this.users = new ArrayList<>();
        uniqueCounter = 1;
    }

    public int getUniqueCounter() {
        return uniqueCounter;
    }

    public static synchronized UserDatabase getInstance() {
        if (user_database == null) {
            user_database = new UserDatabase();
            
        }
        return user_database;
    }

    public ArrayList <User> getUsers() {
        return this.users;
    }

   public boolean addUser(User user) throws NoSuchAlgorithmException {
    if (user != null) {
        this.users.add(user);
        // Extract numeric part of userId and ensure counter stays ahead
        String[] parts = user.getUserId().split("-");
        if (parts.length == 2) {
            try {
                int idCounter = Integer.parseInt(parts[1]);
                uniqueCounter = Math.max(uniqueCounter, idCounter + 1);
            } catch (NumberFormatException e) {
                System.err.println("Invalid UserId format for counter adjustment.");
            }
        }
        return true;
    }
    return false;
}
   

    public User getUserByEmail(String userEmail) {
        for (User user : this.users) {
            if (user.getUserEmail().equals(userEmail)) {
                return user;
            }
        }
        return null;
    }
    
    public User getUser(String userId){
        for(User user:this.users){
            if(user.getUserId().equals(userId)){
                return user;
            }
        }
        return null;
    }
    

    public boolean userLogin(String email, String password) throws NoSuchAlgorithmException {
        User user = UserDatabase.getInstance().getUserByEmail(email);
        if (user == null) {
            return false;
        }
        
        if (!user.getUserPassword().equals(HashingUtil.generateUserHashedPassword(password))) {
            System.out.println("password incorrect");
            return false;
        }

        user.setUserStatus("online");
        UserSignupSingleton.getInstance().setUser(user);
        saveUsersToFile(USERFILE);
        return true;
    }

    public void saveUsersToFile(String filePath) {
        JSONArray jsonArray = new JSONArray();
        for (User user : this.users) {
        JSONObject userJson = user.toJSON();
        jsonArray.put(userJson);
        }
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonArray.toString(4)); // Indented with 4 spaces
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public void loadUsersFromFile(String filePath) {
       this.users = new ArrayList <>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }

        JSONArray jsonArray = new JSONArray(jsonBuilder.toString());
        int maxCounter = 0; // Track the maximum unique ID counter value
        for (int i = 0; i < jsonArray.length(); i++) {
           JSONObject jsonObject = jsonArray.getJSONObject(i);
          // String dateOfBirth = jsonObject.getString("DateOfBirth");
          // String status = jsonObject.getString("Status");
         //  String email = jsonObject.getString("Email");
         //  String username = jsonObject.getString("Username");
         //   String userId = jsonObject.getString("UserId");
         //  String password = jsonObject.getString("Password");
        //   LocalDate date = LocalDate.parse(dateOfBirth, DateTimeFormatter.ISO_LOCAL_DATE);
           User user = User.fromJson(jsonObject);
           addUser(user);
            
            // Extract numeric part of userId to determine uniqueCounter
            String[] parts = user.getUserId().split("-");
            if (parts.length == 2) {
                try {
                    int idCounter = Integer.parseInt(parts[1]);
                    maxCounter = Math.max(maxCounter, idCounter);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid UserId format: " + user.getUserId());
                }
            }
        //  addUser(User.UserFactory.create(userId,email, username, password, date, status, false));
        }
        // Update uniqueCounter to avoid duplicates
          uniqueCounter = maxCounter + 1;
        
    } catch (IOException e) {
        System.err.println("Error reading file: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Error parsing JSON: " + e.getMessage());
      }
    }
    
    public void reloadUsersFromFile(String filePath) {
        this.users = new ArrayList <>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            ArrayList<User> temp = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(jsonBuilder.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String dateOfBirth = jsonObject.getString("DateOfBirth");
                String status = jsonObject.getString("Status");
                String email = jsonObject.getString("Email");
                String username = jsonObject.getString("Username");
                String userId = jsonObject.getString("UserId");
                String password = jsonObject.getString("Password");
                LocalDate date = LocalDate.parse(dateOfBirth, DateTimeFormatter.ISO_LOCAL_DATE);
                temp.add(User.UserFactory.create(userId,email, username, password, date, status,false));
            }
            this.users = temp;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }
    }  
}

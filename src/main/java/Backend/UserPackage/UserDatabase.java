/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.UserPackage;

import Backend.UserPackage.User.UserFactory;
import static Files.FILEPATHS.USERFILE;
import java.io.BufferedReader;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import org.json.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Abdelrahman
 */
public final class UserDatabase {

    private static UserDatabase user_database;
    private ArrayList<User> users;
    private static int uniqueCounter;

    private UserDatabase() {
        this.users = new ArrayList<>();
    }

    public int getUniqueCounter() {
        return UserDatabase.uniqueCounter;
    }

    public static synchronized UserDatabase getInstance() {
        if (user_database == null) {
            user_database = new UserDatabase();
            UserDatabase.uniqueCounter = 1;
        }
        return user_database;
    }

    public ArrayList<String> getUserIds() {
        ArrayList<String> ids = new ArrayList<>();
        for (User user : users) {
            ids.add(user.getUserId());
        }
        return ids;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public boolean addUser(User user) throws NoSuchAlgorithmException {
        if (user != null) {
            this.users.add(user);
            uniqueCounter++;
            return true;
        }

        return false;
    }
    
    public User getUser(String userId){
        for(User user:this.users){
            if(user.getUserId().equals(userId)) return user;
        }
        return null;
    }


    public boolean userLogin(String userId, String password) throws NoSuchAlgorithmException {
        for (User user : this.users) {
            if (user.getUserId().equals(userId) && user.getUserPassword().equals(HashingUtil.generateUserHashedPassword(password))) {
                user.setUserStatus("online");
                saveUsersToFile(USERFILE);
                MainUser.setMainUser(user);
                return true;
            }
        }
        return false;
    }

    public void saveUsersToFile(String filePath) {
        JSONArray jsonArray = new JSONArray();
        for (User user : this.users) {
            jsonArray.put(user.toJSON());
        }
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonArray.toString(4)); // Indented with 4 spaces
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadUsersFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

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
                addUser(User.UserFactory.create(email, username, password, date));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }

    }

    public User getUserFromId(String userId) {
        for(User u : this.users){
            if(u.getUserId().equals(userId))
                return u;
        }
        return null;
    }

}

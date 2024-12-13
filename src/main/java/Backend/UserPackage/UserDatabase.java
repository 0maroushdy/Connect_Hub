
package Backend.UserPackage;

import Backend.GroupiPackage.Groupi;
import Backend.GroupiPackage.GroupDatabase;
import Backend.UserProfilePackage.UserProfile;
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
import java.util.HashSet;

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
    
    public ArrayList <User> getUsersByUsername(String username){
        ArrayList <User> ans = new ArrayList<>();
        for(User user:this.users){
            if(user.getUsername().equals(username)) ans.add(user);
        }
        return ans;
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
            System.out.println("3 .... user.getUserProfile().getProfileBio()\n");
            jsonArray.put(user.toJSON());
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
       GroupDatabase.getInstance().getGroups().removeAll(GroupDatabase.getInstance().getGroups());
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }

        JSONArray jsonArray = new JSONArray(jsonBuilder.toString());
        int maxCounter = 0; // Track the maximum unique ID counter value
        int maxCounter2 = 0;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String dateOfBirth = jsonObject.getString("DateOfBirth");
            String status = jsonObject.getString("Status");
            String email = jsonObject.getString("Email");
            String username = jsonObject.getString("Username");
            String userId = jsonObject.getString("UserId");
            String password = jsonObject.getString("Password");
            LocalDate date = LocalDate.parse(dateOfBirth, DateTimeFormatter.ISO_LOCAL_DATE);
            
            JSONObject ProfileObj = (JSONObject) jsonObject.get("Profile"); // added for the profile 
            UserProfile Profile = new UserProfile(ProfileObj); // using the constructor recving JSONobj

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
       
            addUser(User.UserFactory.create( userId, email, username, password, date, status, Profile, false));
        //  addUser(User.UserFactory.create(userId,email, username, password, date, status, false));
        }
         for(Groupi group:GroupDatabase.getInstance().getGroups()){
            String[] parts2 = group.getGroupId().split("-");
            if (parts2.length == 2) {
                try {
                    int idCounter2 = Integer.parseInt(parts2[1]);
                    maxCounter2 = Math.max(maxCounter2, idCounter2);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid groupId format: " + group.getGroupId());
                }
            }
            }
        
        
        // Update uniqueCounter to avoid duplicates
          uniqueCounter = maxCounter + 1;
          GroupDatabase.getInstance().setCounter(maxCounter2+1);
        
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
                
                JSONObject ProfileObj = (JSONObject) jsonObject.get("Profile"); // added for the profile 
                UserProfile Profile = new UserProfile(ProfileObj); // using the constructor recving JSONobj
                
System.out.println("test 1 ------"+Profile.getProfileBio());
                temp.add(User.UserFactory.create( userId, email, username, password, date, status, Profile, false));
System.out.println("test 2 ------"+Profile.getProfileBio());
            }
            this.users = temp;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }
    }  
}

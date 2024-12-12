//<<<<<<< HEAD
//package Backend.UserProfilePackage;
//
//import static Files.FILEPATHS.PROFILEFILE;
//import Backend.UserPackage.HashingUtil;
//import Backend.UserPackage.User;
//=======
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Backend.UserProfilePackage;
//
//import Backend.UserPackage.HashingUtil;
//>>>>>>> 3ea4a5e01255111459d5f8a73b7ad853041ab929
//import Backend.UserPackage.UserDatabase;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import java.io.*;
//import java.security.NoSuchAlgorithmException;
//<<<<<<< HEAD
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//=======
//>>>>>>> 3ea4a5e01255111459d5f8a73b7ad853041ab929
//
//import java.util.ArrayList;
//import org.json.JSONException;
//
///**
// *
// * @author Omar
// */
//public final class ProfileDatabase {
//
//    private static ProfileDatabase userProfileDatabase;
//<<<<<<< HEAD
//    private ArrayList<UserProfile> profiles;
//    private static int uniqueCounter;
//
//    
//=======
//    private ArrayList <UserProfile> profiles;
//    private static int uniqueCounter;
//
//>>>>>>> 3ea4a5e01255111459d5f8a73b7ad853041ab929
//    // --- Singleton Pattern Applying  ---------------
//    private ProfileDatabase() {
//        this.profiles = new ArrayList<>();
//    }
//
//    public static synchronized ProfileDatabase getInstance() {
//        if (userProfileDatabase == null) {
//            userProfileDatabase = new ProfileDatabase();
//<<<<<<< HEAD
//=======
//            uniqueCounter = 1;  // Set starting point for unique counter
//>>>>>>> 3ea4a5e01255111459d5f8a73b7ad853041ab929
//        }
//        return userProfileDatabase;
//    }
//
//    public ArrayList<UserProfile> getProfiles() {
//        return this.profiles;
//    }
//<<<<<<< HEAD
//  
//    
//    // ------ Save to file and Load from it ---------------
//    public void saveProfilesToFile(String filePath) {
//        JSONArray jsonArray = new JSONArray();
//        for (UserProfile profile : this.profiles) {
//            jsonArray.put(profile.toJSON());
//        }
//        try (FileWriter file = new FileWriter(filePath)) {
//            file.write(jsonArray.toString(4)); // Indentation with 4 spaces 
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public void loadProfilesFromFile(String filePath) throws FileNotFoundException, IOException {
//        this.profiles = new ArrayList <>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            StringBuilder jsonBuilder = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                jsonBuilder.append(line);
//            }
//        JSONArray jsonArray = new JSONArray(jsonBuilder.toString());
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jsonObject = jsonArray.getJSONObject(i);
//            String userId = jsonObject.getString("UserId");
//            String profilePhoto = jsonObject.getString("profilePhoto");
//            String profileCover = jsonObject.getString("profileCover");
//            String bio = jsonObject.getString("bio");
//
//            addProfile(new UserProfile(userId, profilePhoto, profileCover, bio));
//        }
//        } catch (IOException e) {
//            System.err.println("Error reading file: " + e.getMessage());
//        } catch (Exception e) {
//            System.err.println("Error parsing JSON: " + e.getMessage());
//        }
//    }
//    
//    public boolean addProfile(UserProfile profile) throws NoSuchAlgorithmException {
//        if (profile != null) {
//            this.profiles.add(profile);
//            return true;
////            saveProfilesToFile(PROFILEFILE);
//        }
//        return false;
//     }
//    
//=======
////
//>>>>>>> 3ea4a5e01255111459d5f8a73b7ad853041ab929
//    public boolean saveProfile(String userId, JSONObject profileData) {
//        UserProfile profile = new UserProfile(userId, profileData);
//        this.profiles.add(profile);
//        return saveProfilesToFile("profiles.json");
//    }
//
//<<<<<<< HEAD
//    public JSONObject getUserProfileJSON(String userId) {
//=======
//    public JSONObject loadProfile(String userId) {
//>>>>>>> 3ea4a5e01255111459d5f8a73b7ad853041ab929
//        for (UserProfile profile : this.profiles) {
//            if (profile.getUserId().equals(userId)) {
//                return profile.toJSON();
//            }
//        }
//        return null;
//    }
//
//    // -----------** Updaters **-----------
//    public boolean updateProfileField(String userId, String field, String value) {
//        UserProfile profile = this.getUserProfile(userId);
//        if (profile == null) {
//            return false;
//        }
//        
//        profile.updateField(field, value);
//        return saveProfilesToFile("profiles.json");
//    }
//
//    public boolean deleteProfile(String userId) {
//        UserProfile profile = this.getUserProfile(userId);
//        if (profile == null) {
//            return false;
//        }
//
//        this.profiles.remove(profile);
//        return saveProfilesToFile("profiles.json");
//    }
//
//    // -----** Saving all profiles into a file ** -------
//    public boolean saveProfilesToFile(String filePath) {
//        JSONArray jsonArray = new JSONArray();
//        for (UserProfile profile : this.profiles) {
//            jsonArray.put(profile.toJSON());
//        }
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//<<<<<<< HEAD
//            writer.write(jsonArray.toString(4)); // print with 4 spaces
//=======
//            writer.write(jsonArray.toString(4)); // Pretty print with 4 spaces
//>>>>>>> 3ea4a5e01255111459d5f8a73b7ad853041ab929
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // -----** Loading all profiles From the file ** -------
//    public void loadProfilesFromFile(String filePath) {
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            StringBuilder jsonBuilder = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                jsonBuilder.append(line);
//            }
//
//            JSONArray jsonArray = new JSONArray(jsonBuilder.toString());
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject profileData = jsonArray.getJSONObject(i);
//                String userId = profileData.getString("userId");
//                UserProfile profile = new UserProfile(userId, profileData);
//                this.profiles.add(profile);
//            }
//        } catch (IOException | JSONException e) {
//            System.err.println("Error loading profiles from file: " + e.getMessage());
//        }
//    }
//
//    // ---------- ** password updaing & verificatoin ** --------------
//    public boolean verifyPassword(String userId, String hashedQueryPassword) throws NoSuchAlgorithmException {
//        String Password = UserDatabase.getInstance().getUser(userId).getUserPassword();
//        String queryPass = HashingUtil.generateUserHashedPassword(hashedQueryPassword);
//        
//        return Password.equals(queryPass);
//    }
//
//    public boolean updatePassword(String userId, String Password) throws NoSuchAlgorithmException {
//        UserDatabase.getInstance().getUser(userId).setUserPassword(Password);
//        this.saveUser(userId);
//        return true;
//    }
//
//    public boolean saveUser(String userId) {
//        UserDatabase.getInstance().saveUsersToFile(userId); // must be a saving method !!-------- working on
//        return true;
//    }
//
//    private UserProfile getUserProfile(String userId) {
//        for (UserProfile profile : this.profiles) {
//            if (profile.getUserId().equals(userId)) {
//                return profile;
//            }
//        }
//        return null;
//    }
//}

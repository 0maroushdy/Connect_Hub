/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.UserProfilePackage;

import Backend.UserPackage.HashingUtil;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omar
 */
public class ProfileManager implements IProfileUpdater {
    
    private Database database; // Dependency injection for database

    public ProfileManager(Database db) {
        this.database = db;
    }

    @Override
    public boolean updatePhoto(String userId, String photoPath) {
        // Validate and update profile photo path
        return database.updateUserField(userId, "profilePhoto", photoPath);
    }

    @Override
    public boolean updateCover(String userId, String coverPath) {
        // Validate and update cover photo path
        return database.updateUserField(userId, "profileCover", coverPath);
    }

    @Override
    public boolean updateBio(String userId, String bio) throws Exception {
        if (bio.length() > 250) throw new Exception("Bio exceeds 250 characters");
        return database.updateUserField(userId, "profileBio", bio);
    }

    @Override
    public boolean updatePassword(String userId, String oldPassword, String newPassword) {
        String hashedOldPassword;
        
        try {
            hashedOldPassword = HashingUtil.generateUserHashedPassword(oldPassword);
            if (!database.verifyPassword(userId, hashedOldPassword)) return false;

            String hashedNewPassword = HashingUtil.generateUserHashedPassword(newPassword);
            return database.updateUserField(userId, "password", hashedNewPassword);
        }
        catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ProfileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean saveProfile(String userId) {
        // Save the updated profile to the database
        return database.saveUser(userId);
    }
}
    


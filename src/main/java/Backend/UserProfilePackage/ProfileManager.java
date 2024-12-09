//package Backend.UserProfilePackage;
//
//import static Files.FILEPATHS.PROFILEFILE;
//import Backend.UserPackage.HashingUtil;
//import java.security.NoSuchAlgorithmException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.json.JSONObject;
//
///**
// *
// * @author Omar
// */
//public class ProfileManager implements IProfileUpdater {
//    
//    private static ProfileDatabase database; 
//
//    public ProfileManager(ProfileDatabase db) {
//        this.database = db;
//    }
//
//    @Override
//    public boolean updatePhoto(String userId, String photoPath) {
//        // Validate and update profile photo path
//        return database.updateProfileField(userId, "profilePhoto", photoPath);
//    }
//
//    @Override
//    public boolean updateCover(String userId, String coverPath) {
//        // Validate and update cover photo path
//        return database.updateProfileField(userId, "profileCover", coverPath);
//    }
//
//    @Override
//    public boolean updateBio(String userId, String bio){
//        return database.updateProfileField(userId, "profileBio", bio);
//    }
//
//    @Override
//    public boolean updatePassword(String userId, String oldPassword, String newPassword) {
//        String hashedOldPassword;
//        
//        try {
//            hashedOldPassword = HashingUtil.generateUserHashedPassword(oldPassword);
//            if (!database.verifyPassword(userId, hashedOldPassword)) return false;
//
//            String hashedNewPassword = HashingUtil.generateUserHashedPassword(newPassword);
//            return database.updateProfileField(userId, "password", hashedNewPassword);
//        }
//        catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(ProfileManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean saveProfile(String userId) {
//        return database.saveUser(userId);
//    }
//    
//
//    
//}
//    
//

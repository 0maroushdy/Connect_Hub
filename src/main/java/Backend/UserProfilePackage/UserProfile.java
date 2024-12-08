package Backend.UserProfilePackage;

import static Files.FILEPATHS.PROFILEFILE;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Omar
 */
public class UserProfile {

    private String profilePhoto;
    private String profileCover;
    private String profileBio;
    private String userId;
    
//    private User user;

    // -----------** Constructor **-----------
    public UserProfile(String photo, String cover, String bio) {
        profilePhoto = photo;
        profileCover = cover;
        profileBio = bio;
        // must handeling the userId .......... !!
    }

    public UserProfile(String Id, String photo, String cover, String bio) {
        profilePhoto = photo;
        profileCover = cover;
        profileBio = bio;
        userId = Id;
    }

    public UserProfile(String userId, JSONObject profileData) {
        this.userId = userId;
        this.profilePhoto = profileData.optString("profilePhoto", "");
        this.profileCover = profileData.optString("profileCover", "");
        this.profileBio = profileData.optString("profileBio", "");
    }

    // -----------** Getters **-----------
    public String getProfilePhoto() {
        return profilePhoto;
    }

    public String getProfileCover() {
        return profileCover;
    }

    public String getProfileBio() {
        return profileBio;
    }

    public String getUserId() {
        return userId;
    }

    // -----------** Some Setters **-----------
    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setProfileCover(String profileCover) {
        this.profileCover = profileCover;
    }

    public void setProfileBio(String profileBio) throws overSizeInputException {
        if (profileBio.length() > 250) {
            throw new overSizeInputException("Bio must be less than 250 letter");
        }
        this.profileBio = profileBio;
    }

    //--------* Update a specific field of the profile --- by the profile DB *--------
    public void updateField(String field, String value) {
        switch (field) {
            case "profilePhoto" -> {
                this.setProfilePhoto(value);
            }

            case "profileCover" -> {
                this.setProfileCover(value);
            }

            case "profileBio" -> {
                try {
                    this.setProfileBio(value);
                } catch (overSizeInputException ex) {
                    Logger.getLogger(UserProfile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            default ->
                throw new IllegalArgumentException("Unknown field erroer: " + field);
        }
    }

    // Convert the profile to a JSON object
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("userId", this.userId);
        json.put("profilePhoto", this.profilePhoto);
        json.put("profileCover", this.profileCover);
        json.put("bio", this.profileBio);
        return json;
    }
    
    

}

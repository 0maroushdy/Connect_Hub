
package Backend.UserProfilePackage;

import java.util.ArrayList;
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
    private JSONObject profileData;
//    private User user;
    
    // -----------** Constructor **-----------
    public UserProfile(String photo, String cover, String bio){
        profilePhoto = photo;
        profileCover = cover;
        profileBio   = bio;
    }
    
    public UserProfile(){
        profilePhoto = null;
        profileCover = null;
        profileBio   = null;
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

    public JSONObject getProfileData() {
        return profileData;
    }
    
    
    // -----------** Some Setters **-----------
    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setProfileCover(String profileCover) {
        this.profileCover = profileCover;
    }

    public void setProfileBio(String profileBio) throws overSizeInputException {
        if(profileBio.length() > 250)
            throw new overSizeInputException("Bio must be less than 250 letter");
        this.profileBio = profileBio;
    }

    
    //--------* Update a specific field of the profile --- by the profile DB *--------
    public void updateField(String field, Object value) {
        switch (field) {
            case "profilePhoto":
                if (value instanceof String) {
                    this.profilePhoto = (String) value;
                } else {
                    throw new IllegalArgumentException("Invalid type for profilePhoto. Expected String.");
                }
                break;

            case "profileCover":
                if (value instanceof String) {
                    this.profileCover = (String) value;
                } else {
                    throw new IllegalArgumentException("Invalid type for profileCover. Expected String.");
                }
                break;

            case "profileBio":
                if (value instanceof String) {
                    this.profileBio = (String) value;
                } else {
                    throw new IllegalArgumentException("Invalid type for bio. Expected String.");
                }
                break;

            default:
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

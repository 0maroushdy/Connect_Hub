
package BackEnd;

import java.util.ArrayList;

/**
 *
 * @author Omar
 */
public class UserProfile {
    private String profilePhoto;
    private String profileCover;
    private String profileBio;
    private User user;
//    private ArrayList<User> friends ; // may be better to impelement in User Calss
    
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
    
    // -----------** Updaters **-----------
    public void setProfilePhoto(String profilePhoto) {
        // example: C:\iamr\dsfgda\dfsgsgfd.jpg , https://aljkfef.sdf --> so we can use any String without validatoin
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
    
    public boolean updatePassword(String oldPass, String newPass){
        if( user.getUserPassword().equal(generateUserHashedPassword(oldPass)) ){    
            user.setUserPassword(newPass);
            return true;
        }
        return false;
    }
    
    // -----** Saving user with updated info 
    public boolean updateUser(){
        
    }
    
    
    
}

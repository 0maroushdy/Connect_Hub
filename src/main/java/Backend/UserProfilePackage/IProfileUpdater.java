/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.UserProfilePackage;

/**
 *
 * @author Omar
 */

public interface IProfileUpdater {
    
    boolean updatePhoto(String userId, String photoPath);
    boolean updateCover(String userId, String coverPath);
    boolean updateBio(String userId, String bio) throws Exception;
    boolean updatePassword(String userId, String oldPassword, String newPassword);
    boolean saveProfile(String userId);
    
}

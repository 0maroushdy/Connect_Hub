/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.NotificationPackage;

/**
 *
 * @author Abdelrahman
 */
public class LikedPostNotification extends UserNotification{
    
     public LikedPostNotification(String message, String Id) {
        super(message, Id, "likeNotification");
    }
    public LikedPostNotification(String message) {
        super(message);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.NotificationPackage;

/**
 *
 * @author Abdelrahman
 */
public class AddedCommentNotification extends UserNotification{
    
    public AddedCommentNotification(String message, String Id) {
        super(message, Id, "commentNotification");
    }
    public AddedCommentNotification(String message) {
        super(message);
    }
    
}

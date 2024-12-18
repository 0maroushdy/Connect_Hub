/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.NotificationPackage;

/**
 *
 * @author Abdelrahman
 */
public class ReceivedChatMessageNotification extends UserNotification{
    
     public ReceivedChatMessageNotification(String message, String Id) {
        super(message, Id, "messageNotification");
    }
    public ReceivedChatMessageNotification(String message) {
        super(message);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.NotificationPackage;

/**
 *
 * @author Omar
 */
public class FriendRequestNotification  extends ANotification {
        public FriendRequestNotification(String message) {
        super(message);
    }

    @Override
    public void notifyUser() {
        System.out.println("Friend Request: " + getMessage()); // i'm gonna replace it int the frontE
    }
}
    


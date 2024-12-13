/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.NotificationPackage;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Omar
 */
public class NotificationManager {
    private final ArrayList<UserNotification> notifications;   
    
    // -----------** Constructor **--------------
    public NotificationManager() {
        notifications = new ArrayList<>();
    }
    
    public NotificationManager(ArrayList<UserNotification> notis) {
        notifications = notis;
    }

    // -----------** Adding Noti. **--------------
    public void addNotification(UserNotification notification ) {
        notification.setId( this.generateNotificatoinId());
        notifications.add( notification);
    }

    // -----------** Getting Noti. **--------------
    public ArrayList<UserNotification> getNotiList(){
        return this.notifications;
    }
    
    // -----------** Adding EXTRA Noti. **--------------
    public void addFriendRequestNotification(String message) {
        this.addNotification(new FriendRequestNotification(message));
    }

    public void addGroupActivityNotification(String message) {
        this.addNotification(new GroupActivityNotification(message));
    }

    public void addNewPostNotification(String message) {
        this.addNotification(new NewPostNotification(message));
    }

    
    // -----------** Notification Id generator **--------------
    public String generateNotificatoinId(){
       return  Integer.toString(notifications.size()+1);
    }
    
    
    // -----------** Sendign and reviving data**--------------
    public JSONArray toJSON() {
      JSONArray notificationArray = new JSONArray();
        for (UserNotification noti : this.notifications) {
            notificationArray.put(noti.toJSON()); 
        }
        return notificationArray;
    }
    
//    public JSONArray fromJSON(JSONArray arr) {
//      
//      for (int i = 0; i < arr.length(); i++) {
//        String friendId = arr.getString(i);
//        user.friends.add(friendId);
//      
//      JSONArray notificationArray = new JSONArray();
//        for (UserNotification noti : this.notifications) {
//            notificationArray.put(noti.toJSON()); 
//        }
//        return notificationArray;
//    }

    
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.NotificationPackage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Omar
 */
class NotificationManager {
    private final List<ANotification> notifications;

    public NotificationManager() {
        notifications = new ArrayList<>();
    }

    public void addNotification(ANotification notification) {
        notifications.add(notification);
    }

    public void fetchNotifications() {
        System.out.println("Fetching Notifications..."); // i'm gonna replace it int the frontE
        for (ANotification notification : notifications) {
            notification.notifyUser();
        }
        notifications.clear();
    }
    
    // fn. ffor the notification service between users and the prog.
    public void sendFriendRequestNotification(String message) {
        this.addNotification(new FriendRequestNotification(message));
    }

    public void sendGroupActivityNotification(String message) {
        this.addNotification(new GroupActivityNotification(message));
    }

    public void sendNewPostNotification(String message) {
        this.addNotification(new NewPostNotification(message));
    }

    public void refreshNotifications() {
        this.fetchNotifications();
    }
    
    
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.NotificationPackage;

/**
 *
 * @author Omar
 */
abstract class ANotification {
    private String message;
    private boolean seenStatus = false; // 0 means not seen, and 1 means seen. 

    public ANotification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean getSeenStatus(){
        return seenStatus;
    }
    public abstract void notifyUser();
}

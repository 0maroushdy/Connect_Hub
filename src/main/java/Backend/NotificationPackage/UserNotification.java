/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.NotificationPackage;

import Backend.UserProfilePackage.UserProfile;
import org.json.JSONObject;

/**
 *
 * @author Omar
 */
 public class UserNotification {
    private String message;
    private String msgID;
    private boolean seenStatus; // 0 means not seen, and 1 means seen. 

    
    // -----------** Constructors **---------------
    public UserNotification(String message) {
        this.message = message;
        this.seenStatus = false;
    }
    
    public UserNotification(String message, String Id) {
        this.message = message;
        this.msgID = Id;
        this.seenStatus = false;
    }
    
    public UserNotification(String message, String Id, boolean st) {
        this.message = message;
        this.msgID = Id;
        this.seenStatus = st;
    }
    
    public UserNotification(JSONObject jo) {
        this.message = jo.getString("message");
        this.msgID = jo.getString("msgID");
        this.seenStatus = jo.getBoolean("seenStatus");
    }
    
    // -----------** Getters **---------------
    public String getMessage() {
        return message;
    }

    public boolean getSeenStatus(){
        return seenStatus;
    }
    
    // -----------** Setters **---------------
    public void setSeenStatus(boolean st){
        this.seenStatus = st;
    }
    public void setId(String st){
        this.msgID = st;
    }
    
    // -----------** Actoins **---------------
    // to be extended in the next Lab >> L 11
    
    // Convert the Notificaton to a JSON object 
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("message", this.message);
        json.put("msgID", this.msgID);
        json.put("seenStatus", this.seenStatus);
        return json;
    }
    
    public UserNotification fromJSON(JSONObject json) {
        boolean seenSt = false;
        if(json.getString("seenStatus") == "true")
            seenSt = true;
        UserNotification notification = new UserNotification(json.getString("message"),
                              json.getString("msgID") 
                             ,seenSt);
        return notification;
    }
    
    
    
}

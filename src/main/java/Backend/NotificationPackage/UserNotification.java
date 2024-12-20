/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.NotificationPackage;

import Backend.UserProfilePackage.UserProfile;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author Omar
 */
 public class UserNotification {
    private String message;
    private String msgID;
    private boolean seenStatus; // 0 means not seen, and 1 means seen. 
    private String Type;

    
    // -----------** Constructors **---------------
    public UserNotification(String message) {
        this.message = message;
        this.seenStatus = false;
        this.Type = "default";
    }
    
    public UserNotification(String message, String Id, String type) {
        this.message = message;
        this.msgID = Id;
        this.Type = type;
        this.seenStatus = false;
    }
    
    public UserNotification(String message, String Id, boolean st, String type) {
        this.message = message;
        this.msgID = Id;
        this.seenStatus = st;
        this.Type = type;
    }
    
    public UserNotification(JSONObject jo) {
        this.message = jo.getString("message");
        this.msgID = jo.getString("msgID");
        this.seenStatus = jo.getBoolean("seenStatus");
        this.Type = jo.getString("type");
    }
    
    // -----------** Getters **---------------
    public String getMessage() {
        return message;
    }

    public boolean getSeenStatus(){
        return seenStatus;
    }
    
    public String getType() {
        return Type;
    }
    // -----------** Setters **---------------
    public void setSeenStatus(boolean st){
        this.seenStatus = st;
    }
    public void setId(String st){
        this.msgID = st;
    }
    
    public void setType(String st){
        this.Type = st;
    }
    
    // -----------** Actoins **---------------
    // to be extended in the next Lab >> L 11
    
    // Convert the Notificaton to a JSON object 
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("message", this.message);
        json.put("msgID", this.msgID);
        json.put("seenStatus", this.seenStatus);
        json.put("type", this.Type);
        return json;
    }
    
    public UserNotification fromJSON(JSONObject json) {
        boolean seenSt = false;
        if(json.getBoolean("seenStatus") == true)
            seenSt = true;
        UserNotification notification = new UserNotification(json.getString("message"),
                              json.getString("msgID"), seenSt, json.getString("type") );
        return notification;
        
    }
    
    
    
}

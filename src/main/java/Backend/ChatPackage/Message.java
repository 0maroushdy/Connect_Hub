/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ChatPackage;

import Backend.GroupPackage.Group;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Abdelrahman
 */
public class Message {
    
    private String messageId;
    private String messageSenderId;
    private String messageFriendId;
    private String messageContent;
    
    private Message(String messageId,String messageSenderId,String messageFriendId,String messageContent){
        this.messageId = messageId;
        this.messageSenderId = messageSenderId;
        this.messageFriendId = messageFriendId;
        this.messageContent = messageContent;
    }

    private Message() {
        
    }
          /* Getters */
    public String getMessageId(){
        return this.messageId;
    }
    
    public String getMessageSenderId(){
        return this.messageSenderId;
    }
    
    public String getMessageFriendId(){
        return this.messageFriendId;
    }
    
    public String getMessageContent(){
        return this.messageContent;
    }
    
               /* Setters */
    public void setMessageId(String messageId){
        this.messageId = messageId;
    }
    
    public void setMessageSenderId(String messageSenderId){
        this.messageSenderId = messageSenderId;
    }
    
    public void setMessageFriendId(String messageFriendId){
        this.messageFriendId = messageFriendId;
    }
    
    public void setMessageContent(String messageContent){
        this.messageContent = messageContent;
    }
    
      public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageId",this.messageId);
        jsonObject.put("messageSenderId",this.messageSenderId);
        jsonObject.put("messageFriendId", this.messageFriendId);
        jsonObject.put("messageContent", this.messageContent);
        return jsonObject;
    }
      
     public static Message fromJSON(JSONObject object){
        Message message = new Message();
        message.messageId = object.getString("messageId");
        message.messageSenderId = object.getString("messageSenderId");
        message.messageFriendId = object.getString("messageFriendId");
        message.messageContent = object.getString("messageContent");
        return message;
     }
    
    
    public static class MessageBuilder{
        
        public static Message messageCreate(String messageId,String messageSenderId,String messageFriendId,String messageContent){
            return new Message(messageId,messageSenderId,messageFriendId,messageContent);
        }

   }
    
}

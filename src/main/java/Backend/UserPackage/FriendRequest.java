/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.UserPackage;

import org.json.JSONObject;

/**
 *
 * @author Abdelrahman
 */
public class FriendRequest {
    private String requestSenderId;
    private String requestReceiverId;
    private Status requestStatus;
    public enum Status{
        Accepted, Declined, Pending
    }
    
     public FriendRequest(String requestsenderId,String requestReceiverId, Status requestStatus) {
        this.requestSenderId = requestsenderId;
        this.requestReceiverId = requestReceiverId;
        this.requestStatus = requestStatus;
    }
     
     public FriendRequest(){
         
     }
     
           /* Getters */
    
    public Status getRequestStatus(){
        return this.requestStatus;
    }
    
    public String getRequestSenderId(){
        return this.requestSenderId;
    }
    
    public String getRequestReceiverId(){
        return this.requestReceiverId;
    }
    
               /* Setters */
    
    public void setRequestStatus(Status requestStatus){
        this.requestStatus = requestStatus;
    }
    
    public void setRequestSenderId(String requestSenderId){
        this.requestSenderId = requestSenderId;
    }
    
    public void setRequestReceiverId(String requesrReceieverId){
        this.requestReceiverId = requestReceiverId;
    }
    
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("SenderUserId", this.requestSenderId);
        jsonObject.put("ReceiverUserId", this.requestReceiverId);
        jsonObject.put("RequestStatus", this.requestStatus.toString());
        
        return jsonObject;
    }
    
    public static FriendRequest fromJson(JSONObject jsonObject){
       FriendRequest request = new FriendRequest();
       String senderId = jsonObject.getString("SenderUserId");
      //  System.out.println(senderId);
        String receiverId = jsonObject.getString("ReceiverUserId");
       // System.out.println(receiverId);
       request.requestSenderId = senderId;
       request.requestReceiverId = receiverId;
      //  User sender = UserDatabase.getInstance().getUser(senderId);
        
      //  User receiver = UserDatabase.getInstance().getUser(receiverId);
        
        // Set the request sender and receiver
      //  request.requestSender = sender;
       // request.requestReceiver = receiver;
        
      //  System.out.println(request.requestSender.userToString());
     //   System.out.println(request.requestReceiver.userToString());

        // Deserialize the request status
        String statusString = jsonObject.getString("RequestStatus");
        request.requestStatus = Status.valueOf(statusString);

        return request;
    }
    
    
}

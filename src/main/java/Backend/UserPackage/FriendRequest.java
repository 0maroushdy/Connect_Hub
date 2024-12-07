/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.UserPackage;

/**
 *
 * @author Abdelrahman
 */
public class FriendRequest {
    
    private User requestSender;
    private User requestReceiver;
    private Status requestStatus;
    public enum Status{
        Accepted, Declined, Pending
    }
    
     public FriendRequest(User requestsender, User requestReceiver, Status requestStatus) {
        this.requestSender = requestsender;
        this.requestReceiver = requestReceiver;
        this.requestStatus = requestStatus;
    }
     
           /* Getters */
    public User getRequestSender(){
        return this.requestSender;
    }
    
    public User getRequestReciever(){
        return this.requestReceiver;
    }
    
    public Status getRequestStatus(){
        return this.requestStatus;
    }
    
               /* Setters */
    public void setRequestSender(User requestSender){
        this.requestSender = requestSender;
    }
    
    public void setRequestReciever(User requestReciever){
        this.requestReceiver = requestReciever;
    }
    
    public void setRequestStatus(Status requestStatus){
        this.requestStatus = requestStatus;
    }
    
    
}

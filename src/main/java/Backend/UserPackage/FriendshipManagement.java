/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.UserPackage;

import static Files.FILEPATHS.USERFILE;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Abdelrahman
 */
public class FriendshipManagement {
    
    private ArrayList <User> users;
    
    public FriendshipManagement(){
        UserDatabase.getInstance().loadUsersFromFile(USERFILE);
        this.users = UserDatabase.getInstance().getUsers();
    }
    
    public boolean sendFriendRequest(User requestSender,User requestReceiver){
        if(!requestSender.isUserBlocked(requestReceiver)){
            FriendRequest request = new FriendRequest(requestSender.getUserId(),requestReceiver.getUserId(), FriendRequest.Status.Pending);
            for(User user:this.users){
                if(user.getUserId().equals(requestSender.getUserId())) {
                    if(user.getUserFriends().contains(requestReceiver.getUserId())){
                        return false;
                    }
                    user.getUserSentFriendRequests().add(request);
                }
                if(user.getUserId().equals(requestReceiver.getUserId())){
                    user.getUserReceivedFriendRequests().add(request);
                }
            }

           // System.out.println(requestSender.getUserSentFriendRequests().size());
           
           UserDatabase.getInstance().getUser(requestSender.getUserId()).getNotificationManager().addFriendRequestNotification("You have sent a Friend request to: "+requestReceiver.getUsername());
           requestReceiver.getNotificationManager().addFriendRequestNotification("You have Recieved a Friend request From: "+requestSender.getUsername());
           UserDatabase.getInstance().saveUsersToFile(USERFILE);
           

            return true;
        }
        else{
            return false;
        }  
    }
    
    public boolean acceptFriendRequest(User user,FriendRequest request) {
        
        if (request.getRequestReceiverId().equals(user.getUserId())) {
           for(User userr:this.users){
            if(userr.getUserId().equals(user.getUserId())){
            for(FriendRequest requestt:userr.getUserReceivedFriendRequests()){
                if(requestt.getRequestReceiverId().equals(request.getRequestReceiverId()) && requestt.getRequestSenderId().equals(request.getRequestSenderId())){
                    requestt.setRequestStatus(FriendRequest.Status.Accepted);
                    userr.getUserFriends().add(requestt.getRequestSenderId());
                    for(User sender:this.users){
                        if(sender.getUserId().equals(requestt.getRequestSenderId())){
                            sender.getUserFriends().add(userr.getUserId());
                        }
                    }
                }
            }
         }
        if(userr.getUserId().equals(request.getRequestSenderId())){
              for(FriendRequest requesttt:userr.getUserSentFriendRequests()){
                  if(requesttt.getRequestReceiverId().equals(request.getRequestReceiverId()) && requesttt.getRequestSenderId().equals(request.getRequestSenderId())){
                      requesttt.setRequestStatus(FriendRequest.Status.Accepted);
                  }
              }
          }
         
        }
          UserDatabase.getInstance().saveUsersToFile(USERFILE);
          
            return true;
        }
        return false;
    }
    
    public boolean declineFriendRequest(User user,FriendRequest request) {
         if (request.getRequestReceiverId().equals(user.getUserId())) {
           for(User userr:this.users){
            if(userr.getUserId().equals(user.getUserId())){
            for(FriendRequest requestt:userr.getUserReceivedFriendRequests()){
                if(requestt.getRequestReceiverId().equals(request.getRequestReceiverId()) && requestt.getRequestSenderId().equals(request.getRequestSenderId())){
                    requestt.setRequestStatus(FriendRequest.Status.Declined);
                }
            }
         }
        if(userr.getUserId().equals(request.getRequestSenderId())){
              for(FriendRequest requesttt:userr.getUserSentFriendRequests()){
                  if(requesttt.getRequestReceiverId().equals(request.getRequestReceiverId()) && requesttt.getRequestSenderId().equals(request.getRequestSenderId())){
                      requesttt.setRequestStatus(FriendRequest.Status.Declined);
                  }
              }
          }
         
        }
            UserDatabase.getInstance().saveUsersToFile(USERFILE);
            return true;
        }
        return false;
    }
    
    public void blockUser(User blocker,User blocked) {
        for(User user:this.users){
        if(user.getUserId().equals(blocker.getUserId()) && user.getUserFriends().contains(blocked.getUserId())){
        user.getUserFriends().remove(blocked.getUserId());
        user.getUserBlockedUsers().add(blocked.getUserId());
        UserDatabase.getInstance().saveUsersToFile(USERFILE);
         }
        if(user.getUserId().equals(blocked.getUserId()) && user.getUserFriends().contains(blocker.getUserId())){
        user.getUserFriends().remove(blocker.getUserId());
        UserDatabase.getInstance().saveUsersToFile(USERFILE);
         }
        }
    }

    public void removeFriend(User user,User friend) {
        for(User userr:this.users){
        if(userr.getUserId().equals(user.getUserId()) && userr.getUserFriends().contains(friend.getUserId())){
        userr.getUserFriends().remove(friend.getUserId());
        UserDatabase.getInstance().saveUsersToFile(USERFILE);
          }
        }
    }
    
    public Set <User> suggestFriends(User user){
        Set <User> suggestions = new HashSet<>();
        for(User differentUser:UserDatabase.getInstance().getUsers()){
            if(!user.getUserFriends().contains(differentUser.getUserId()) && !user.getUserBlockedUsers().contains(differentUser.getUserId()) && !user.getUserId().equals(differentUser.getUserId())) suggestions.add(differentUser);
        }
        return suggestions;
    } 
    
    public static class FriendshipManagementFactory{
        
        public static FriendshipManagement create(){
            return new FriendshipManagement();
        }
        
    }
    
    
}

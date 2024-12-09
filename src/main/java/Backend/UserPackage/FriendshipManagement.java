/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.UserPackage;

import static Files.FILEPATHS.USERFILE;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Abdelrahman
 */
public class FriendshipManagement {
    
    public FriendshipManagement(){
        UserDatabase.getInstance().loadUsersFromFile(USERFILE);
    }
    
    public boolean sendFriendRequest(User requestSender,User requestReceiver){
        if(!requestSender.isUserBlocked(requestReceiver)){
            FriendRequest request = new FriendRequest(requestSender.getUserId(),requestReceiver.getUserId(), FriendRequest.Status.Pending);
            requestSender.getUserSentFriendRequests().add(request);
            requestReceiver.getUserReceivedFriendRequests().add(request);  
            UserDatabase.getInstance().saveUsersToFile(USERFILE);
           // System.out.println(requestSender.getUserSentFriendRequests().size());
            return true;
        }
        else{
            return false;
        }  
    }
    
    public boolean acceptFriendRequest(User user,FriendRequest request) {
        if (request.getRequestReceiverId().equals(user.getUserId())) {
            if (user.getUserReceivedFriendRequests().contains(request)) {
            request.setRequestStatus(FriendRequest.Status.Accepted);
            user.getUserFriends().add(UserDatabase.getInstance().getUser(request.getRequestSenderId()));
            UserDatabase.getInstance().getUser(request.getRequestSenderId()).getUserFriends().add(user);
            UserDatabase.getInstance().saveUsersToFile(USERFILE);
            return true;
        }
        }
        return false;
    }
    
    public boolean declineFriendRequest(User user,FriendRequest request) {
        if (request.getRequestSenderId().equals(user.getUserId())) {
            if (user.getUserReceivedFriendRequests().contains(request)) {
            request.setRequestStatus(FriendRequest.Status.Declined);
            UserDatabase.getInstance().saveUsersToFile(USERFILE);
            return true;
        }
        }
        return false;
    }
    
    public void blockUser(User blocker,User blocked) {
        blocker.getUserFriends().remove(blocked);
        blocker.getUserBlockedUsers().add(blocked);
        UserDatabase.getInstance().saveUsersToFile(USERFILE);
    }

    public void removeFriend(User user,User friend) {
        user.getUserFriends().remove(friend);
        UserDatabase.getInstance().saveUsersToFile(USERFILE);
    }
    
    public Set <User> suggestFriends(User user){
        Set <User> suggestions = new HashSet<>();
        for(User differentUser:UserDatabase.getInstance().getUsers()){
            if(!user.getUserFriends().contains(differentUser) && !user.getUserBlockedUsers().contains(differentUser) && !user.getUserId().equals(differentUser.getUserId())) suggestions.add(differentUser);
        }
        return suggestions;
    } 
    
    public static class FriendshipManagementFactory{
        
        public static FriendshipManagement create(){
            return new FriendshipManagement();
        }
        
    }
    
    
}

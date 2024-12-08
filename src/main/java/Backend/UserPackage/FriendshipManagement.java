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
           // System.out.println(requestSender.getUserSentFriendRequests().size());
            requestSender.sendFriendRequest(requestReceiver);
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
            user.acceptFriendRequest(request);
            return true;
        }
        return false;
    }
    
    public boolean declineFriendRequest(User user,FriendRequest request) {
        if (request.getRequestSenderId().equals(user.getUserId())) {
            user.declineFriendRequest(request);
            return true;
        }
        return false;
    }
    
    public void blockUser(User blocker,User blocked) {
        blocker.blockUser(blocked);
    }

    public void removeFriend(User user,User friend) {
        user.removeFriend(friend);
    }
    
  /*  public Set <User> suggestFriends(User user){
        Set <User> suggestions = new HashSet<>();
        for(User differentUser:UserDatabase.getInstance().getUsers()){
            if(!user.getUserFriends().contains(differentUser) && !user.getUserBlockedUsers().contains(differentUser) && !user.getUserId().equals(differentUser.getUserId())) suggestions.add(differentUser);
        }
        return suggestions;
    } */
    
    public static class FriendshipManagementFactory{
        
        public static FriendshipManagement create(){
            return new FriendshipManagement();
        }
        
    }
    
    
}

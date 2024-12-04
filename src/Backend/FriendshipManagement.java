/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Abdelrahman
 */
public class FriendshipManagement {
    
    public boolean sendFriendRequest(User requestSender, User requestReceiver){
        if(!requestSender.isUserBlocked(requestReceiver)){
            requestSender.sendFriendRequest(requestReceiver);
            return true;
        }
        else{
            return false;
        }  
    }
    
    public boolean acceptFriendRequest(User user,FriendRequest request) {
        if (request.getRequestReciever().equals(user)) {
            user.acceptFriendRequest(request);
            return true;
        }
        return false;
    }
    
    public boolean declineFriendRequest(User user,FriendRequest request) {
        if (request.getRequestReciever().equals(user)) {
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
    
    public Set <User> suggestFriends(User user){
        Set <User> suggestions = new HashSet<>();
        for(User differentUser:UserDatabase.getInstance().getUsers()){
            if(!user.getUserFriends().contains(differentUser) && !user.getUserBlockedUsers().contains(differentUser)) suggestions.add(differentUser);
        }
        return suggestions;
    }
    
    
    
}

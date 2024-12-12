/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.UserPackage;

import Backend.UserProfilePackage.UserProfile;
import static Files.FILEPATHS.USERFILE;
import java.util.ArrayList;

/**
 *
 * @author Abdelrahman
 */
public class UserSearch {
    
    private ArrayList <User> searchUsers;
    private FriendshipManagement friendship;
    
    private UserSearch(String username){
        this.searchUsers = new ArrayList<>();
        this.searchUsers = UserDatabase.getInstance().getUsersByUsername(username);
        this.friendship = FriendshipManagement.FriendshipManagementFactory.create();
    }
    
    public ArrayList <User> getSearchUsers(){
        for(User user:this.searchUsers){
            if(user.getUserId().equals(UserSignupSingleton.getInstance().getUser().getUserId())) this.searchUsers.remove(user);
        }
        return this.searchUsers;
    }
    
     public boolean addFriend(User requestSender,User requestReceiver){
        return this.friendship.sendFriendRequest(requestSender, requestReceiver);
    }
     
     public void removeFriend(User user,User friend) {
       this.friendship.removeFriend(user, friend);
    }
      
     public void blockUser(User blocker,User blocked) {
        this.friendship.blockUser(blocker, blocked);
    }
     
    public UserProfile getUserProfile(User user){
        for(User userr:this.searchUsers){
            if(userr.getUserId().equals(user.getUserId())) userr.getUserProfile();
        }
        return null;
    }
    
    
    
    
    
    public static class UserSearchFactory{
        
        public static UserSearch createUserSearch(String username){
            return new UserSearch(username);
        }
    }
}

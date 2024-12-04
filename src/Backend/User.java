/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.Set;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.*;

/**
 *
 * @author Abdelrahman
 */
public class User {
   
   private String userId;
   private String email;
   private String username;
   private String password;
   private String dateOfBirth;
   private String status;
   private Set <User> friends;
   private Set <User> blockedUsers; 
   private Set <FriendRequest> sentFriendRequests; 
   private Set <FriendRequest> receivedFriendRequests;
   
      /* Constructor */
  private User(String userId, String email,String username,String password,LocalDate dateOfBirth){
       this.userId = userId;
       this.email = email;
       this.username = username;
       this.password = password;
       this.dateOfBirth = dateOfBirth.toString();
       this.friends = new HashSet<>();
       this.blockedUsers = new HashSet<>();
       this.sentFriendRequests = new HashSet<>();
       this.receivedFriendRequests = new HashSet<>();
   }

   public User() {
        
    }
         /* Getters */
    public String getUserId(){
       return this.userId;
   }
   
   public String getUserEmail(){
       return this.email;
   }
   
   public String getUsername(){
       return this.username;
   }
   
   public String getUserDateOfBirth(){
       return this.dateOfBirth;
   }
   
   public String getUserStatus(){
       return this.status;
   }
   
   public String getUserPassword(){
       return this.password;
   }
   
   public Set <User> getUserFriends(){
       return this.friends;
   }
   
   public Set <User> getUserBlockedUsers(){
       return this.blockedUsers;
   }
   
   public Set <FriendRequest> getUserSentFriendRequests(){
       return this.sentFriendRequests;
   }
   
   public Set <FriendRequest> getUserReceivedFriendRequests(){
       return this.receivedFriendRequests;
   }
   
   public void setUserPassword (String unHashedPassword) throws NoSuchAlgorithmException{
       this.password = HashingUtil.generateUserHashedPassword(unHashedPassword);
   }
   
             /* Setters */
   public void setUserDateOfBirth(LocalDate date){
       this.dateOfBirth = date.toString();
   }
  
   public void setUsername(String username){
       this.username = username;
   }
   
   public void setUserStatus(String status){
       this.status = status;
   }
   
   public boolean setUserEmail(String email){
       if(ValidationUtil.validateUserEmail(email)) this.email = email;
       else return false;
       
       return true;
   }
   
  public void userLogout(){
      this.status = "offline";
  }
  
  public JSONObject toJSON(){
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("UserId",this.userId);
      jsonObject.put("Username",this.username);
      jsonObject.put("Email",this.email);
      jsonObject.put("Password",this.password);
      jsonObject.put("DateOfBirth",this.dateOfBirth);
      jsonObject.put("Status",this.status);
      return jsonObject;
  }
  
  public void sendFriendRequest(User receiver) {
        FriendRequest request = new FriendRequest(this, receiver, FriendRequest.Status.Pending);
        this.sentFriendRequests.add(request);
        receiver.receivedFriendRequests.add(request);
  }
  
  public boolean acceptFriendRequest(FriendRequest request) {
        if (this.receivedFriendRequests.contains(request)) {
            request.setRequestStatus(FriendRequest.Status.Accepted);
            this.friends.add(request.getRequestSender());
            request.getRequestSender().friends.add(this);
            return true;
        }
        return false;
    }
  
  public boolean declineFriendRequest(FriendRequest request) {
        if (this.receivedFriendRequests.contains(request)) {
            request.setRequestStatus(FriendRequest.Status.Declined);
            return true;
        }
        return false;
    }
  
   public void blockUser(User user) {
        this.friends.remove(user);
        this.blockedUsers.add(user);
    }
   
    public void removeFriend(User user) {
        this.friends.remove(user);
    }
    
    public boolean isUserBlocked(User user) {
        return this.blockedUsers.contains(user);
    }
  
  public static class UserFactory{
      
     public static User create(String email, String username, String password, LocalDate dateOfBirth) throws NoSuchAlgorithmException {
            String hashedPassword = HashingUtil.generateUserHashedPassword(password);
            String userId = username + "-" + UserDatabase.getInstance().getUniqueCounter();
            return new User(userId, email, username, hashedPassword, dateOfBirth);
        }
  }
  
}

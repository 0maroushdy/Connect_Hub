/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.UserPackage;

import Backend.UserProfilePackage.UserProfile;
import static Files.FILEPATHS.USERFILE;
import java.util.Set;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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
   private UserProfile profile; // adding userProfile attribute --> making composition
   private Set <FriendRequest> sentFriendRequests; 
   private Set <FriendRequest> receivedFriendRequests;
   
      /* Constructor */
  private User(String userId, String email,String username,String password,LocalDate dateOfBirth,String status){
       this.userId = userId;
       this.email = email;
       this.username = username;
       this.password = password;
       this.dateOfBirth = dateOfBirth.toString();
       this.status = status;
       this.friends = new HashSet<>();
       this.blockedUsers = new HashSet<>();
       this.sentFriendRequests = new HashSet<>();
       this.receivedFriendRequests = new HashSet<>();
       this.profile = new UserProfile();
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
   
   public UserProfile getUserProfile(){
       return this.profile;
   }
   
             /* Setters */
   public void setUserPassword (String unHashedPassword) throws NoSuchAlgorithmException{
       this.password = HashingUtil.generateUserHashedPassword(unHashedPassword);
   }
   
   public void setUserPassword (String password, boolean wantToHash) throws NoSuchAlgorithmException{
       if(wantToHash == true)
            this.password = HashingUtil.generateUserHashedPassword(password);
       else
           this.password = password;
   }
   
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
     UserSignupSingleton.getInstance().getUser().setUserStatus("offline");
     UserDatabase.getInstance().saveUsersToFile(USERFILE);
  }
  
  public JSONObject toJSON(){
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("UserId",this.userId);
      jsonObject.put("Username",this.username);
      jsonObject.put("Email",this.email);
      jsonObject.put("Password",this.password);
      jsonObject.put("Status",this.status);
      jsonObject.put("DateOfBirth",this.dateOfBirth);
      jsonObject.put("Profile",this.profile.toJSON());
      return jsonObject;
  }
  
  public void sendFriendRequest(User receiver) {
        FriendRequest request = new FriendRequest(this, receiver, FriendRequest.Status.Pending);
        this.sentFriendRequests.add(request);
        receiver.receivedFriendRequests.add(request);
        UserDatabase.getInstance().saveUsersToFile(USERFILE);
  }
  
  public boolean acceptFriendRequest(FriendRequest request) {
        if (this.receivedFriendRequests.contains(request)) {
            request.setRequestStatus(FriendRequest.Status.Accepted);
            this.friends.add(request.getRequestSender());
            request.getRequestSender().friends.add(this);
            UserDatabase.getInstance().saveUsersToFile(USERFILE);
            return true;
        }
        return false;
    }
  
  public boolean declineFriendRequest(FriendRequest request) {
        if (this.receivedFriendRequests.contains(request)) {
            request.setRequestStatus(FriendRequest.Status.Declined);
            UserDatabase.getInstance().saveUsersToFile(USERFILE);
            return true;
        }
        return false;
    }
  
   public void blockUser(User user) {
        this.friends.remove(user);
        this.blockedUsers.add(user);
        UserDatabase.getInstance().saveUsersToFile(USERFILE);
    }
   
    public void removeFriend(User user) {
        this.friends.remove(user);
        UserDatabase.getInstance().saveUsersToFile(USERFILE);
    }
    
    public boolean isUserBlocked(User user) {
        return this.blockedUsers.contains(user);
    }
    
     public ArrayList <User> suggestFriends(){
        ArrayList <User> suggestions = new ArrayList<>();
        for(User user:UserDatabase.getInstance().getUsers()){
             System.out.println(user.userToString());
            if(!this.getUserFriends().contains(user) && !this.getUserBlockedUsers().contains(user) && !this.getUserId().equals(user.getUserId())) suggestions.add(user);
          //  System.out.println(differentUser.getUserId());
        }
        return suggestions;
    }
     
   public String userToString(){
       String ans ="";
       ans += this.userId;
       ans += " ";
       ans += this.username;
       ans += " ";
       ans += this.email;
       ans += " ";
       ans += this.password;
       ans += " ";
       ans += this.dateOfBirth;
       ans += " ";
       ans += this.status;
       return ans;
   }
  
  public static class UserFactory{
     /* public static User create(String email, String username, String password, LocalDate dateOfBirth,String status) throws NoSuchAlgorithmException {
            String hashedPassword = HashingUtil.generateUserHashedPassword(password);
            String userId = username + "-" + UserDatabase.getInstance().getUniqueCounter();
            User user = new User(userId, email, username, hashedPassword, dateOfBirth,status);
            return user;
        }*/
      
   /*  public static User create(String email, String username, String password, LocalDate dateOfBirth,String status, boolean wanttohash) throws NoSuchAlgorithmException {
            String hashedPassword = HashingUtil.generateUserHashedPassword(password);
            String userId = username + "-" + UserDatabase.getInstance().getUniqueCounter();
            User user = new User(userId, email, username, hashedPassword, dateOfBirth,status);
            user.setUserPassword(password,wanttohash);
            return user;
        } */
     public static User create(String userId,String email, String username, String password, LocalDate dateOfBirth,String status, boolean wanttohash) throws NoSuchAlgorithmException {
            String hashedPassword = HashingUtil.generateUserHashedPassword(password);
            User user = new User(userId, email, username, hashedPassword, dateOfBirth,status);
            user.setUserPassword(password,wanttohash);
            return user;
        }
     
  }
  
}

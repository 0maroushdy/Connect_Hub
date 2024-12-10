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
import java.time.format.DateTimeFormatter;
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
   private Set <String> friends;
   private Set <String> blockedUsers; 
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
       this.friends = new HashSet<>();
       this.blockedUsers = new HashSet<>();
       this.sentFriendRequests = new HashSet<>();
       this.receivedFriendRequests = new HashSet<>();
       this.profile = new UserProfile();
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
   
   public Set <String> getUserFriends(){
       return this.friends;
   }
   
   public Set <String> getUserBlockedUsers(){
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
   
   public void setStatusAcceptedFriendRequest(FriendRequest request){
       for(FriendRequest requestt:this.sentFriendRequests){
           if(requestt.getRequestSenderId().equals(request.getRequestSenderId()) && requestt.getRequestReceiverId().equals(request.getRequestReceiverId())){
               requestt.setRequestStatus(FriendRequest.Status.Accepted);
           }
           
       }
   }
   
   public void setStatusDeclinedFriendRequest(FriendRequest request){
       for(FriendRequest requestt:this.sentFriendRequests){
           if(requestt.getRequestSenderId().equals(request.getRequestSenderId()) && requestt.getRequestReceiverId().equals(request.getRequestReceiverId())){
               requestt.setRequestStatus(FriendRequest.Status.Declined);
           }
           
       }
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
      JSONArray friendsArray = new JSONArray();
        for (String friend : this.friends) {
            friendsArray.put(friend); 
        }
        jsonObject.put("Friends", friendsArray);

        // Serialize blocked users
        JSONArray blockedUsersArray = new JSONArray();
        for (String blockedUser : this.blockedUsers) {
            blockedUsersArray.put(blockedUser); 
        }
        jsonObject.put("BlockedUsers", blockedUsersArray);

        // Serialize sent friend requests
        JSONArray sentRequestsArray = new JSONArray();
        for (FriendRequest request : this.sentFriendRequests) {
            sentRequestsArray.put(request.toJSON());
        }
        jsonObject.put("SentFriendRequests", sentRequestsArray);

        // Serialize received friend requests
        JSONArray receivedRequestsArray = new JSONArray();
        for (FriendRequest request : this.receivedFriendRequests) {
            receivedRequestsArray.put(request.toJSON()); 
        }
        jsonObject.put("ReceivedFriendRequests", receivedRequestsArray);
      return jsonObject;
  }
  
  public static User fromJson(JSONObject jsonObject){
    User user = new User();
    user.dateOfBirth = jsonObject.getString("DateOfBirth");
    user.status = jsonObject.getString("Status");
    user.email = jsonObject.getString("Email");
     user.username = jsonObject.getString("Username");
     user.userId = jsonObject.getString("UserId");
    user.password = jsonObject.getString("Password");
   // user.date = LocalDate.parse(dateOfBirth, DateTimeFormatter.ISO_LOCAL_DATE);
     JSONObject profileObj = (JSONObject) jsonObject.get("Profile") ;
    user.profile = new UserProfile( profileObj.getString("profilePhoto"),
                              profileObj.getString("profileCover") 
                             ,profileObj.getString("bio")) ;
    // Deserialize the friends set
   // user.friends = new HashSet<>();
    JSONArray friendsArray = jsonObject.getJSONArray("Friends");
    for (int i = 0; i < friendsArray.length(); i++) {
        String friendId = friendsArray.getString(i);
        user.friends.add(friendId);
    }

    // Deserialize the blocked users set
   // user.blockedUsers = new HashSet<>();
    JSONArray blockedUsersArray = jsonObject.getJSONArray("BlockedUsers");
    for (int i = 0; i < blockedUsersArray.length(); i++) {
        String blockedUserId = blockedUsersArray.getString(i);
        user.blockedUsers.add(blockedUserId);
    }

    // Deserialize sent friend requests
   // user.sentFriendRequests = new HashSet<>();
    JSONArray sentRequestsArray = jsonObject.getJSONArray("SentFriendRequests");
    for (int i = 0; i < sentRequestsArray.length(); i++) {
        JSONObject requestJson = sentRequestsArray.getJSONObject(i);
        FriendRequest request = FriendRequest.fromJson(requestJson);
          if (request != null) { // Ensure the deserialization didn't fail
        user.sentFriendRequests.add(request);
    } else {
        System.err.println("Warning: Failed to deserialize sent friend request at index " + i);
    }
    }

    // Deserialize received friend requests
   // user.receivedFriendRequests = new HashSet<>();
    JSONArray receivedRequestsArray = jsonObject.getJSONArray("ReceivedFriendRequests");
    for (int i = 0; i < receivedRequestsArray.length(); i++) {
        JSONObject requestJson = receivedRequestsArray.getJSONObject(i);
        FriendRequest request = FriendRequest.fromJson(requestJson);
        if (request != null) { // Ensure the deserialization didn't fail
        user.receivedFriendRequests.add(request);
    } else {
        System.err.println("Warning: Failed to deserialize received friend request at index " + i);
    }
    }
    return user;
  }
  
   /* public void sendFriendRequest(User receiver) {
        FriendRequest request = new FriendRequest(this.userId, receiver.userId, FriendRequest.Status.Pending);
        this.sentFriendRequests.add(request);
        receiver.receivedFriendRequests.add(request);
  } */
  
 /* public boolean acceptFriendRequest(FriendRequest request) {
        if (this.receivedFriendRequests.contains(request)) {
            request.setRequestStatus(FriendRequest.Status.Accepted);
            this.friends.add(UserDatabase.getInstance().getUser(request.getRequestSenderId()));
            UserDatabase.getInstance().getUser(request.getRequestSenderId()).friends.add(this);
            UserDatabase.getInstance().saveUsersToFile(USERFILE);
            return true;
        }
        return false;
    } */
  
 /* public boolean declineFriendRequest(FriendRequest request) {
        if (this.receivedFriendRequests.contains(request)) {
            request.setRequestStatus(FriendRequest.Status.Declined);
            UserDatabase.getInstance().saveUsersToFile(USERFILE);
            return true;
        }
        return false;
    } */
  
  /* public void blockUser(User user) {
        this.friends.remove(user);
        this.blockedUsers.add(user);
        UserDatabase.getInstance().saveUsersToFile(USERFILE);
    } */
   
   /* public void removeFriend(User user) {
        this.friends.remove(user);
        UserDatabase.getInstance().saveUsersToFile(USERFILE);
    } */
    
    public boolean isUserBlocked(User user) {
        return this.blockedUsers.contains(user);
    }
    
   /*  public ArrayList <User> suggestFriends(){
        ArrayList <User> suggestions = new ArrayList<>();
        for(User user:UserDatabase.getInstance().getUsers()){
             System.out.println(user.userToString());
            if(!this.getUserFriends().contains(user) && !this.getUserBlockedUsers().contains(user) && !this.getUserId().equals(user.getUserId())) suggestions.add(user);
          //  System.out.println(differentUser.getUserId());
        }
        return suggestions;
    } */
      
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

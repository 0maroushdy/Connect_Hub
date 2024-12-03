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
   
  private User(String email,String username,String password,LocalDate dateOfBirth) throws NoSuchAlgorithmException{
       this.userId = generateUserId(username);
       this.email = email;
       this.username = username;
       this.password = generateUserHashedPassword(password);
       this.dateOfBirth = dateOfBirth.toString();
   }

   public User() {
        
    }
   
   public void setUserPassword (String unHashedPassword) throws NoSuchAlgorithmException{
       this.password = generateUserHashedPassword(unHashedPassword);
   }
   
   
   public String generateUserId(String username){
       String id = username + "-" + UserDatabase.getInstance().getUniqueCounter();
       return id;
   }
   
   public boolean validateUserInput(String email,String username,String password,LocalDate dateOfBirth){
       return !email.isEmpty() && !username.isEmpty() && !password.isEmpty();
   }
   
   public String generateUserHashedPassword(String password) throws NoSuchAlgorithmException{
       MessageDigest mssg = MessageDigest.getInstance("SHA-256");
       byte [] hashedBytes = mssg.digest(password.getBytes());
       StringBuilder hexadecimalString = new StringBuilder();
       for(byte b:hashedBytes){
           String hexadecimal = Integer.toHexString(0xff & b);
           if(hexadecimal.length() == 1){
               hexadecimalString.append('0');
           }
           hexadecimalString.append(hexadecimal);
       }
       return hexadecimalString.toString();
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
       if(validateUserEmail(email)) this.email = email;
       else return false;
       
       return true;
   }
   
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
   
   public boolean validateUserEmail(String email){
     int countAtSigns = 0;
     int countDots = 0;
     for(int i=0;i<email.length();i++){
         if(email.charAt(i) == '@') countAtSigns++;
         else if(email.charAt(i) == '.') countDots++;
     }
     /*Validation to ensure there is only one dot and one @ sign*/
     if(countAtSigns != 1 || countDots == 0) return false; 
     
     int ind = 0;
     int countChars=0;
     while(email.charAt(ind) != '@'){
         countChars++;
         ind++;
     }
     ind++;
     /* Validation to ensure there is at least one character before @ sign */
     if(countChars == 0) return false;
     
     int countCharsBetween = 0;
     while(email.charAt(ind) != '.'){
         countCharsBetween++;
         ind++;
     }
     ind++;
     /*Validation to ensure there is at least one character after @ and before . */
     if(countCharsBetween == 0) return false;
     
     int countCharsEnd=0;
     
     while(ind < email.length()){
         countCharsEnd++;
         ind++;
     }
     /*validation to ensure there is at least one character after . */
     if(countCharsEnd == 0) return false;
     
     return true;
  }
   
   public String getUserPassword(){
       return this.password;
   }
   
  public void userLogout(){
      this.status = "offline";
  }
  
  static class UserBuilder{
      static User create(String email, String username, String password, LocalDate dateOfBirth) throws NoSuchAlgorithmException {
            return new User(email, username, password, dateOfBirth);
        }
  }
  
}

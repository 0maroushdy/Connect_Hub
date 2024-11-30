/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.util.Set;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
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
   
   public void generateUser_id(String username,Set <String> allIds){
       int uniqueCounter = 1;
       this.userId = username + uniqueCounter;
       while(allIds.contains(this.userId)){
           uniqueCounter++;
           this.userId = username + uniqueCounter;
       }
       
   }
   
   public void setUserHashedPassword(String password) throws NoSuchAlgorithmException{
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
       this.password = hexadecimalString.toString();
   }
   
   public void setUserDateOfBirth(LocalDate date){
       this.dateOfBirth = date.toString();
   }
   
   public void setUserStatus(){
       if(user_login(userId,password)) this.status = "online";
       else this.status = "offline";
   }
   
   public void setUsername(String username){
       this.username = username;
   }
   
   public boolean setUserEmail(String email){
       if(validateUser_email(email)) this.email = email;
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
   
   public boolean validateUser_email(String email){
     int countAtSigns = 0;
     int countDots = 0;
     for(int i=0;i<email.length();i++){
         if(email.charAt(i) == '@') countAtSigns++;
         else if(email.charAt(i) == '.') countDots++;
     }
     /*Validation to ensure there is only one dot and one @ sign*/
     if(countAtSigns != 1 || countDots != 1) return false; 
     
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
   
  public String user_signup(String email,String username,String password,LocalDate dateOfBirth,Set <String> allIds) throws NoSuchAlgorithmException{
    if(!setUserEmail(email)) return "invalidemail";
    generateUser_id(username,allIds);
    setUserHashedPassword(password);
    setUsername(username);
    setUserDateOfBirth(dateOfBirth);
    setUserStatus();
    return "";
  }
   
   public boolean user_login(String userId,String password){
      
       return true;
  }
  
  public void user_logout(){
      this.status = "offline";
  }
}

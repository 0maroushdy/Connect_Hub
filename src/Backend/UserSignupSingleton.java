/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

/**
 *
 * @author Abdelrahman
 */
public class UserSignupSingleton {
    
    private static UserSignupSingleton userInstance;
    private User user;
    
    private UserSignupSingleton() {}
    
    public User getUser(){
        return this.user;
    }
     
     public static UserSignupSingleton getInstance() {
        if (userInstance == null) {
            synchronized (UserSignupSingleton.class) {
                if (userInstance == null) {
                    userInstance = new UserSignupSingleton();
                }
            }
        }
        return userInstance;
    }
     
    public boolean validateUserInput(String email,String username,String password,LocalDate dateOfBirth){
       return !email.isEmpty() && !username.isEmpty() && !password.isEmpty();
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
    
   public boolean userSignup(String email,String username,String password,LocalDate dateOfBirth) throws NoSuchAlgorithmException{
   
   if(!validateUserInput(email,username,password,dateOfBirth)) return false;
   
   if(!validateUserEmail(email)) return false;
   
   user = User.UserBuilder.create(email, username, password, dateOfBirth);
   user.setUserStatus("online");
   UserDatabase.getInstance().addUser(user);
   UserDatabase.getInstance().saveUsersToFile("UserDatabase.txt");
   return true;
   
  }
}

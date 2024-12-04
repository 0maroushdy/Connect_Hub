/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
       Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
  }
    
   public boolean userSignup(String email,String username,String password,LocalDate dateOfBirth) throws NoSuchAlgorithmException{
   
   if(!validateUserInput(email,username,password,dateOfBirth)) return false;
   
   if(!validateUserEmail(email)) return false;
   
   user = User.UserFactory.create(email, username, password, dateOfBirth);
   user.setUserStatus("online");
   UserDatabase.getInstance().addUser(user);
   UserDatabase.getInstance().saveUsersToFile("UserDatabase.txt");
   return true;
   
  }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.UserPackage;

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
    
   public boolean userSignup(String email,String username,String password,LocalDate dateOfBirth) throws NoSuchAlgorithmException{
   
   if(!ValidationUtil.validateUserInput(email,username,password,dateOfBirth)) return false;
   
   if(!ValidationUtil.validateUserEmail(email)) return false;
   
   user = User.UserFactory.create(email, username, password, dateOfBirth);
   user.setUserStatus("online");
   UserDatabase.getInstance().addUser(user);
   UserDatabase.getInstance().saveUsersToFile("UserDatabase.txt");
   return true;
   
  }
}
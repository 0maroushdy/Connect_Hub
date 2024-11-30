/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Abdelrahman
 */
public class UserDatabase {
    
    private static UserDatabase user_database;
    private ArrayList <User> users;
    
    private UserDatabase(){
        this.users = new ArrayList<>();
    }
    
    public static synchronized UserDatabase getInstance(){
        if(user_database == null){
            user_database = new UserDatabase();
        }
        return user_database;
    }
    
    public Set <String> getUserIds(){
        Set <String> ids = new HashSet<>();
        for(User user:users){
            ids.add(user.getUserId());
        }
        return ids;
    }
    
    public ArrayList <User> getUsers(){
        return this.users;
    }
    
    public boolean addUser(String userId,String password,String email,String username, LocalDate dateOfBirth) throws NoSuchAlgorithmException{
        User user = new User();
        if(user.user_login(userId, password) || user.user_signup(email, username, password, dateOfBirth,this.getUserIds())){
            users.add(user);
            return true;
        }
        return false;
    }
    
    
    
}

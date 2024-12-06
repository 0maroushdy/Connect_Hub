/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

import Backend.UserPackage.User;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

/**
 *
 * @author moustafa
 */
public class testDataBase {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        
        
        User u = User.UserFactory.create("moustafa@gmail.com","moustafa","123",LocalDate.now(),"Online");
        (new Story(u)).uplode();
        
        System.out.println(ContentDataBase.getInstance().getStories());
        
        
        
        ContentDataBase.getInstance().shutDown();
    }
}

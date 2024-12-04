/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Abdelrahman
 */
public class ValidationUtil {
    
   public static boolean validateUserInput(String email,String username,String password,LocalDate dateOfBirth){
       return !email.isEmpty() && !username.isEmpty() && !password.isEmpty();
   }
   
   public static boolean validateUserEmail(String email){
     
       String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
       Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
  }
}

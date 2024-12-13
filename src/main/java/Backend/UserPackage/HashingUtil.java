/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.UserPackage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abdelrahman
 */
public class HashingUtil {
    
    public static String generateUserHashedPassword(String password){
        try {
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashingUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
   }
}

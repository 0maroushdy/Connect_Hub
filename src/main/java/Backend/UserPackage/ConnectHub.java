/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Backend.UserPackage;

import Backend.ContentPackage.ContentDataBase;
import Backend.GroupPackage.Group;
import Backend.GroupPackage.GroupDatabase;
import static Files.FILEPATHS.USERFILE;
import Frontend.UserPackage.WelcomeFrame;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 * @author Abdelrahman
 */
public class ConnectHub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, NoSuchAlgorithmException {
        // TODO code application logic here
       
        UserDatabase.getInstance().loadUsersFromFile(USERFILE);
        ContentDataBase.getInstance().load();
        WelcomeFrame frame = new WelcomeFrame();
        frame.setVisible(true);
        
    }
    
}

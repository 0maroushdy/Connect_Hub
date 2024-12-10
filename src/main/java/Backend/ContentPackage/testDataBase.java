/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

import Backend.UserPackage.User;
import Backend.UserPackage.UserDatabase;
import Backend.UserPackage.UserSignupSingleton;
import static Files.FILEPATHS.USERFILE;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

/**
 *
 * @author moustafa
 */
public class testDataBase {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        UserDatabase.getInstance().loadUsersFromFile(USERFILE);
        //this is the problem
        User u = User.UserFactory.create("m-1","ggg@ggg.ggg", "moustafa", "123", LocalDate.now(), "Online", true);
        UserDatabase.getInstance().addUser(u);
        UserSignupSingleton.getInstance().setUser(u);

        new Story.Builder(u, "story 1")
                .build()
                .uplode();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        new Story.Builder(u, "story 2")
                .build()
                .uplode();

        new Post.Builder(u, "post 1")
                .build()
                .uplode();
    }
}

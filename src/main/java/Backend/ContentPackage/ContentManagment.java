/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Backend.ContentPackage;

import Backend.UserPackage.UserDatabase;
import static Files.FILEPATHS.USERFILE;
import java.util.ArrayList;

/**
 *
 * @author moustafa
 */
public class ContentManagment {

    

    public static void main(String[] args) {
        var k = UserDatabase.getInstance();
        k.loadUsersFromFile(USERFILE);
        
        
        for(var authorId : k.getUsers()){
            (new Post(authorId)).uplode();
        }

        for(var authorId : k.getUsers()){
            (new Story(authorId)).uplode();
        }

        ContentDataBase.getInstance().save();
    }

}

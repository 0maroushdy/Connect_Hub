/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

import java.security.NoSuchAlgorithmException;

/**
 *
 * @author moustafa
 */
public class testDataBase {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        new Story.Builder("m-1", "story 1")
                .build()
                .uplode();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Story.Builder("m-1", "story 2")
                .build()
                .uplode();

        new Post.Builder("m-1", "post 1")
                .build()
                .uplode();
        
        System.exit(0);
    }
    
}

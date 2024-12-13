/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

import java.security.NoSuchAlgorithmException;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 * @author moustafa
 */
public class testDataBase {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        System.out.println("Posts: " + ContentDataBase.getInstance().getPosts()
                .stream()
                .map(Post::getTimestamp)
                .collect(Collectors.toCollection(TreeSet::new)));
        System.out.println("Stories: " + ContentDataBase.getInstance().getStories()
                .stream()
                .map(Story::getTimestamp)
                .collect(Collectors.toCollection(TreeSet::new)));

        new Story.Builder("m-1", "story 1")
                .build()
                .uplode();

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Story s = new Story.Builder("m-1", "story 2")
                .build();

        s.uplode();

        new Post.Builder("m-1", "post 1")
                .build()
                .uplode();

        System.out.println("Posts: " + ContentDataBase.getInstance().getPosts()
                .stream()
                .map(Post::getTimestamp)
                .collect(Collectors.toCollection(TreeSet::new)));
        System.out.println("Stories: " + ContentDataBase.getInstance().getStories()
                .stream()
                .map(Story::getTimestamp)
                .collect(Collectors.toCollection(TreeSet::new)));

        System.exit(0);
    }

}

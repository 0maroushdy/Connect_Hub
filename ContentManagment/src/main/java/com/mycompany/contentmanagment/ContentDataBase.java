/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contentmanagment;

//import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*TODO
1- sync this class
2- id int or big int
*/
/**
 *
 * @author moustafa
 */
public class ContentDataBase implements AutoCloseable {

    private final ArrayList<Post> posts = new ArrayList<>();
    private final ArrayList<Story> Stories = new ArrayList<>();
    private static final ContentDataBase dataBase = new ContentDataBase();
    private final ScheduledExecutorService scheduler;
//    private BigInteger id = BigInteger.ZERO;
    private int id = 0;
    
    private ContentDataBase() {
        this.scheduler = Executors.newScheduledThreadPool(1);

        this.scheduler.schedule(
                () -> this.removeStory(),
                1, TimeUnit.HOURS
        );
    }

    public static ContentDataBase getInstance() {
        return ContentDataBase.dataBase;
    }

    public void addContent(Post cont) {
        this.posts.add(cont);
    }

    public void addContent(Story cont) {
        this.Stories.add(cont);
    }

    private void removeStory() {
        for (Story s : this.Stories) {
            Duration duration = Duration.between(s.getTimeOfUpload(), LocalDateTime.now());
            if (duration.toHours() >= 24) {
                this.Stories.remove(s);
            }
        }
    }

    protected ArrayList<Post> getPosts() {
        return posts;
    }

    protected ArrayList<Story> getStories() {
        return Stories;
    }

//    public BigInteger getUniqueId() {
//        id = id.add(BigInteger.ONE);
//        return id;
//    }

    public synchronized int getUniqueId() {
        this.id += 1;
        return id;
    }
    
    

    @Override
    public void close() {
        this.scheduler.shutdown();
        System.out.println("Scheduler shutdown.");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

import Backend.UserPackage.User;
import static Files.FILEPATHS.*;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author moustafa
 */
public class ContentDataBase {

    private final TreeSet<Post> posts;
    private final TreeSet<Story> stories;

    private static ContentDataBase dataBase;
    private final ScheduledExecutorService scheduler;

    private ContentDataBase() {
        this.posts = new TreeSet<>();
        this.stories = new TreeSet<>();

        this.scheduler = Executors.newScheduledThreadPool(1);
        this.scheduler.scheduleAtFixedRate(
                this::removeStory,
                0, 1, TimeUnit.SECONDS
        );

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            this.shutDown();
        }));
    }

    public static ContentDataBase getInstance() {
        if (ContentDataBase.dataBase == null) {
            synchronized (ContentDataBase.class) {
                ContentDataBase.dataBase = new ContentDataBase();
                ContentDataBase.dataBase.load();
            }
        }
        return ContentDataBase.dataBase;
    }

    public TreeSet <Post> getPosts() {
        return new TreeSet<>(posts);
    }

    public TreeSet<Story> getStories() {
        return new TreeSet<>(stories);
    }
    
    public Post getPostById(String postId){
        for(Post post:this.posts){
            if(post.getContentId().equals(postId)) return post;
        }
        return null;
    }
    
    public void RemovePost(Post post){
        if(post != null){
            this.posts.remove(post);
            this.update();
        }
    }

    public synchronized void addContent(Post cont) {
        this.posts.add(cont);
        this.update();
    }

    public synchronized void addContent(Story cont) {
        this.stories.add(cont);
        this.update();
    }

    private synchronized void load() {
        try {
            JSONArray storiesJSON = JSONUtils.readFromFile(STORYFILE);
            for (int i = 0; i < storiesJSON.length(); i++) {
                try {
                    JSONObject storyJSON = storiesJSON.getJSONObject(i);
                    Story s = Story.fromJSON(storyJSON);
                    this.stories.add(s);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

            JSONArray postsJSON = JSONUtils.readFromFile(POSTFILE);
            for (int i = 0; i < postsJSON.length(); i++) {
                JSONObject postJSON = postsJSON.getJSONObject(i);
                this.posts.add(Post.fromJSON(postJSON));
            }

        } catch (IOException e) {
            System.err.println("Error loading files: " + e.getMessage());
        } catch (JSONException e) {
            System.err.println("Invalid JSON format: " + e.getMessage());
        }
    }

    private synchronized void save() {

        JSONArray storiesJSON = new JSONArray();
        for (Story story : this.stories) {
            storiesJSON.put(story.toJSON());
        }

        JSONArray postsJSON = new JSONArray();
        for (Post post : this.posts) {
            postsJSON.put(post.toJSON());
        }

        try {
            JSONUtils.writeToFile(STORYFILE, storiesJSON);
            JSONUtils.writeToFile(POSTFILE, postsJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void update() {
        this.load();
        this.save();
    }

    private synchronized void removeStory() {
        //debug
//            System.out.println("Scheduled remove stories");
        //

        ArrayList<Story> storiesToRemove = new ArrayList<>() {
            @Override
            public boolean contains(Object o) {
                if (this == o) {
                    return true;
                }
                if (o == null) {
                    return false;
                }
                if (o instanceof Story storyToCheck) {
                    for (Story story : this) {
                        if (story.getContentId().equals(storyToCheck.getContentId())) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };

        long targetSeconds = 23 * 3600 + 59 * 60 + 50;
        //debug
//            targetSeconds = 5;
        //
        for (Story s : this.stories) {
            Duration duration = Duration.between(s.getTimeOfUpload(), LocalDateTime.now());

            if (duration.getSeconds() >= targetSeconds) {
                storiesToRemove.add(s);
            }
        }

        this.stories.removeAll(storiesToRemove);
//could be optimised because stories is sorted in cronological ordering
        try {
            JSONArray storiesJSON = JSONUtils.readFromFile(STORYFILE);
            for (int i = 0; i < storiesJSON.length(); i++) {
                JSONObject storyJSON = storiesJSON.getJSONObject(i);
                Story s = Story.fromJSON(storyJSON);
                if (!storiesToRemove.contains(s)) {
                    this.stories.add(s);
                }
            }

            storiesJSON = new JSONArray();
            for (Story story : this.stories) {
                storiesJSON.put(story.toJSON());
            }

            JSONUtils.writeToFile(STORYFILE, storiesJSON);
        } catch (IOException ex) {
            Logger.getLogger(ContentDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void shutDown() {
        System.out.println("Shutting down ContentDataBase...");
        System.out.println("\tSaving content database");

        this.update();

        if (this.scheduler != null && !scheduler.isShutdown()) {
            System.out.println("\tShutting down scheduler...");
            this.scheduler.shutdown();
            try {
                if (!this.scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    this.scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
            }
        }

    }

    public static class Query {

        public static synchronized ArrayList<Post> getFriendsPosts(User user) {
            ArrayList<Post> friendsPosts = new ArrayList<>();
            for (Post post : ContentDataBase.getInstance().getPosts()) {
                if (user.getUserFriends().contains(post.getAuthorId())) {
                    friendsPosts.add(post);
                }
            }
            return friendsPosts;
        }

        public static synchronized ArrayList<Story> getFriendsStories(User user) {
            ArrayList<Story> friendsStories = new ArrayList<>();
            for (Story story : ContentDataBase.getInstance().getStories()) {
                if (user.getUserFriends().contains(story.getAuthorId())) {
                    friendsStories.add(story);
                }
            }
            return friendsStories;
        }
    }

}

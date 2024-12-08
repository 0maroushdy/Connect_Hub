/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

import Backend.UserPackage.User;
import static Files.FILEPATHS.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/*TODO
3- make id generator a diff class or use uuid
 */
/**
 *
 * @author moustafa
 */
public class ContentDataBase {

    private final ArrayList<Post> posts;
    private final ArrayList<Story> stories;
    private static ContentDataBase dataBase;
    private final ScheduledExecutorService scheduler;

    private static int id = 0;

    private ContentDataBase() {
        this.posts = new ArrayList<>();
        this.stories = new ArrayList<>();

        this.scheduler = Executors.newScheduledThreadPool(1);
        this.scheduler.scheduleAtFixedRate(
                this::removeStory,
                1, 1, TimeUnit.HOURS
        );

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            this.shutDown();
        }));
    }

    public synchronized static ContentDataBase getInstance() {
        if (ContentDataBase.dataBase == null) {
            ContentDataBase.dataBase = new ContentDataBase();
            ContentDataBase.dataBase.load();
        }
        return ContentDataBase.dataBase;
    }

    public synchronized void addContent(Post cont) {
        this.posts.add(cont);
        this.save();
    }

    public synchronized void addContent(Story cont) {
        this.stories.add(cont);
        this.save();
    }

    private synchronized void removeStory() {
        System.out.println("Scheduled remove stories");

        ArrayList<Story> storiesToRemove = new ArrayList<>();
        for (Story s : this.stories) {
            Duration duration = Duration.between(s.getTimeOfUpload(), LocalDateTime.now());
            if (duration.toHours() >= 24) {
                storiesToRemove.add(s);
            }
        }

        this.stories.removeAll(storiesToRemove);
        this.save();
    }

    public synchronized ArrayList<Post> getPosts() {
        return new ArrayList<>(posts);
    }

    public synchronized ArrayList<Story> getStories() {
        return new ArrayList<>(stories);
    }

    public synchronized ArrayList<Post> getFriendsPosts(User user) {
        ArrayList<Post> friendsPosts = new ArrayList<>();
        for (Post post : this.posts) {
            if (user.getUserFriends().contains(post.getAuthor())) {
                friendsPosts.add(post);
            }
        }
        return friendsPosts;
    }

    public synchronized ArrayList<Story> getFriendsStories(User user) {
        ArrayList<Story> friendsStories = new ArrayList<>();
        for (Story story : this.stories) {
            if (user.getUserFriends().contains(story.getAuthor())) {
                friendsStories.add(story);
            }
        }
        return friendsStories;
    }

    public synchronized static int getUniqueId() {
        ContentDataBase.id += 1;
        return ContentDataBase.id;
    }

    protected synchronized final void load() {

        try (BufferedReader storiesFile = new BufferedReader(new FileReader(STORYFILE)); BufferedReader postsFile = new BufferedReader(new FileReader(POSTFILE))) {

            JSONArray storiesJSON = new JSONArray(new JSONTokener(storiesFile));
            for (int i = 0; i < storiesJSON.length(); i++) {
                JSONObject storyJSON = storiesJSON.getJSONObject(i);
                Story story = Story.fromJSON(storyJSON);
                this.stories.add(story);
            }

            JSONArray postsJSON = new JSONArray(new JSONTokener(postsFile));
            for (int i = 0; i < postsJSON.length(); i++) {
                JSONObject postJSON = postsJSON.getJSONObject(i);
                Post post = Post.fromJSON(postJSON);
                this.posts.add(post);
            }

            System.out.println("Data loaded successfully.");
        } catch (JSONException e) {
            System.out.println("Either posts file or story file have invalid JSON syntax");
        } catch (IOException e) {

        }
    }

    public synchronized void save() {

        JSONArray storiesJSON = new JSONArray();
        this.stories.forEach(story -> storiesJSON.put(story.toJSON()));

        JSONArray postsJSON = new JSONArray();
        this.posts.forEach(post -> postsJSON.put(post.toJSON()));

        try (FileWriter storiesFile = new FileWriter(STORYFILE); FileWriter postsFile = new FileWriter(POSTFILE)) {
            storiesFile.write(storiesJSON.toString(4));
            postsFile.write(postsJSON.toString(4));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shutDown() {
        System.out.println("Shutting down ContentDataBase...");
        System.out.println("\tSaving content database");
        this.save();

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
}

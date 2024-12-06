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
1- sync this class
3- make id generator a diff class or use uuid
 */
/**
 *
 * @author moustafa
 */
public class ContentDataBase {

    private final ArrayList<Post> posts = new ArrayList<>();
    private final ArrayList<Story> stories = new ArrayList<>();
    private static final ContentDataBase dataBase = new ContentDataBase();
    private final ScheduledExecutorService scheduler;

    private static int id = 0;

    private ContentDataBase() {
        this.load();
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
        this.stories.add(cont);
        this.save();
    }

    private void removeStory() {
        for (Story s : this.stories) {
            Duration duration = Duration.between(s.getTimeOfUpload(), LocalDateTime.now());
            if (duration.toHours() >= 24) {
                this.stories.remove(s);
            }
        }
    }

    protected ArrayList<Post> getPosts() {
        return posts;
    }

    protected ArrayList<Story> getStories() {
        return stories;
    }

    public ArrayList<Post> getFriendsPosts(User user) {
        ArrayList<Post> friendsPosts = new ArrayList<>();
        for (Post post : this.posts) {
            if (user.getUserFriends().contains(post.getAuthor())) {
                friendsPosts.add(post);
            }
        }
        return friendsPosts;
    }

    public ArrayList<Story> getFriendsStories(User user) {
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

    protected final void load() {

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

    public void save() {

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

    public void shutDown(){
        System.out.println("Saving content database");
            this.save();
            
            System.out.println("Shutting down scheduler...");
            scheduler.shutdownNow();
    }
}

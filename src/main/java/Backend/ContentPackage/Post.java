/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

import Backend.GroupPackage.Group;
import Backend.GroupPackage.GroupDatabase;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author moustafa
 */
public class Post extends AContent {

    public Post(Post.Builder postBuilder) {
        super(postBuilder.groupId,postBuilder.authorId,
                postBuilder.text, 
                postBuilder.imagePath,
                postBuilder.timeOfUpload,
                postBuilder.contentId,
                postBuilder.comments,
                postBuilder.likes);
    }

    @Override
    public void uplode() {
        this.setTimeOfUpload();
        ContentDataBase.getInstance().addContent(this);
    }
    
  
    
    

    public static Post fromJSON(JSONObject jsonObject) {
        String groupId = jsonObject.getString("groupId");
        String userId = jsonObject.getString("authorId");
        String text = jsonObject.getString("text");
        String imagePath = jsonObject.optString("imagePath", null);
        String timeStamp = jsonObject.getString("timeStamp");
        UUID contentId = UUID.fromString(jsonObject.getString("contentId"));
        LocalDateTime timeOfUpload = LocalDateTime.parse(timeStamp,AContent.getTimeStampFormat());
        ArrayList <Comment> comments = new ArrayList<>();
        JSONArray commentsArray = jsonObject.getJSONArray("comments");
        for (int i = 0; i < commentsArray.length(); i++) {
          JSONObject commentJson = commentsArray.getJSONObject(i);
          Comment comment = Comment.fromJSON(commentJson);
          if (comment != null) { // Ensure the deserialization didn't fail
           comments.add(comment);
          if(!CommentDatabase.getInstance().checkIfExists(comment)){
           CommentDatabase.getInstance().addComment(comment);
          }
        } 
      }
        JSONObject likesJson = jsonObject.getJSONObject("likes");
        Map <String, Integer> likes = new HashMap<>();
        Iterator <String> keys = likesJson.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            likes.put(key, likesJson.getInt(key));
        } 
        Post.Builder postBuilder = new Post.Builder(
                groupId,
                userId,
                text
        )
                .setImagePath(imagePath)
                .setTimeOfUpload(timeOfUpload)
                .setContentId(contentId)
                .setContentComments(comments)
                .setContentLikes(likes);
                

        return postBuilder.build();
    }

    public static class Builder {
        
        private String groupId = "";
        private final String text;
        private final String authorId;
        private String imagePath;
        private LocalDateTime timeOfUpload;
        private UUID contentId;
        private ArrayList <Comment> comments;
        private Map <String,Integer> likes;

        public Builder(String groupId,String authorId, String text) {
            if (authorId == null || text == null || text.isEmpty()) {
                throw new IllegalArgumentException("Author and text cannot be null or empty.");
            }
            if(groupId != null || groupId.length()!=0){
                this.groupId = groupId;
            }
            this.authorId = authorId;
            this.text = text;
            this.comments = new ArrayList<>();
            this.likes = new HashMap<>();
            this.imagePath = null;
            this.timeOfUpload = null;
        }

        public Builder setImagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }
        
        public Builder setTimeOfUpload(LocalDateTime timeOfUpload) {
            this.timeOfUpload = timeOfUpload;
            return this;
        }
        
        public Builder setContentId(UUID id){
            this.contentId = id;
            return this;
        }
        
        public Builder setContentComments(ArrayList <Comment> comments){
            this.comments = comments;
            return this;
        }
        
        public Builder setContentLikes(Map <String,Integer> likes){
            this.likes = likes;
            return this;
        }
 
        public Post build() {
            return new Post(this);
        }
    }
}

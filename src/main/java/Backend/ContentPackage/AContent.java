/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

import Backend.ChatPackage.Message;
import Backend.ChatPackage.MessageDatabase;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author moustafa
 */
public abstract class AContent implements Comparable<AContent> {

    private final UUID contentId;
    private final String authorId;
    private String groupId = "";
    private String text;
    private String imagePath;
    private ArrayList <Comment> comments;
    private Map <String,Integer> likes;
    
    /*

timeOfUpload is LocalDateTime
timeStamp is String representation of timeOfUpload

     */
    private LocalDateTime timeOfUpload;

    private static final DateTimeFormatter timeStampFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    protected AContent(String groupId,String authorId, String text, String imagePath, LocalDateTime timeOfUpload, UUID contentId,ArrayList <Comment> comments,Map <String,Integer> likes ) {
        //if condition not needed because the check is in the post and story builder 
        if (authorId == null || text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Author and text cannot be null or empty.");
        }
        //
        if(groupId != null || groupId.length() != 0){
            this.groupId = groupId;
        }
        this.authorId = authorId;
        this.text = text;
        this.timeOfUpload = timeOfUpload;
        this.imagePath = imagePath;
        if (contentId == null) {
            this.contentId = UUID.randomUUID();
        } else {
            this.contentId = contentId;
        }
        this.comments = comments;
        this.likes = likes;
    }

    public String getTimestamp() {
        return this.timeOfUpload.format(AContent.timeStampFormat);
    }

    public String getText() {
        return text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public UUID getContentId() {
        return contentId;
    }

    public String getAuthorId() {
        return authorId;
    }
    
    public String getGroupId(){
        return this.groupId;
    }

    public static DateTimeFormatter getTimeStampFormat() {
        return AContent.timeStampFormat;
    }

    public LocalDateTime getTimeOfUpload() {
        return timeOfUpload;
    }
    
    public ArrayList <Comment> getPostComments(){
            return this.comments;
      }
    
    public Map <String,Integer> getPostLikes(){
        return this.likes;
    }
    
    protected void setTimeOfUpload() {
        this.timeOfUpload = LocalDateTime.now();
    }
    
    public void setPostText(String text){
        this.text = text;
    }
    
    public void setPostImage(String imagePath){
        this.imagePath = imagePath;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("contentId", this.contentId);
        jsonObject.put("authorId", this.authorId);

        jsonObject.put("text", this.text);
        jsonObject.put("imagePath", this.imagePath != null ? this.imagePath : JSONObject.NULL);

        jsonObject.put("timeStamp", this.getTimestamp());
        jsonObject.put("groupId",this.groupId);
        
        JSONArray commentsArray = new JSONArray();
        for (Comment comment : CommentDatabase.getInstance().getComments()) {
            if(comment.getCommentPostId().equals(this.contentId)){
            commentsArray.put(comment.toJSON());}  
        }
        jsonObject.put("comments", commentsArray);
        
        jsonObject.put("likes",this.likes);

        return jsonObject;
    }
    
    
    
    
    
    public abstract void uplode();

    @Override
    public int compareTo(AContent o) {
        return this.timeOfUpload.compareTo(o.timeOfUpload);
    }

}

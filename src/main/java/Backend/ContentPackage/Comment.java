/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

import java.util.UUID;
import org.json.JSONObject;

/**
 *
 * @author Abdelrahman
 */
public class Comment {
    
    private UUID commentPostId;
    private String commentId;
    private String commentPublisher;
    private String commentContent;
    
    private Comment(UUID commentPostId,String commentId,String commentPublisher,String commentContent){
        this.commentPostId = commentPostId;
        this.commentId = commentId;
        this.commentPublisher = commentPublisher;
        this.commentContent = commentContent;
    }

    private Comment() {
       
    }
    
           /* Getters */
    public UUID getCommentPostId(){
        return this.commentPostId;
    }
    
    public String getCommentId(){
        return this.commentId;
    }
    
    public String getCommentPublisher(){
        return this.commentPublisher;
    }
    
    public String getCommentContent(){
        return this.commentContent;
    }
    
            /* Setters */
    public void setCommentId(String commentId){
        this.commentId = commentId;
    }
    
    public void setCommentPublisher(String commentPublisher){
        this.commentPublisher = commentPublisher;
    }
    
    public void setCommentContent(String commentContent){
        this.commentContent = commentContent;
    }
    
    public void setCommentPostId(UUID commentPostId){
        this.commentPostId = commentPostId;
    }
    
     public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("commentPostId",this.commentPostId);
        jsonObject.put("commentId",this.commentId);
        jsonObject.put("commentPublisher",this.commentPublisher);
        jsonObject.put("commentContent",this.commentContent);
        return jsonObject;
    }
     
     
      public static Comment fromJSON(JSONObject object){
        Comment comment = new Comment();
        comment.commentPostId = UUID.fromString(object.getString("commentPostId"));
        comment.commentId = object.getString("commentId");
        comment.commentPublisher = object.getString("commentPublisher");
        comment.commentContent = object.getString("commentContent");
        return comment;
     }
    
    public static class commentBuilder{
         
        public static Comment commentCreate(UUID commentPostId,String commentId,String commentPublisher,String commentContent){
            return new Comment(commentPostId,commentId,commentPublisher,commentContent);
        }
        
    }
 
}

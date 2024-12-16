/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

import Backend.ChatPackage.Message;
import Backend.ChatPackage.MessageDatabase;
import Backend.UserPackage.User;
import Backend.UserPackage.UserDatabase;
import static Files.FILEPATHS.USERFILE;
import java.util.ArrayList;

/**
 *
 * @author Abdelrahman
 */
public class CommentDatabase {
    
    private static CommentDatabase commentDatabase;
    private int uniqueCounter;
    private ArrayList <Comment> comments;
    
    
    private CommentDatabase() {
        this.comments = new ArrayList<>();
        uniqueCounter = 1;
    }
    
    public static synchronized CommentDatabase getInstance() {
        if (commentDatabase == null) {
            commentDatabase = new CommentDatabase();
        }
        return commentDatabase;
    }
    
     public int getUniqueCounter() {
        return uniqueCounter;
    }
    
    public ArrayList <Comment> getComments(){
        return this.comments;
    }
    
    public void setCounter(int counter){
        this.uniqueCounter = counter;
    }
    
    public boolean checkIfExists(Comment comment){
        for(Comment commentt:this.comments){
            if(commentt.getCommentId().equals(comment.getCommentId())) return true;
        }
        return false;
    }
    
    
     public void addComment(Comment comment){
        if(comment!=null){
            this.comments.add(comment);
            
         String[] parts = comment.getCommentId().split("-");
        if (parts.length == 2) {
            try {
                int idCounter = Integer.parseInt(parts[1]);
                uniqueCounter = Math.max(uniqueCounter, idCounter + 1);
            } catch (NumberFormatException e) {
                System.err.println("Invalid UserId format for counter adjustment.");
            }
        }
        }
        
    }
    
    public boolean addComment(Post post,User commentPublisher,String commentContent){
       if(commentContent == null || commentContent.length() == 0) return false;
        for(Post postt:ContentDataBase.getInstance().getPosts()){
            if(postt.getContentId().equals(post.getContentId())){
                    String id = "Comment" + "-" + uniqueCounter;
                    Comment newComment = Comment.commentBuilder.commentCreate(postt.getContentId(),id,commentPublisher.getUserId(),commentContent);
                    addComment(newComment);
                    ContentDataBase.getInstance().update();
                    return true;
            }  
        }
        return false;
    }
}

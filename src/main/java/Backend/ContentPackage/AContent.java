/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

import Backend.UserPackage.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author moustafa
 */
public abstract class AContent {

    private final int contentId;
    private final User author;

    private String text;
    private String imagePath;

    private LocalDateTime timeOfUpload;

    private static final DateTimeFormatter timeStampFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String getTimestamp() {
        return this.timeOfUpload.format(AContent.timeStampFormat);
    }

    public String getText() {
        return text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getContentId() {
        return contentId;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getTimeOfUpload() {
        return timeOfUpload;
    }
    
    public static DateTimeFormatter getTimeStampFormat(){
        return AContent.timeStampFormat;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setTimeOfUpload(LocalDateTime timeOfUpload) {
        this.timeOfUpload = timeOfUpload;
    }

    
    public void setTimeOfUpload() {
        this.timeOfUpload = LocalDateTime.now();
    }

    public AContent(User author) {
        this.author = author;
        this.contentId = ContentDataBase.getUniqueId();
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("contentId", this.contentId);
        jsonObject.put("authorId", this.author.getUserId());

        jsonObject.put("text", this.text != null ? this.text : JSONObject.NULL);
        jsonObject.put("imagePath", this.imagePath != null ? this.imagePath : JSONObject.NULL);        
        
        jsonObject.put("timestamp", this.getTimestamp());

        return jsonObject;
    }

    public abstract void uplode();
}

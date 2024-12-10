/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

import Backend.UserPackage.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.json.JSONObject;

/*
TODO
1- add factory
 */
/**
 *
 * @author moustafa
 */
public abstract class AContent implements Comparable<AContent> {

    private final UUID contentId;
    private final User author;

    private String text;
    private String imagePath;

    /*

timeOfUpload is LocalDateTime
timeStamp is String representation of timeOfUpload

     */
    private LocalDateTime timeOfUpload;

    private static final DateTimeFormatter timeStampFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    protected AContent(User author, String text, String imagePath, LocalDateTime timeOfUpload, UUID contentId) {
        //if condition not needed because the check is in the post and story builder 
        if (author == null || text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Author and text cannot be null or empty.");
        }
        //
        this.author = author;
        this.text = text;
        this.timeOfUpload = timeOfUpload;
        this.imagePath = imagePath;
        if (contentId == null) {
            this.contentId = UUID.randomUUID();
        } else {
            this.contentId = contentId;
        }
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

    public User getAuthor() {
        return author;
    }

    public static DateTimeFormatter getTimeStampFormat() {
        return AContent.timeStampFormat;
    }

    public LocalDateTime getTimeOfUpload() {
        return timeOfUpload;
    }

    protected void setTimeOfUpload() {
        this.timeOfUpload = LocalDateTime.now();
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("contentId", this.contentId);
        jsonObject.put("authorId", this.author.getUserId());

        jsonObject.put("text", this.text);
        jsonObject.put("imagePath", this.imagePath != null ? this.imagePath : JSONObject.NULL);

        jsonObject.put("timeStamp", this.getTimestamp());

        return jsonObject;
    }

    public abstract void uplode();

    @Override
    public int compareTo(AContent o) {
        return this.contentId.toString().compareTo(o.contentId.toString());
    }

}

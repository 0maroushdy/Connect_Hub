/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.json.JSONObject;

/**
 *
 * @author moustafa
 */
public abstract class AContent implements Comparable<AContent> {

    private final UUID contentId;
    private final UUID authorId;

    private String text;
    private String imagePath;

    /*

timeOfUpload is LocalDateTime
timeStamp is String representation of timeOfUpload

     */
    private LocalDateTime timeOfUpload;

    private static final DateTimeFormatter timeStampFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    protected AContent(UUID authorId, String text, String imagePath, LocalDateTime timeOfUpload, UUID contentId) {
        //if condition not needed because the check is in the post and story builder 
        if (authorId == null || text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Author and text cannot be null or empty.");
        }
        //
        this.authorId = authorId;
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

    public UUID getAuthorId() {
        return authorId;
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
        jsonObject.put("authorId", this.authorId);

        jsonObject.put("text", this.text);
        jsonObject.put("imagePath", this.imagePath != null ? this.imagePath : JSONObject.NULL);

        jsonObject.put("timeStamp", this.getTimestamp());

        return jsonObject;
    }

    public abstract void uplode();

    @Override
    public int compareTo(AContent o) {
        return this.contentId.compareTo(o.contentId);
    }

}

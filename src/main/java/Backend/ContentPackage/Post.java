/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

import java.time.LocalDateTime;
import java.util.UUID;
import org.json.JSONObject;

/**
 *
 * @author moustafa
 */
public class Post extends AContent {

    public Post(Post.Builder postBuilder) {
        super(postBuilder.authorId,
                postBuilder.text, 
                postBuilder.imagePath,
                postBuilder.timeOfUpload,
                postBuilder.contentId);
    }

    @Override
    public void uplode() {
        this.setTimeOfUpload();
        ContentDataBase.getInstance().addContent(this);
    }

    public static Post fromJSON(JSONObject jsonObject) {
        
        String userId = jsonObject.getString("authorId");
        String text = jsonObject.getString("text");
        String imagePath = jsonObject.optString("imagePath", null);
        String timeStamp = jsonObject.getString("timeStamp");
        UUID contentId = UUID.fromString(jsonObject.getString("contentId"));
        LocalDateTime timeOfUpload = LocalDateTime.parse(timeStamp,AContent.getTimeStampFormat());
        
        Post.Builder postBuilder = new Post.Builder(
                userId,
                text
        )
                .setImagePath(imagePath)
                .setTimeOfUpload(timeOfUpload)
                .setContentId(contentId);

        return postBuilder.build();
    }

    public static class Builder {

        private final String text;
        private final String authorId;
        private String imagePath;
        private LocalDateTime timeOfUpload;
        private UUID contentId;

        public Builder(String authorId, String text) {
            if (authorId == null || text == null || text.isEmpty()) {
                throw new IllegalArgumentException("Author and text cannot be null or empty.");
            }
            this.authorId = authorId;
            this.text = text;
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

        public Post build() {
            return new Post(this);
        }
    }
}

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
public class Story extends AContent {

    public Story(Story.Builder storyBuilder) {
        super(storyBuilder.authorId,
                storyBuilder.text,
                storyBuilder.imagePath,
                storyBuilder.timeOfUpload,
                storyBuilder.contentId);
    }

    @Override
    public void uplode() {
        this.setTimeOfUpload();
        ContentDataBase.getInstance().addContent(this);
    }

    public static Story fromJSON(JSONObject jsonObject) {
        String userId = jsonObject.getString("authorId");
        String text = jsonObject.getString("text");
        String imagePath = jsonObject.optString("imagePath", null);
        String timeStamp = jsonObject.getString("timeStamp");
        UUID contentId = UUID.fromString(jsonObject.getString("contentId"));
        LocalDateTime timeOfUpload = LocalDateTime.parse(timeStamp,AContent.getTimeStampFormat());
            
        Story.Builder storyBuilder = new Story.Builder(
                userId,
                text
        )
                .setImagePath(imagePath)
                .setTimeOfUpload(timeOfUpload)
                .setContentId(contentId);

        return storyBuilder.build();
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

        public Story build() {
            return new Story(this);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

import Backend.UserPackage.User;
import Backend.UserPackage.UserDatabase;
import java.time.LocalDateTime;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author moustafa
 */
public class Story extends AContent {

    public Story(Story.Builder storyBuilder) {
        super(storyBuilder.author,
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
        //debug
//            System.out.println(UserDatabase.getInstance().getUsers());
        //
        String userId = jsonObject.getString("authorId");
        User u = UserDatabase.getInstance().getUser(userId);
        String text = jsonObject.getString("text");
        String imagePath = jsonObject.optString("imagePath", null);
        String timeStamp = jsonObject.getString("timeStamp");
        UUID contentId = UUID.fromString(jsonObject.getString("contentId"));
        LocalDateTime timeOfUpload = LocalDateTime.parse(timeStamp,AContent.getTimeStampFormat());
            
        Story.Builder storyBuilder = new Story.Builder(
                u,
                text
        )
                .setImagePath(imagePath)
                .setTimeOfUpload(timeOfUpload)
                .setContentId(contentId);

        return storyBuilder.build();
    }

    public static class Builder {

        private final String text;
        private final User author;
        private String imagePath;
        private LocalDateTime timeOfUpload;
        private UUID contentId;

        public Builder(User author, String text) {
            if (author == null || text == null || text.isEmpty()) {
                //debug
//                    System.out.println(author);
//                    System.out.println(text);
                //
                throw new IllegalArgumentException("Author and text cannot be null or empty.");
            }
            this.author = author;
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

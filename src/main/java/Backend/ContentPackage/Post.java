/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ContentPackage;

import Backend.UserPackage.User;
import Backend.UserPackage.UserDatabase;
import java.time.LocalDateTime;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author moustafa
 */
public class Post extends AContent {

    public Post(User authorId) {
        super(authorId);
    }

    @Override
    public void uplode() {
        this.setTimeOfUpload();
        ContentDataBase.getInstance().addContent(this);
    }

    public static Post fromJSON(JSONObject jsonObject) {
        User author = UserDatabase.getInstance().getUserFromId(jsonObject.getString("authorId"));
        Post post = new Post(author);
        post.setText(jsonObject.getString("text"));
        post.setImagePath(jsonObject.optString("imagePath", null));

        String timeOfUploadStr = jsonObject.getString("timestamp");
        post.setTimeOfUpload(LocalDateTime.parse(timeOfUploadStr, AContent.getTimeStampFormat()));
        return post;
    }
}

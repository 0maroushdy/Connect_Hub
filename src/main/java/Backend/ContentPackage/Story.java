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
public class Story extends AContent {

    public Story(User authorId) {
        super(authorId);
    }

    @Override
    public void uplode() {
        this.setTimeOfUpload();
        ContentDataBase.getInstance().addContent(this);
    }

    public static Story fromJSON(JSONObject jsonObject) {
        User author = UserDatabase.getInstance().getUserFromId(jsonObject.getString("authorId"));
        Story story = new Story(author);
        story.setText(jsonObject.getString("text"));
        story.setImagePath(jsonObject.optString("imagePath", null));

        String timeOfUploadStr = jsonObject.getString("timestamp");
        story.setTimeOfUpload(LocalDateTime.parse(timeOfUploadStr, AContent.getTimeStampFormat()));
        return story;
    }
}

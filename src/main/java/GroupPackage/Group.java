/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupPackage;

import Backend.ContentPackage.JSONUtils;
import static Files.FILEPATHS.ROCKLY;
import java.util.ArrayList;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/*
TODO
.- every photo that will be saved to the datases should get copied to an
enternal folder
.- make a good interface for checking for permissions
 */
/**
 *
 * @author moustafa
 */
public class Group {

    private final UUID id;
    private String name;
    private String description;
    private String photoPath;

    private GroupHandler handler;

    private Group(String name, GroupHandler handler, String description, String photoPath, UUID id) {

        if (name == null || name.equals("") || handler == null) {
            throw new IllegalArgumentException("Name and handler cannot be null or empty.");
        }

        this.id = (id == null) ? UUID.randomUUID() : id;

        this.name = name;

        this.handler = handler;

        //optional
        this.description = description;
        this.photoPath = photoPath;
        //
    }

    public Group(Group.Builder groupBuilder) {
        this(groupBuilder.name,
                groupBuilder.handler,
                groupBuilder.description,
                groupBuilder.photoPath,
                groupBuilder.id);
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", this.id);
        jsonObject.put("name", this.name);

        //optional attributes
        jsonObject.put("description", this.description);
        jsonObject.put("photoPath", this.photoPath);
        //

        jsonObject.put("handler", this.handler.toJSON());

        return jsonObject;
    }

    public static Group fromJSON(JSONObject jsonObject) {

        UUID id = UUID.fromString(jsonObject.getString("id"));
        String name = jsonObject.getString("name");

        String description = jsonObject.getString("description");
        String photoPath = jsonObject.getString("photoPath");

       GroupHandler handler = GroupHandler.fromJSON(jsonObject.getJSONObject("handler"));

        Group.Builder groupBuilder = new Group.Builder(name,handler)
                .setId(id)
                .setDescription(description)
                .setPhotoPath(photoPath);

        return groupBuilder.build();
    }

    public static class Builder {

        private UUID id;
        private String name;
        private GroupHandler handler;

        private String description;
        private String photoPath;

        public Builder(String name, GroupHandler handler) {
            if (name == null || name.equals("") || handler == null) {
                throw new IllegalArgumentException("Name and handler cannot be null or empty.");
            }
            this.handler = handler;
            this.name = name;

            //defaults for optional attributes
            this.description = "";
            this.photoPath = ROCKLY;
            //
        }

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPhotoPath(String photoPath) {
            this.photoPath = photoPath;
            return this;
        }

        public Group build() {
            return new Group(this);
        }
    }

}

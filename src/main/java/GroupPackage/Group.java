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

    private final String mainAdminId;
    private final ArrayList<String> adminIds;
    private final ArrayList<String> memberIds;

    private final ArrayList<String> joinRequests;

    private Group(String name, String mainAdminId, String description, String photoPath, UUID id,
            ArrayList<String> adminIds, ArrayList<String> memberIds, ArrayList<String> joinRequests) {
        if (name == null || name.equals("") || mainAdminId == null) {
            throw new IllegalArgumentException("Name and mainAdmin cannot be null or empty.");
        }

        this.id = (id == null) ? UUID.randomUUID() : id;

        this.name = name;
        this.mainAdminId = mainAdminId;

        //optional
        this.description = description;
        this.photoPath = photoPath;
        //

        if (adminIds == null) {
            this.adminIds = new ArrayList<>();
            this.adminIds.add(mainAdminId);
        } else {
            this.adminIds = adminIds;
        }

        if (memberIds == null) {
            this.memberIds = new ArrayList<>();
            this.memberIds.add(mainAdminId);
        } else {
            this.memberIds = memberIds;
        }

        this.joinRequests = (joinRequests == null) ? new ArrayList<>() : joinRequests;
    }

    public Group(Group.Builder groupBuilder) {
        this(groupBuilder.name,
                groupBuilder.mainAdminId,
                groupBuilder.description,
                groupBuilder.photoPath,
                groupBuilder.id,
                groupBuilder.adminIds,
                groupBuilder.memberIds,
                groupBuilder.joinRequests);
    }

    private void validateInputUsers(String actorId, String targetId) {
        if (actorId == null || targetId == null) {
            throw new IllegalArgumentException("Actor and target cannot be null.");
        }
        if (actorId.equals(targetId)) {
            throw new IllegalArgumentException("Actor and target cannot be the same user.");
        }
    }

    public void promote(String actorId, String targetId) {
        validateInputUsers(actorId, targetId);

        //check for minimun permissions of actor to perform the action
        if (!actorId.equals(this.mainAdminId)) {
            throw new IllegalArgumentException("User with Id: " + actorId
                    + " doesn't have permission");
        }
        //

        if (this.adminIds.contains(targetId)) {
            throw new IllegalArgumentException("User with Id: " + targetId
                    + " is already an admin");
        }

        if (!this.memberIds.contains(targetId)) {
            throw new IllegalArgumentException("User with Id: " + targetId
                    + " is not in the group");
        }

        this.adminIds.add(targetId);
    }

    public void demote(String actorId, String targetId) {
        validateInputUsers(actorId, targetId);

        //check for minimun permissions of actor to perform the action
        if (!actorId.equals(this.mainAdminId)) {
            throw new IllegalArgumentException("User with Id: " + actorId
                    + " doesn't have permission");
        }
        //

        if (!this.adminIds.contains(targetId)) {
            throw new IllegalArgumentException("User with Id: " + targetId
                    + " is not an admin");
        }

        this.adminIds.remove(targetId);
    }

    public void removeMember(String actorId, String targetId) {
        validateInputUsers(actorId, targetId);

        //check for minimun permissions of actor to perform the action
        if (this.adminIds.contains(actorId)) {
            throw new IllegalArgumentException("User with Id: " + actorId
                    + " doesn't have permission");
        }
        //

        if (actorId.equals(this.mainAdminId)) {
            this.adminIds.remove(targetId);
        }

        if (!this.memberIds.remove(targetId)) {
            throw new IllegalArgumentException("User with Id: " + targetId
                    + " is not in the group");
        }
    }

    public void approveJoinRequest(String actorId, String targetId) {
        validateInputUsers(actorId, targetId);

        //check for minimun permissions of actor to perform the action
        if (!this.adminIds.contains(actorId)) {
            throw new IllegalArgumentException("User with Id: " + actorId
                    + " doesn't have permission");
        }
        //

        if (this.memberIds.contains(targetId)) {
            throw new IllegalArgumentException("User with Id: " + targetId
                    + " is already in the group");
        }

        if (!this.joinRequests.remove(targetId)) {
            throw new IllegalArgumentException("User with Id: " + targetId
                    + " didn't request to join");
        }

        this.memberIds.add(targetId);
    }

    public void rejectJoinRequest(String actorId, String targetId) {
        validateInputUsers(actorId, targetId);

        //check for minimun permissions of actor to perform the action
        if (!this.adminIds.contains(actorId)) {
            throw new IllegalArgumentException("User with Id: " + actorId
                    + " doesn't have permission");
        }
        //

        if (this.memberIds.contains(targetId)) {
            throw new IllegalArgumentException("User with Id: " + targetId
                    + " is already in the group");
        }

        if (!this.joinRequests.remove(targetId)) {
            throw new IllegalArgumentException("User with Id: " + targetId
                    + " didn't request to join");
        }
    }

    public void leave(String actorId) {
        if (actorId == null) {
            throw new IllegalArgumentException("actor can't be null");
        }
        if (actorId.equals(this.mainAdminId)) {
            throw new IllegalArgumentException("The group's main admin can't leave the group");
        }

        this.adminIds.remove(actorId);

        if (!this.memberIds.remove(actorId)) {
            throw new IllegalArgumentException("User with Id: " + actorId
                    + " is not in the group");
        }
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", this.id);
        jsonObject.put("name", this.name);

        //optional attributes
        jsonObject.put("description", this.description);
        jsonObject.put("photoPath", this.photoPath);
        //
        
        jsonObject.put("mainAdminId", this.mainAdminId);
        jsonObject.put("adminIds", new JSONArray(this.adminIds));
        jsonObject.put("memberIds", new JSONArray(this.memberIds));
        jsonObject.put("joinRequests", new JSONArray(this.joinRequests));

        return jsonObject;
    }

    public static Group fromJSON(JSONObject jsonObject) {

        UUID id = UUID.fromString(jsonObject.getString("id"));
        String name = jsonObject.getString("name");

        String description = jsonObject.getString("description");
        String photoPath = jsonObject.getString("photoPath");

        String mainAdminId = jsonObject.getString("mainAdminId");

        ArrayList<String> adminIds = JSONUtils.loadArrayList(jsonObject, "adminIds");
        ArrayList<String> memberIds = JSONUtils.loadArrayList(jsonObject, "memberIds");
        ArrayList<String> joinRequests = JSONUtils.loadArrayList(jsonObject, "joinRequests");

        Group.Builder groupBuilder = new Group.Builder(mainAdminId, name)
                .setId(id)
                .setDescription(description)
                .setPhotoPath(photoPath)
                .setAdminIds(adminIds)
                .setMemberIds(memberIds)
                .setJoinRequests(joinRequests);

        return groupBuilder.build();
    }

    public static class Builder {

        private UUID id;
        private String mainAdminId;
        private String name;

        private String description;
        private String photoPath;

        private ArrayList<String> adminIds;
        private ArrayList<String> memberIds;
        private ArrayList<String> joinRequests;

        public Builder(String mainAdminId, String name) {
            if (name == null || name.equals("") || mainAdminId == null) {
                throw new IllegalArgumentException("Name and mainAdmin cannot be null or empty.");
            }
            this.mainAdminId = mainAdminId;
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

        public Builder setAdminIds(ArrayList<String> adminIds) {
            this.adminIds = adminIds;
            return this;
        }

        public Builder setMemberIds(ArrayList<String> memberIds) {
            this.memberIds = memberIds;
            return this;
        }

        public Builder setJoinRequests(ArrayList<String> joinRequests) {
            this.joinRequests = joinRequests;
            return this;
        }

        public Group build() {
            return new Group(this);
        }
    }
}

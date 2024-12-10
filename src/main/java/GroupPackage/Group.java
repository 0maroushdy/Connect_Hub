/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupPackage;

import Backend.UserPackage.User;
import java.util.ArrayList;
import java.util.UUID;

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

    private final User mainAdmin;
    private final ArrayList<User> admins;
    private final ArrayList<User> members;

    private final ArrayList<User> joinRequests;

    private Group(String name, User mainAdmin, String description, String photoPath, UUID id) {
        if (name == null || name.equals("") || mainAdmin == null) {
            throw new IllegalArgumentException("Name and mainAdmin cannot be null or empty.");
        }

        this.id = (id == null) ? UUID.randomUUID() : id;

        this.name = name;
        this.mainAdmin = mainAdmin;

        //optional
        this.description = description;
        this.photoPath = photoPath;
        //

        this.admins = new ArrayList<>();
        this.members = new ArrayList<>();
        this.joinRequests = new ArrayList<>();

        this.admins.add(mainAdmin);
        this.members.add(mainAdmin);
    }
    
    public Group(Group.Builder groupBuilder){
        this(groupBuilder.name,
                groupBuilder.mainAdmin,
                groupBuilder.description,
                groupBuilder.photoPath,
                groupBuilder.id);
    }

    private void validateInputUsers(User actor, User target) {
        if (actor == null || target == null) {
            throw new IllegalArgumentException("Actor and target cannot be null.");
        }
        if (actor == target) {
            throw new IllegalArgumentException("Actor and target cannot be the same user.");
        }
    }

    public void promote(User actor, User target) {
        validateInputUsers(actor, target);

        //check for minimun permissions of actor to perform the action
        if (actor != this.mainAdmin) {
            System.out.println("User: " + actor.getUsername()
                    + " doesn't have permission");
            return;
        }
        //

        if (this.admins.contains(target)) {
            System.out.println("User: " + target.getUsername()
                    + " is already an admin");
        }

        if (!this.members.contains(target)) {
            System.out.println("User: " + target.getUsername()
                    + " is not in the group");
            return;
        }

        this.admins.add(target);
    }

    public void demote(User actor, User target) {
        validateInputUsers(actor, target);

        //check for minimun permissions of actor to perform the action
        if (actor != this.mainAdmin) {
            System.out.println("User: " + actor.getUsername()
                    + " doesn't have permission");
            return;
        }
        //

        if (!this.admins.contains(target)) {
            System.out.println("User: " + target.getUsername()
                    + " is not an admin");
            return;
        }

        this.admins.remove(target);
    }

    public void removeMember(User actor, User target) {
        validateInputUsers(actor, target);

        //check for minimun permissions of actor to perform the action
        if (this.admins.contains(actor)) {
            System.out.println("User: " + actor.getUsername()
                    + " doesn't have permission");
            return;
        }
        //

        if (actor == this.mainAdmin) {
            this.admins.remove(target);
        }

        if (!this.members.remove(target)) {
            System.out.println("User: " + target.getUsername()
                    + " is not in the group");
        }
    }

    public void approveJoinRequest(User actor, User target) {
        validateInputUsers(actor, target);

        //check for minimun permissions of actor to perform the action
        if (!this.admins.contains(actor)) {
            System.out.println("User: " + actor.getUsername()
                    + " doesn't have permission");
            return;
        }
        //

        if (this.members.contains(target)) {
            System.out.println("User: " + target.getUsername()
                    + " is already in the group");
            return;
        }

        if (!this.joinRequests.remove(target)) {
            System.out.println("User: " + target.getUsername()
                    + " didn't request to join");
            return;
        }

        this.members.add(target);
    }

    public void rejectJoinRequest(User actor, User target) {
        validateInputUsers(actor, target);

        //check for minimun permissions of actor to perform the action
        if (!this.admins.contains(actor)) {
            System.out.println("User: " + actor.getUsername()
                    + " doesn't have permission");
            return;
        }
        //

        if (this.members.contains(target)) {
            System.out.println("User: " + target.getUsername()
                    + " is already in the group");
            return;
        }

        if (!this.joinRequests.remove(target)) {
            System.out.println("User: " + target.getUsername()
                    + " didn't request to join");
            return;
        }
    }

    public void leave(User actor) {
        if (actor == null) {
            throw new IllegalArgumentException("actor can't be null");
        }
        if (actor == this.mainAdmin) {
            throw new IllegalArgumentException("The group's main admin can't leave the group");
        }

        this.admins.remove(actor);

        if (!this.members.remove(actor)) {
            throw new IllegalArgumentException("User: " + actor.getUsername()
                    + " is not in the group");
        }
    }

    public class Builder {

        private UUID id;
        private User mainAdmin;
        private String name;

        private String description;
        private String photoPath;

        public Builder(User mainAdmin, String name) {
            if (name == null || name.equals("") || mainAdmin == null) {
                throw new IllegalArgumentException("Name and mainAdmin cannot be null or empty.");
            }
            this.mainAdmin = mainAdmin;
            this.name = name;  
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
    }
}

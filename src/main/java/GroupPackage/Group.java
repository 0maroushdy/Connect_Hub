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
 */
/**
 *
 * @author moustafa
 */
public class Group {

    private UUID id;
    private String name;
    private String description;
    private String photoPath;

    private User mainAdmin;
    private ArrayList<User> admins;
    private ArrayList<User> members;

    public Group(String name, User mainAdmin, String description, String photoPath, UUID id) {
        if (name == null || name.equals("") || mainAdmin == null) {
            throw new IllegalArgumentException("Name and mainAdmin cannot be null or empty.");
        }

        if (id == null) {
            this.id = UUID.randomUUID();
        }

        this.name = name;
        this.mainAdmin = mainAdmin;

        //optional
        this.description = description;
        this.photoPath = photoPath;
        //

        this.admins = new ArrayList<>();
        this.members = new ArrayList<>();

        this.admins.add(mainAdmin);
        this.members.add(mainAdmin);
    }

    public void promote(User actor, User target) {
        if (actor == target) {
            System.out.println("both actor and target are the same user");
            return;
        }

        if (actor != this.mainAdmin) {
            System.out.println("User: " + target.getUsername()
                    + " doesn't have permission");
            return;
        }

        if (!this.admins.contains(target)) {
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
        if (actor == target) {
            System.out.println("both actor and target are the same user");
            return;
        }

        if (actor != this.mainAdmin) {
            System.out.println("User: " + target.getUsername()
                    + " doesn't have permission");
            return;
        }

        if (!this.admins.contains(target)) {
            System.out.println("User: " + target.getUsername()
                    + " is not in the group");
            return;
        }

        this.admins.remove(target);
    }

    public void removeMember(User actor, User target) {
        if (actor == target) {
            System.out.println("both actor and target are the same user");
            return;
        }

        if (actor != this.mainAdmin) {
            System.out.println("User: " + target.getUsername()
                    + " doesn't have permission");
            return;
        }

        this.admins.remove(target);

        if (!this.members.remove(target)) {
            System.out.println("User: " + target.getUsername()
                    + " is not in the group");
        }
    }
}

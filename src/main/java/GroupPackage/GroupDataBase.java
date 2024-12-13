/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupPackage;

import Backend.ContentPackage.JSONUtils;
import static Files.FILEPATHS.GROUPFILE;
import java.io.IOException;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author moustafa
 */
public class GroupDataBase {

    private final TreeSet<Group> groups;

    private static GroupDataBase dataBase;

    private GroupDataBase() {
        this.groups = new TreeSet<>();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            this.shutdown();
        }));
    }

    public static GroupDataBase getInstance() {
        if (GroupDataBase.dataBase == null) {
            synchronized (GroupDataBase.class) {
                GroupDataBase.dataBase = new GroupDataBase();
                GroupDataBase.dataBase.load();
            }
        }
        return GroupDataBase.dataBase;
    }

    public TreeSet<Group> getGroups() {
        return new TreeSet<>(groups);
    }

    public void addGroup(Group group) {
        this.groups.add(group);
        this.update();
    }

    private synchronized void load() {
        try {
            JSONArray groupsJSON = JSONUtils.readFromFile(GROUPFILE);
            for (int i = 0; i < groupsJSON.length(); i++) {
                JSONObject groupJSON = groupsJSON.getJSONObject(i);
                Group group = Group.fromJSON(groupJSON);
                this.groups.add(group);
            }

        } catch (IOException e) {
            System.err.println("Error loading files: " + e.getMessage());
        } catch (JSONException e) {
            System.err.println("Invalid JSON format: " + e.getMessage());
        }
    }

    public synchronized void update() {
        this.load();
        this.save();
    }

    private synchronized void save() {

        JSONArray groupsJSON = new JSONArray();
        for (Group group : this.groups) {
            groupsJSON.put(group.toJSON());
        }

        try {
            JSONUtils.writeToFile(GROUPFILE, groupsJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public synchronized void remove(Group group){
        this.load();
        this.groups.remove(group);
        this.save();
    }
    
    private void shutdown() {
        System.out.println("Shutting down GroupDataBase...");
        System.out.println("\tSaving GroupDataBase");

        this.update();
    }
}

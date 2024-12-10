/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.GroupPackage;

import Backend.ContentPackage.Post;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Abdelrahman
 */
public class Group {
    
    private String groupName;
    private String groupDescription;
    private String groupPhoto;
    private String groupPrimaryAdminId;
    private ArrayList <String> groupOtherAdminsIds;
    private ArrayList <String> groupMembersIds;
   // private ArrayList <Integer> groupPostsIds;
    
    
    private Group(String groupPrimaryAdminId,String groupName,String groupDescription,String groupPhoto){
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.groupPhoto = groupPhoto;
        this.groupPrimaryAdminId = groupPrimaryAdminId;
        this.groupOtherAdminsIds = new ArrayList<>();
        this.groupMembersIds = new ArrayList<>();
       // this.groupPostsIds = new ArrayList<>();
    }
    
    public Group(){
        this.groupOtherAdminsIds = new ArrayList<>();
        this.groupMembersIds = new ArrayList<>();
    }
    
         /* Getters */
    public String getGroupName(){
        return this.groupName;
    }
    
    public String getGroupDescription(){
        return this.groupDescription;
    }
    
    public String getGroupPhoto(){
        return this.groupPhoto;
    }
    
    public String getGroupPrimaryAdminId(){
        return this.groupPrimaryAdminId;
    }
    
    public ArrayList <String> getGroupOtherAdminsIds(){
        return this.groupOtherAdminsIds;
    }
    
    public ArrayList <String> getGroupMemberIds(){
        return this.groupMembersIds;
    }
    
       /* Setters */
    public void setGroupName(String groupName){
        this.groupName = groupName;
    }
    
    public void setGroupDescription(String groupDescription){
        this.groupDescription = groupDescription;
    }
    
    public void setGroupPhoto(String groupPhoto){
        this.groupPhoto = groupPhoto;
    }
    
    public void setGroupPrimaryAdminId(String groupPrimaryAdminId){
        this.groupPrimaryAdminId = groupPrimaryAdminId;
    }
    
    public void setGroupMembersIds(ArrayList <String> groupMemberIds){
        this.groupMembersIds = groupMemberIds;
    }
    
     public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("groupName", this.groupName);
        jsonObject.put("groupDescription", this.groupDescription);
        jsonObject.put("groupPhoto", this.groupPhoto);
        jsonObject.put("groupPrimaryAdminId", this.groupPrimaryAdminId);
        jsonObject.put("groupOtherAdminsIds", new JSONArray(this.groupOtherAdminsIds));
        jsonObject.put("groupMembersIds", new JSONArray(this.groupMembersIds));
        return jsonObject;
    }
     
     public static Group fromJSON(JSONObject object){
        Group group = new Group();
        group.groupName = object.getString("groupName");
        group.groupDescription = object.getString("groupDescription");
        group.groupPhoto = object.getString("groupPhoto");
        group.groupPrimaryAdminId = object.getString("groupPrimaryAdminId");
        JSONArray otherAdminsArray = object.getJSONArray("groupOtherAdminsIds");
        ArrayList <String> groupOtherAdminsIds = new ArrayList<>();
        for (int i = 0; i < otherAdminsArray.length(); i++) {
            groupOtherAdminsIds.add(otherAdminsArray.getString(i));
        }
        group.groupOtherAdminsIds = groupOtherAdminsIds;
        JSONArray membersArray = object.getJSONArray("groupMembersIds");
        ArrayList<String> groupMembersIds = new ArrayList<>();
        for (int i = 0; i < membersArray.length(); i++) {
            groupMembersIds.add(membersArray.getString(i));
        }
        group.groupMembersIds = groupMembersIds;
        return group;
     }
    

    
    public static class GroupCreate{
        
        public static Group groupCreate(String groupPrimaryAdminId,String groupName,String groupDescription,String groupPhoto){
            return new Group(groupPrimaryAdminId,groupName,groupDescription,groupPhoto);
        }
    }
}

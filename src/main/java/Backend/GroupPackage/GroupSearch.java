/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.GroupPackage;

import Backend.UserPackage.User;
import Backend.UserPackage.UserDatabase;
import java.util.ArrayList;

/**
 *
 * @author Abdelrahman
 */
public class GroupSearch {
    
    private ArrayList <Group> searchGroups;
    private GroupDatabase groupDatabase;
    private ArrayList <User> users;
    
    private GroupSearch(String groupName){
        this.searchGroups = new ArrayList<>();
        this.searchGroups = GroupDatabase.getInstance().getGroupsByName(groupName);
        this.groupDatabase = GroupDatabase.getInstance();
        this.users = new ArrayList<>();
        this.users = UserDatabase.getInstance().getUsers();
    }
    
    public ArrayList <Group> getSearchGroups(){
        return this.searchGroups;
    }
    
    public void joinGroup(User user,Group group){
        this.groupDatabase.sendGroupRequest(user.getUserId(), group);
    }
    
    public boolean leaveGroup(String groupMemberId,Group group){
      return this.groupDatabase.leaveGroup(groupMemberId, group);
    }
    
  /*  public Group viewGroup(User user){
        for(User userr:this.users){
            if(userr.getUserId().equals(user.getUserId())){
                for(Group group:this.groupDatabase.getGroups()){
                    
                }
            }
        }
    } */
    
    public static class GroupSearchFactory{
         
        public static GroupSearch createGroupSearch(String groupName){
            return new GroupSearch(groupName);
        }
   }
    
}

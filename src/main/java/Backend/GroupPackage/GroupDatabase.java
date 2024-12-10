/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.GroupPackage;

import Backend.UserPackage.User;
import Backend.UserPackage.UserDatabase;
import static Files.FILEPATHS.USERFILE;
import java.util.ArrayList;

/**
 *
 * @author Abdelrahman
 */
public class GroupDatabase {
    
    private static GroupDatabase groupDatabase;
    private ArrayList <Group> groups;
    private ArrayList <User> users;
    
    private GroupDatabase() {
        this.groups = new ArrayList<>();
        this.users = new ArrayList<>();
        this.users = UserDatabase.getInstance().getUsers();
    }
    
    public static synchronized GroupDatabase getInstance() {
        if (groupDatabase == null) {
            groupDatabase = new GroupDatabase();
        }
        return groupDatabase;
    }
    
    public ArrayList <Group> getGroups(){
        return this.groups;
    }
    
    public ArrayList <Group> getGroupsByName(String groupName){
        ArrayList <Group> groups = new ArrayList<>();
        for(Group group:this.groups){
            if(group.getGroupName().equals(groupName)) groups.add(group);
        }
        return groups;
    }
    
    public Group getGroupByAttributes(String groupName,String groupDescription,String groupPhoto){
        for(Group group:this.groups){
            if(group.getGroupName().equals(groupName) && group.getGroupDescription().equals(groupDescription) && group.getGroupPhoto().equals(group.getGroupPhoto())){
                return group;
            }
        }
        return null;
    }
    
    public void createGroup(User user,String groupName,String groupDescription,String groupPhoto){
      for(User userr:this.users){
       if(userr.getUserId().equals(user.getUserId())){
      Group newGroup = Group.GroupCreate.groupCreate(user.getUserId(),groupName, groupDescription, groupPhoto);
      this.groups.add(newGroup);
      userr.getUserJoinedGroups().add(newGroup);
      UserDatabase.getInstance().saveUsersToFile(USERFILE);
        }
      }
    }
    
    public void joinGroup(User user,Group group){
       for(User userr:this.users){
         if(userr.getUserId().equals(user.getUserId())){
        for(Group groupp:this.groups){
            if(groupp.getGroupName().equals(group.getGroupName()) && groupp.getGroupDescription().equals(group.getGroupDescription()) && groupp.getGroupPhoto().equals(group.getGroupPhoto())){
                groupp.getGroupMemberIds().add(user.getUserId());
                userr.getUserJoinedGroups().add(groupp);
                UserDatabase.getInstance().saveUsersToFile(USERFILE);
          }
        }
        }
       }
    }
    
     public boolean promoteOtherAdmin(String primaryAdminId,String otherAdminId,Group group){
      for(User user:this.users){
      for(Group groupp:user.getUserJoinedGroups()){
        if(user.getUserId().equals(primaryAdminId)){
       if(groupp.getGroupName().equals(group.getGroupName()) && groupp.getGroupDescription().equals(group.getGroupDescription()) && groupp.getGroupPhoto().equals(group.getGroupPhoto())){ 
       if(groupp.getGroupPrimaryAdminId().equals(primaryAdminId)){
        if(groupp.getGroupOtherAdminsIds().contains(otherAdminId)){
            groupp.setGroupPrimaryAdminId(otherAdminId);
            groupp.getGroupOtherAdminsIds().remove(otherAdminId);
            UserDatabase.getInstance().saveUsersToFile(USERFILE);
            return true;
        }
       }
       }
       }
       }
       }
       return false;
    }
    
    public boolean demoteOtherAdmin(String primaryAdminId,String otherAdminId,Group group){
       for(User user:this.users){
        for(Group groupp:user.getUserJoinedGroups()){
          if(user.getUserId().equals(primaryAdminId)){
       if(groupp.getGroupName().equals(group.getGroupName()) && groupp.getGroupDescription().equals(group.getGroupDescription()) && groupp.getGroupPhoto().equals(group.getGroupPhoto())){ 
       if(groupp.getGroupPrimaryAdminId().equals(primaryAdminId)){
        if(groupp.getGroupOtherAdminsIds().contains(otherAdminId)){
            groupp.getGroupOtherAdminsIds().remove(otherAdminId);
            groupp.getGroupMemberIds().add(otherAdminId);
            UserDatabase.getInstance().saveUsersToFile(USERFILE);
            return true;
        }
       }
       }
       }
       }
       }
       return false;
    }
    
    public boolean removeAnyGroupMember(String primaryAdminId,String groupMemberId,Group group){
       for(User user:this.users){
       for(Group groupp:user.getUserJoinedGroups()){
          if(user.getUserId().equals(primaryAdminId)){
       if(groupp.getGroupName().equals(group.getGroupName()) && groupp.getGroupDescription().equals(group.getGroupDescription()) && groupp.getGroupPhoto().equals(group.getGroupPhoto())){
        if(groupp.getGroupPrimaryAdminId().equals(primaryAdminId)){
            if(groupp.getGroupMemberIds().contains(groupMemberId)){
                groupp.getGroupMemberIds().remove(groupMemberId);
                UserDatabase.getInstance().saveUsersToFile(USERFILE);
                return true;
            }
            if(groupp.getGroupOtherAdminsIds().contains(groupMemberId)){
                groupp.getGroupOtherAdminsIds().remove(groupMemberId);
                UserDatabase.getInstance().saveUsersToFile(USERFILE);
                return true;
            }
        }
        }
        }
        }
        }
        return false;
    }
    
   /* public boolean addGroupPost(String primaryAdminId){
        if(this.groupPrimaryAdminId.equals(primaryAdminId)){
            Post post = new Post(UprimaryAdminId);
        }
        return false;
    } */
    
    public boolean deleteGroup(String primaryAdminId,Group group){
        for(User user:this.users){
        for(Group groupp:user.getUserJoinedGroups()){
            if(user.getUserId().equals(primaryAdminId)){
            if(groupp.getGroupName().equals(group.getGroupName()) && groupp.getGroupDescription().equals(group.getGroupDescription()) && groupp.getGroupPhoto().equals(group.getGroupPhoto())){
                if(groupp.getGroupPrimaryAdminId().equals(primaryAdminId)){
                this.groups.remove(group);
                UserDatabase.getInstance().saveUsersToFile(USERFILE);
                return true;}
            }
            }
        }
        }
        return false;
    }
    
    public boolean removeNormalMember(String otherAdminId,String groupMemberId,Group group){
       for(User user:this.users){ 
        for(Group groupp:user.getUserJoinedGroups()){
            if(user.getUserId().equals(otherAdminId)){
           if(groupp.getGroupName().equals(group.getGroupName()) && groupp.getGroupDescription().equals(group.getGroupDescription()) && groupp.getGroupPhoto().equals(group.getGroupPhoto())){
               if(groupp.getGroupOtherAdminsIds().contains(otherAdminId)){
                   if(groupp.getGroupMemberIds().contains(groupMemberId)){
                       groupp.getGroupMemberIds().remove(groupMemberId);
                       UserDatabase.getInstance().saveUsersToFile(USERFILE);
                       return true;
                   }
               }
           } 
           }
        }
        }
        return false;
    }
    
    public boolean leaveGroup(String groupMemberId,Group group){
        for(User user:this.users){
        for(Group groupp:user.getUserJoinedGroups()){
            if(user.getUserId().equals(groupMemberId)){
            if(groupp.getGroupName().equals(group.getGroupName()) && groupp.getGroupDescription().equals(group.getGroupDescription()) && groupp.getGroupPhoto().equals(group.getGroupPhoto())){
                if(groupp.getGroupMemberIds().contains(groupMemberId)){
                    groupp.getGroupMemberIds().remove(groupMemberId);
                    UserDatabase.getInstance().saveUsersToFile(USERFILE);
                    return true;
                }
            }
            }
        }
        }
        return false;
    }
    
    
    
}

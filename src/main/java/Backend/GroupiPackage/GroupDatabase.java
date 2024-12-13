/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.GroupiPackage;

import Backend.UserPackage.User;
import Backend.UserPackage.UserDatabase;
import static Files.FILEPATHS.USERFILE;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author Abdelrahman
 */
public class GroupDatabase {
    
    private static GroupDatabase groupDatabase;
    private int uniqueCounter;
    private ArrayList <Groupi> groups;
    private ArrayList <User> users;
    
    private GroupDatabase() {
        this.groups = new ArrayList<>();
        this.users = new ArrayList<>();
        this.users = UserDatabase.getInstance().getUsers();
        uniqueCounter = 1;
    }
    
    public static synchronized GroupDatabase getInstance() {
        if (groupDatabase == null) {
            groupDatabase = new GroupDatabase();
        }
        return groupDatabase;
    }
    
     public int getUniqueCounter() {
        return uniqueCounter;
    }
    
    public ArrayList <Groupi> getGroups(){
        return this.groups;
    }
    
    public ArrayList <Groupi> getGroupsByName(String groupName){
        ArrayList <Groupi> groups = new ArrayList<>();
        for(Groupi group:this.groups){
            System.out.println(group.getGroupName() + " " + group.getGroupDescription());
            if(group.getGroupName().equals(groupName)) groups.add(group);
        }
        return groups;
    }
    
    public Groupi getGroupById(String groupId){
        for(Groupi group:this.groups){
            if(group.getGroupId().equals(groupId)) return group;
        }
        return null;
    }
    
   /* public Group getGroupByAttributes(String groupName,String groupDescription,String groupPhoto){
        for(Group group:this.groups){
            if(group.getGroupName().equals(groupName) && group.getGroupDescription().equals(groupDescription) && group.getGroupPhoto().equals(group.getGroupPhoto())){
                return group;
            }
        }
        return null;
    } */
    
    public void setCounter(int counter){
        this.uniqueCounter = counter;
    }
    
   public boolean checkIfGroupExists(Groupi group){
        for(Groupi groupp:this.groups){
            if(groupp.getGroupId().equals(group.getGroupId())){
                return true;
            }
        }
        return false;
    } 
    
    public void addGroup(Groupi group){
        if(group!=null){
            this.groups.add(group);
            
          String[] parts = group.getGroupId().split("-");
        if (parts.length == 2) {
            try {
                int idCounter = Integer.parseInt(parts[1]);
                uniqueCounter = Math.max(uniqueCounter, idCounter + 1);
            } catch (NumberFormatException e) {
                System.err.println("Invalid UserId format for counter adjustment.");
            }
        }
   
        }
    }
    
    public String checkIfInGroup(String userId,Groupi group){
        if(group.getGroupPrimaryAdminId().equals(userId)) return "PRIMARY";
        else if(group.getGroupOtherAdminsIds().contains(userId)) return "OTHER";
        else if(group.getGroupMemberIds().contains(userId)) return "MEMBER";
        return "NONE";
    }
    
    
    
    public boolean createGroup(User user,String groupName,String groupDescription,String groupPhoto){
       if(groupName.length() == 0 || groupDescription.length() == 0 || groupPhoto == null || groupPhoto.length() == 0) return false;
      for(User userr:this.users){
       if(userr.getUserId().equals(user.getUserId())){
        String groupId = groupName + "-" + uniqueCounter;
      Groupi newGroup = Groupi.GroupCreate.groupCreate(groupId,user.getUserId(),groupName, groupDescription, groupPhoto);
      this.groups.add(newGroup);
      userr.getUserJoinedGroups().add(newGroup);
      UserDatabase.getInstance().saveUsersToFile(USERFILE);
        }
      }
      return true;
    }
    
    public void sendGroupRequest(String requestId,Groupi group){
                for(Groupi groupp:this.groups){
                    if(groupp.getGroupName().equals(group.getGroupName()) && groupp.getGroupDescription().equals(group.getGroupDescription()) && groupp.getGroupPhoto().equals(group.getGroupPhoto())){
                        groupp.getGroupRequestsIds().add(requestId);
                        UserDatabase.getInstance().saveUsersToFile(USERFILE);
                    }
                }
    }
    
    public boolean acceptGroupRequest(String otherAdminId,String requestId,Groupi group){
                for(Groupi groupp:this.groups){
                    if(group.getGroupId().equals(group.getGroupId())){
                        if(groupp.getGroupOtherAdminsIds().contains(otherAdminId)){
                            groupp.getGroupRequestsIds().remove(requestId);
                            groupp.getGroupMemberIds().add(requestId);
                            UserDatabase.getInstance().saveUsersToFile(USERFILE);
                            return true;
                        }
                        if(groupp.getGroupPrimaryAdminId().equals(otherAdminId)){
                            groupp.getGroupRequestsIds().remove(requestId);
                            groupp.getGroupMemberIds().add(requestId);
                            UserDatabase.getInstance().saveUsersToFile(USERFILE);
                            return true;
                        }
                    }
                }
        return false;
    }
    
    
     public boolean declineGroupRequest(String otherAdminId,String requestId,Groupi group){
                for(Groupi groupp:this.groups){
                    if(groupp.getGroupId().equals(group.getGroupId())){
                        if(groupp.getGroupOtherAdminsIds().contains(otherAdminId)){
                            groupp.getGroupRequestsIds().remove(requestId);
                            UserDatabase.getInstance().saveUsersToFile(USERFILE);
                            return true;
                        }
                        if(groupp.getGroupPrimaryAdminId().equals(otherAdminId)){
                            groupp.getGroupRequestsIds().remove(requestId);
                            UserDatabase.getInstance().saveUsersToFile(USERFILE);
                            return true;
                        }
                    }
                }
        return false;
    }
    
    
  /*  public void joinGroup(User user,Group group){
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
    } */
    
   public boolean promoteOtherAdmin(String primaryAdminId,String otherAdminId,Groupi group){
      for(Groupi groupp:this.groups){
       if(groupp.getGroupId().equals(group.getGroupId())){ 
       if(groupp.getGroupPrimaryAdminId().equals(primaryAdminId)){
        if(groupp.getGroupOtherAdminsIds().contains(otherAdminId)){
            groupp.setGroupPrimaryAdminId(otherAdminId);
            groupp.getGroupOtherAdminsIds().remove(otherAdminId);
            groupp.getGroupOtherAdminsIds().add(primaryAdminId);
            UserDatabase.getInstance().saveUsersToFile(USERFILE);
            return true;
        }
        if(groupp.getGroupMemberIds().contains(otherAdminId)){
            groupp.getGroupOtherAdminsIds().add(otherAdminId);
            groupp.getGroupMemberIds().remove(otherAdminId);
            UserDatabase.getInstance().saveUsersToFile(USERFILE);
            return true;
        }
       }
       }
       }
       
       return false;
    }
    
    public boolean demoteOtherAdmin(String primaryAdminId,String otherAdminId,Groupi group){
       
        for(Groupi groupp:this.groups){
       if(groupp.getGroupId().equals(group.getGroupId())){ 
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
       
       return false;
    }
    
    public boolean removeAnyGroupMember(String primaryAdminId,String groupMemberId,Groupi group){
       for(Groupi groupp:this.groups){
       if(groupp.getGroupId().equals(group.getGroupId())){
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
        
        return false;
    }
    
   /* public boolean addGroupPost(String primaryAdminId){
        if(this.groupPrimaryAdminId.equals(primaryAdminId)){
            Post post = new Post(UprimaryAdminId);
        }
        return false;
    } */
    
    public boolean deleteGroup(String primaryAdminId,Groupi group){
        for(Groupi groupp:this.groups){
            if(groupp.getGroupId().equals(group.getGroupId())){
                if(groupp.getGroupPrimaryAdminId().equals(primaryAdminId)){
                this.groups.remove(group);
                UserDatabase.getInstance().saveUsersToFile(USERFILE);
                return true;}
            } 
        }
        
        return false;
    }
    
    public boolean removeNormalMember(String otherAdminId,String groupMemberId,Groupi group){
        for(Groupi groupp:this.groups){
           if(groupp.getGroupId().equals(group.getGroupId())){
               if(groupp.getGroupOtherAdminsIds().contains(otherAdminId)){
                   if(groupp.getGroupMemberIds().contains(groupMemberId)){
                       groupp.getGroupMemberIds().remove(groupMemberId);
                       UserDatabase.getInstance().saveUsersToFile(USERFILE);
                       return true;
                   }
               }
           } 
        }
        
        return false;
    }
    
    public boolean leaveGroup(String groupMemberId,Groupi group){
        for(Groupi groupp:this.groups){
            if(groupp.getGroupId().equals(group.getGroupId())){
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
                if(groupp.getGroupPrimaryAdminId().equals(groupMemberId)){
                    this.groups.remove(groupp);
                    UserDatabase.getInstance().saveUsersToFile(USERFILE);
                    return true;
                }
            }
        }
        
        return false;
    }
    
    
    
}

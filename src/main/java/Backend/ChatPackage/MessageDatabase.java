/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.ChatPackage;


import Backend.GroupPackage.Group;
import Backend.UserPackage.User;
import Backend.UserPackage.UserDatabase;
import static Files.FILEPATHS.USERFILE;
import java.util.ArrayList;

/**
 *
 * @author Abdelrahman
 */
public class MessageDatabase {
    
    
    private static MessageDatabase messageDatabase;
    private int uniqueCounter;
    private ArrayList <Message> messages;
    
    
    private MessageDatabase() {
        this.messages = new ArrayList<>();
        uniqueCounter = 1;
    }
    
    public static synchronized MessageDatabase getInstance() {
        if (messageDatabase == null) {
            messageDatabase = new MessageDatabase();
        }
        return messageDatabase;
    }
    
     public int getUniqueCounter() {
        return uniqueCounter;
    }
    
    public ArrayList <Message> getMessages(){
        return this.messages;
    }
    
    public void setCounter(int counter){
        this.uniqueCounter = counter;
    }
    
    public boolean checkIfExists(Message message){
        for(Message messagee:this.messages){
            if(messagee.getMessageId().equals(message.getMessageId())) return true;
        }
        return false;
    }
    
    
     public void addMessage(Message message){
        if(message!=null){
            this.messages.add(message);
            
         String[] parts = message.getMessageId().split("-");
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
    
    public boolean sendMessage(User messageSender,User friend,String messageContent){
       if(messageContent == null || messageContent.length() == 0) return false;
        for(User user:UserDatabase.getInstance().getUsers()){
            if(user.getUserId().equals(messageSender.getUserId())){
                if(user.getUserFriends().contains(friend.getUserId())){
                    String id = "Message" + "-" + uniqueCounter;
                    Message newMessage = Message.MessageBuilder.messageCreate(id,messageSender.getUserId(),friend.getUserId(),messageContent);
                    addMessage(newMessage);
                    user.getUserReceivedMessages().add(newMessage);
                    user.getUserSentMessages().add(newMessage);
                    UserDatabase.getInstance().saveUsersToFile(USERFILE);
                    return true;
                } 
            }  
        }
        return false;
    }
    
    
    
}

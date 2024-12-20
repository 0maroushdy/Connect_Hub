
package Frontend.NotificationPackage;

import Backend.ChatPackage.MessageDatabase;
import Backend.GroupPackage.Group;
import Backend.GroupPackage.GroupDatabase;
import Backend.GroupPackage.GroupSearch;
import Backend.NotificationPackage.NotificationManager;
import Backend.NotificationPackage.UserNotification;
import Frontend.UserPackage.*;
import Backend.UserPackage.FriendRequest;
import Backend.UserPackage.FriendshipManagement;
import Backend.UserPackage.User;
import Backend.UserPackage.UserDatabase;
import Backend.UserPackage.UserSignupSingleton;
import static Files.FILEPATHS.USERFILE;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Omar
 */
public class NotiForm extends javax.swing.JFrame {
    
    private User currentUser;
    
     
    public NotiForm() {
        initComponents();
        this.currentUser = UserSignupSingleton.getInstance().getUser();
        
        initCustomComponents();
    }
    
    private void initCustomComponents(){
        setTitle("Notifications Page");
        setVisible(true);
        setLocationRelativeTo(null);
        pack();
        fillOutNotifications();
        
    }
    
    private void fillOutNotifications(){
                // --------- ListModel Creations ---------
        DefaultListModel<String> NewNotificatoinsModel = new DefaultListModel<>();
        DefaultListModel<String> OldNotificatoinsModel = new DefaultListModel<>();
        DefaultListModel<String> FriendsNotifications = new DefaultListModel<>();
        DefaultListModel<String> GroupsNoti = new DefaultListModel<>();
        DefaultListModel<String> chatsModel = new DefaultListModel<>();
        DefaultListModel<String> commentsModel = new DefaultListModel<>();
        
                /* setting up jlists */
        lstNewNotifications.setModel(NewNotificatoinsModel);
        lstOldNotifications.setModel(OldNotificatoinsModel);
        lstFriendsNotificatoin.setModel(FriendsNotifications);
        lstGroups.setModel(GroupsNoti);
        lstChats.setModel(chatsModel);
        lstComments.setModel(commentsModel);
        
                       /* filling out data */
        for(UserNotification noti: currentUser.getNotificationManager().getNotiList()){
            if(noti.getSeenStatus() == false){
            NewNotificatoinsModel.addElement(noti.getMessage());
           
            } if(noti.getSeenStatus() == true){
            OldNotificatoinsModel.addElement(noti.getMessage());
            }
           
            if(noti.getType().equals("friendNotification")){
              FriendsNotifications.addElement(noti.getMessage()); 
           }
          
           if(noti.getType().equals("groupNotification")){
               GroupsNoti.addElement(noti.getMessage());
           }
           
           if(noti.getType().equals("messageNotification")){
               chatsModel.addElement(noti.getMessage());
           }
           
           if(noti.getType().equals("commentNotification") || noti.getType().equals("likeNotification")){
               commentsModel.addElement(noti.getMessage());
           }

        }
        
        btnAcceptFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String line = lstFriendsNotificatoin.getSelectedValue();
               int ind = lstFriendsNotificatoin.getSelectedIndex();
               String[] data = line.split(" ");         
               FriendshipManagement friendship = FriendshipManagement.FriendshipManagementFactory.create();
               for(User user:UserDatabase.getInstance().getUsers()){
                   if(user.getUserId().equals(data[0])){
                   for (FriendRequest request : user.getUserSentFriendRequests()) {
                        if (request.getRequestReceiverId().equals(currentUser.getUserId())) {
                            friendship.acceptFriendRequest(currentUser, request);
                            FriendsNotifications.removeElementAt(ind);
                        }
                       }
                   }
               }
               UserDatabase.getInstance().saveUsersToFile(USERFILE);
                 
            }
        } );
        
        
        btnDeclineFriend.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String line = lstFriendsNotificatoin.getSelectedValue();
                int ind = lstFriendsNotificatoin.getSelectedIndex();
                String[] data = line.split(" ");
                
                FriendshipManagement friendship = FriendshipManagement.FriendshipManagementFactory.create();
                User requestSender = UserDatabase.getInstance().getUser(data[0]);
                for (FriendRequest request : requestSender.getUserSentFriendRequests()) {
                if (request.getRequestReceiverId().equals(currentUser.getUserId())) {
                friendship.declineFriendRequest(currentUser, request);
                FriendsNotifications.removeElementAt(ind);
              }
           }
                UserDatabase.getInstance().saveUsersToFile(USERFILE);
          } 
        });
        
        

                joinGroup.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String line = lstGroups.getSelectedValue();
                        String [] data = line.split(" ");
                        
                        Group group = GroupDatabase.getInstance().getGroupById(data[0]);
                        GroupSearch groupSearch = GroupSearch.GroupSearchFactory.createGroupSearch(data[1]);
                        
                        if(groupSearch.joinGroup(currentUser, group)){
                        JOptionPane.showMessageDialog(null, "Group request sent to " + group.getGroupId(), "Success", JOptionPane.INFORMATION_MESSAGE);}
                else{
                      JOptionPane.showMessageDialog(null, "You are either a member in this group or already sent group request before", "Fail", JOptionPane.INFORMATION_MESSAGE);      
                        }
                    } 
                });
                
                leaveGroup.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String line = lstGroups.getSelectedValue();
                        String [] data = line.split(" ");
                        
                        Group group = GroupDatabase.getInstance().getGroupById(data[0]);
                        GroupSearch groupSearch = GroupSearch.GroupSearchFactory.createGroupSearch(data[1]);
                       
                        if (groupSearch.leaveGroup(currentUser.getUserId(), group)){
                        JOptionPane.showMessageDialog(null, "Left the group successfully", "Success", JOptionPane.INFORMATION_MESSAGE);}
                       else {
                           JOptionPane.showMessageDialog(null, "You are not a member in this group", "Fail", JOptionPane.INFORMATION_MESSAGE);
                       }
                    } 
                }); 
                
                
                 reply.addActionListener(new ActionListener(){
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      String line = lstChats.getSelectedValue();
                      String [] data = line.split(" ");
                      User friend = UserDatabase.getInstance().getUser(data[0]);
                      createMessage message = new createMessage(currentUser,friend);
                      message.setVisible(true);
                   }   
                });
        
        
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstGroups = new javax.swing.JList<>();
        btnBack2 = new javax.swing.JButton();
        joinGroup = new javax.swing.JButton();
        leaveGroup = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstOldNotifications = new javax.swing.JList<>();
        add = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstNewNotifications = new javax.swing.JList<>();
        remove = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstFriendsNotificatoin = new javax.swing.JList<>();
        btnAcceptFriend = new javax.swing.JButton();
        btnDeclineFriend = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lstChats = new javax.swing.JList<>();
        reply = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        lstComments = new javax.swing.JList<>();
        commentBack = new javax.swing.JButton();

        btnBack1.setBackground(new java.awt.Color(153, 204, 255));
        btnBack1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack1.setForeground(new java.awt.Color(0, 0, 0));
        btnBack1.setText("Back");
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jScrollPane2.setViewportView(lstGroups);

        btnBack2.setBackground(new java.awt.Color(153, 204, 255));
        btnBack2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack2.setForeground(new java.awt.Color(0, 0, 0));
        btnBack2.setText("Back");
        btnBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack2ActionPerformed(evt);
            }
        });

        joinGroup.setBackground(new java.awt.Color(153, 204, 255));
        joinGroup.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        joinGroup.setForeground(new java.awt.Color(0, 0, 0));
        joinGroup.setText("Join");
        joinGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinGroupActionPerformed(evt);
            }
        });

        leaveGroup.setBackground(new java.awt.Color(153, 0, 0));
        leaveGroup.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        leaveGroup.setForeground(new java.awt.Color(0, 0, 0));
        leaveGroup.setText("Leave");
        leaveGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveGroupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(joinGroup)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(leaveGroup)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack2)
                    .addComponent(joinGroup)
                    .addComponent(leaveGroup))
                .addGap(12, 12, 12))
        );

        jTabbedPane1.addTab("Groups", jPanel2);

        jScrollPane3.setViewportView(lstOldNotifications);

        add.setBackground(new java.awt.Color(153, 204, 255));
        add.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add.setForeground(new java.awt.Color(0, 0, 0));
        add.setText("Remove All Notifications");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(add)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("Old Notifications", jPanel3);

        jScrollPane4.setViewportView(lstNewNotifications);

        remove.setBackground(new java.awt.Color(153, 204, 255));
        remove.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        remove.setForeground(new java.awt.Color(0, 0, 0));
        remove.setText("Mark All As Seen");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(remove, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(remove, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jTabbedPane1.addTab("New Notifications", jPanel4);

        jScrollPane1.setViewportView(lstFriendsNotificatoin);

        btnAcceptFriend.setBackground(new java.awt.Color(0, 204, 102));
        btnAcceptFriend.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAcceptFriend.setForeground(new java.awt.Color(0, 0, 0));
        btnAcceptFriend.setText("Accept");
        btnAcceptFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptFriendActionPerformed(evt);
            }
        });

        btnDeclineFriend.setBackground(new java.awt.Color(153, 0, 0));
        btnDeclineFriend.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDeclineFriend.setForeground(new java.awt.Color(255, 255, 255));
        btnDeclineFriend.setText("Decline");
        btnDeclineFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeclineFriendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAcceptFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDeclineFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeclineFriend)
                    .addComponent(btnAcceptFriend))
                .addGap(30, 30, 30))
        );

        jTabbedPane1.addTab("Friends Noti.", jPanel1);

        jScrollPane5.setViewportView(lstChats);

        reply.setBackground(new java.awt.Color(153, 204, 255));
        reply.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        reply.setForeground(new java.awt.Color(0, 0, 0));
        reply.setText("Reply");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(reply, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(394, Short.MAX_VALUE)
                .addComponent(reply, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(85, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Chats Noti.", jPanel5);

        jScrollPane6.setViewportView(lstComments);

        commentBack.setBackground(new java.awt.Color(153, 204, 255));
        commentBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        commentBack.setForeground(new java.awt.Color(0, 0, 0));
        commentBack.setText("Reply");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(commentBack, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(210, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(409, Short.MAX_VALUE)
                .addComponent(commentBack, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(71, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Comments/Like Noti.", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBack1ActionPerformed

    private void btnDeclineFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeclineFriendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeclineFriendActionPerformed

    private void btnAcceptFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptFriendActionPerformed
        // TODO add your handling code here:
        //        FriendshipManagement.FriendshipManagementFactory.create().acceptFriendRequest(currentUser, request);
    }//GEN-LAST:event_btnAcceptFriendActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        // TODO add your handling code here:

        UserSignupSingleton.getInstance().getUser().getNotificationManager().makeAllSeen();
        fillOutNotifications();
    }//GEN-LAST:event_removeActionPerformed

    private void leaveGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveGroupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_leaveGroupActionPerformed

    private void joinGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinGroupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_joinGroupActionPerformed

    private void btnBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        this.dispose();
        try {
            new News();
        } catch (IOException ex) {
            Logger.getLogger(NotiForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBack2ActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton btnAcceptFriend;
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnBack2;
    private javax.swing.JButton btnDeclineFriend;
    private javax.swing.JButton commentBack;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton joinGroup;
    private javax.swing.JButton leaveGroup;
    private javax.swing.JList<String> lstChats;
    private javax.swing.JList<String> lstComments;
    private javax.swing.JList<String> lstFriendsNotificatoin;
    private javax.swing.JList<String> lstGroups;
    private javax.swing.JList<String> lstNewNotifications;
    private javax.swing.JList<String> lstOldNotifications;
    private javax.swing.JButton remove;
    private javax.swing.JButton reply;
    // End of variables declaration//GEN-END:variables
}

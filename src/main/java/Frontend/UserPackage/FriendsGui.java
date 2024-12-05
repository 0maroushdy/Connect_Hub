/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend.UserPackage;

import Backend.UserPackage.FriendRequest;
import Backend.UserPackage.FriendshipManagement;
import Backend.UserPackage.User;
import Backend.UserPackage.UserDatabase;
import Backend.UserPackage.UserSignupSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;

/**
 *
 * @author Abdelrahman
 */
public class FriendsGui extends javax.swing.JFrame {
     
    private User currentUser;
    private DefaultListModel<String> friendRequestsModel;
    private DefaultListModel <String> friendListModel;
    private DefaultListModel <String> friendSuggestionsModel;
    private DefaultListModel <String> changeFriendStatusModel;
    /**
     * Creates new form FriendsGui
     */
    public FriendsGui() {
        initComponents();
        currentUser = UserSignupSingleton.getInstance().getUser();
        this.friendRequestsModel = new DefaultListModel<>();
        this.friendListModel = new DefaultListModel<>();
        this.friendSuggestionsModel = new DefaultListModel<>();
        this.changeFriendStatusModel = new DefaultListModel<>();
        initCustomComponents();
    }
    
    private void initCustomComponents(){
        setTitle("Friend Management");
        setLocationRelativeTo(null);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               /*  setting models for lists */
        friendRequests.setModel(friendRequestsModel);
        friendList.setModel(friendListModel);
        friendSuggestions.setModel(friendSuggestionsModel);
        changeFriendStatus.setModel(changeFriendStatusModel);
                  /*  filling out the lists */
        for(FriendRequest request: currentUser.getUserReceivedFriendRequests()){
            friendRequestsModel.addElement(request.getRequestSender().getUserId() + " " + request.getRequestSender().getUsername());
        }
        for(User friend:currentUser.getUserFriends()){
            friendListModel.addElement(friend.getUserId());
            changeFriendStatusModel.addElement(friend.getUserId() + " " + friend.getUsername());
        }
        for(User friend:FriendshipManagement.FriendshipManagementFactory.create().suggestFriends(currentUser)){
            friendSuggestionsModel.addElement(friend.getUserId() + " " + friend.getUsername());
        }
              /* Button action Listeners */
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String line = friendRequests.getSelectedValue();
              int ind = friendRequests.getSelectedIndex();
              String [] data = line.split(" ");
              User requestSender = UserDatabase.getInstance().getUser(data[0]);
              for(FriendRequest request:requestSender.getUserSentFriendRequests()){
                  if(request.getRequestReciever().getUserId().equals(currentUser.getUserId())){
                      currentUser.acceptFriendRequest(request);
                      friendRequestsModel.removeElementAt(ind);
                  }
              }
            }
        });
        
        decline.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              String line = friendRequests.getSelectedValue();
              int ind = friendRequests.getSelectedIndex();
              String [] data = line.split(" ");
              User requestSender = UserDatabase.getInstance().getUser(data[0]);
              for(FriendRequest request:requestSender.getUserSentFriendRequests()){
                  if(request.getRequestReciever().getUserId().equals(currentUser.getUserId())){
                      currentUser.declineFriendRequest(request);
                      friendRequestsModel.removeElementAt(ind);
                  }
              }
            }
            
        });
        
        addFriend.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              String line = friendSuggestions.getSelectedValue();
              int ind = friendSuggestions.getSelectedIndex();
              String [] data = line.split(" ");
              User suggestion = UserDatabase.getInstance().getUser(data[0]);
              FriendshipManagement.FriendshipManagementFactory.create().sendFriendRequest(currentUser, suggestion);
              friendSuggestionsModel.removeElementAt(ind);
            }
        });
        
        block.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              String line = changeFriendStatus.getSelectedValue();
              int ind = changeFriendStatus.getSelectedIndex();
              String [] data = line.split(" ");
              User blocked = UserDatabase.getInstance().getUser(data[0]);
              FriendshipManagement.FriendshipManagementFactory.create().blockUser(currentUser, blocked);
              changeFriendStatusModel.removeElementAt(ind);
            }
        });
        
        remove.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              String line = changeFriendStatus.getSelectedValue();
              int ind = changeFriendStatus.getSelectedIndex();
              String [] data = line.split(" ");
              User removed = UserDatabase.getInstance().getUser(data[0]);
              FriendshipManagement.FriendshipManagementFactory.create().removeFriend(currentUser,removed);
              changeFriendStatusModel.removeElementAt(ind);
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

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        friendRequests = new javax.swing.JList<>();
        accept = new javax.swing.JButton();
        decline = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        friendList = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        friendSuggestions = new javax.swing.JList<>();
        addFriend = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        changeFriendStatus = new javax.swing.JList<>();
        block = new javax.swing.JButton();
        remove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(friendRequests);

        jScrollPane2.setViewportView(jScrollPane1);

        accept.setBackground(new java.awt.Color(153, 204, 255));
        accept.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        accept.setForeground(new java.awt.Color(0, 0, 0));
        accept.setText("Accept");
        accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptActionPerformed(evt);
            }
        });

        decline.setBackground(new java.awt.Color(153, 204, 255));
        decline.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        decline.setForeground(new java.awt.Color(0, 0, 0));
        decline.setText("Decline");
        decline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(accept, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(decline, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accept)
                    .addComponent(decline))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Friend Requests", jPanel2);

        jScrollPane3.setViewportView(friendList);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Friend List", jPanel3);

        jScrollPane4.setViewportView(friendSuggestions);

        addFriend.setBackground(new java.awt.Color(153, 204, 255));
        addFriend.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addFriend.setForeground(new java.awt.Color(0, 0, 0));
        addFriend.setText("Add Friend");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(addFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Friend Suggestions", jPanel1);

        jScrollPane5.setViewportView(changeFriendStatus);

        block.setBackground(new java.awt.Color(153, 204, 255));
        block.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        block.setForeground(new java.awt.Color(0, 0, 0));
        block.setText("Block");

        remove.setBackground(new java.awt.Color(153, 204, 255));
        remove.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        remove.setForeground(new java.awt.Color(0, 0, 0));
        remove.setText("Remove");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(block, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(remove, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(block, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(remove, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 30, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Block/Remove Friends", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acceptActionPerformed

    private void declineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_declineActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accept;
    private javax.swing.JButton addFriend;
    private javax.swing.JButton block;
    private javax.swing.JList<String> changeFriendStatus;
    private javax.swing.JButton decline;
    private javax.swing.JList<String> friendList;
    private javax.swing.JList<String> friendRequests;
    private javax.swing.JList<String> friendSuggestions;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JButton remove;
    // End of variables declaration//GEN-END:variables
}

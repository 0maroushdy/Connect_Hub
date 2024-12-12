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
import static Files.FILEPATHS.USERFILE;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Abdelrahman
 */
public class FriendsGui extends javax.swing.JFrame {
    
    private User currentUser;
    private FriendshipManagement friendship;
    private ArrayList <User> users;
    /**
     * Creates new form FriendsGui
     */
    public FriendsGui() {
        initComponents();
        this.currentUser = UserSignupSingleton.getInstance().getUser();
        this.friendship = FriendshipManagement.FriendshipManagementFactory.create();
        this.users = new ArrayList <>();
        this.users = UserDatabase.getInstance().getUsers();
        initCustomComponents();
    }
    
    private void initCustomComponents(){
        setTitle("Friend Management");
        setLocationRelativeTo(null);
        pack();
                /* ListModel Creations */
        DefaultListModel<String> friendRequestsModel = new DefaultListModel<>();
        DefaultListModel<String> friendListModel = new DefaultListModel<>();
        DefaultListModel<String> changeFriendStatusModel = new DefaultListModel<>();
        DefaultListModel<String> friendSuggestionsModel = new DefaultListModel<>();
        
                /* setting up jlists */
        changeFriendStatus.setModel(changeFriendStatusModel);
        friendList.setModel(friendListModel);
        friendRequests.setModel(friendRequestsModel);
        friendSuggestions.setModel(friendSuggestionsModel);
        
                       /* filling out data */
        for(FriendRequest friendRequest: currentUser.getUserReceivedFriendRequests()){
            friendRequestsModel.addElement(friendRequest.getRequestSenderId()+ " " + UserDatabase.getInstance().getUser(friendRequest.getRequestSenderId()).getUsername() + " " + friendRequest.getRequestStatus());
        }
        
        for(User friend: UserDatabase.getInstance().getUsers()){
            if(currentUser.getUserFriends().contains(friend.getUserId())){
            friendListModel.addElement(friend.getUserId() + " " + friend.getUsername() + " " + friend.getUserStatus());
            changeFriendStatusModel.addElement(friend.getUserId() + " " + friend.getUsername() + " " + friend.getUserStatus());}
        }
        
        for(User friend: this.friendship.suggestFriends(currentUser)){
           // System.out.println(friend.getUserId());
            friendSuggestionsModel.addElement(friend.getUserId() + " " + friend.getUsername());
        }
        
                /* Button listeners */    
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String line = friendRequests.getSelectedValue();
               int ind = friendRequests.getSelectedIndex();
               String[] data = line.split(" ");
               for(User user:users){
                if(user.getUserId().equals(data[0])){
               for (FriendRequest request : user.getUserSentFriendRequests()) {
                if (request.getRequestReceiverId().equals(currentUser.getUserId())) {
                friendship.acceptFriendRequest(currentUser, request);
                friendRequestsModel.removeElementAt(ind);
                }
               }
               }
               }
               UserDatabase.getInstance().saveUsersToFile(USERFILE);
            }
        });
        
        decline.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String line = friendRequests.getSelectedValue();
                int ind = friendRequests.getSelectedIndex();
                String[] data = line.split(" ");
                User requestSender = UserDatabase.getInstance().getUser(data[0]);
                for (FriendRequest request : requestSender.getUserSentFriendRequests()) {
                if (request.getRequestReceiverId().equals(currentUser.getUserId())) {
                friendship.declineFriendRequest(currentUser, request);
                friendRequestsModel.removeElementAt(ind);
              }
           }
                UserDatabase.getInstance().saveUsersToFile(USERFILE);
          } 
        });
        
        block.addActionListener(new ActionListener(){
            @Override
        public void actionPerformed(ActionEvent e) {
        String line = changeFriendStatus.getSelectedValue();
        int ind = changeFriendStatus.getSelectedIndex();
        String[] data = line.split(" ");
        User blocked = UserDatabase.getInstance().getUser(data[0]);
        friendship.blockUser(currentUser, blocked);
        changeFriendStatusModel.removeElementAt(ind);
        UserDatabase.getInstance().saveUsersToFile(USERFILE);
          } 
        });
        
        remove.addActionListener(new ActionListener(){
            @Override
        public void actionPerformed(ActionEvent e) {
        String line = changeFriendStatus.getSelectedValue();
        int ind = changeFriendStatus.getSelectedIndex();
        String[] data = line.split(" ");
        User removed = UserDatabase.getInstance().getUser(data[0]);
        friendship.removeFriend(currentUser, removed);
        changeFriendStatusModel.removeElementAt(ind);
        UserDatabase.getInstance().saveUsersToFile(USERFILE);
            }
        });
        
        add.addActionListener(new ActionListener(){
            @Override
        public void actionPerformed(ActionEvent e) {
        String line = friendSuggestions.getSelectedValue();
        int ind = friendSuggestions.getSelectedIndex();
        String[] data = line.split(" ");
        User suggestion = UserDatabase.getInstance().getUser(data[0]);
        friendship.sendFriendRequest(currentUser,suggestion);
      //  System.out.println(currentUser.getUserSentFriendRequests().size());
        friendSuggestionsModel.removeElementAt(ind);
        UserDatabase.getInstance().saveUsersToFile(USERFILE);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        friendRequests = new javax.swing.JList<>();
        accept = new javax.swing.JButton();
        decline = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        friendList = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        friendSuggestions = new javax.swing.JList<>();
        add = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        changeFriendStatus = new javax.swing.JList<>();
        block = new javax.swing.JButton();
        remove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(friendRequests);

        accept.setBackground(new java.awt.Color(153, 204, 255));
        accept.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        accept.setForeground(new java.awt.Color(0, 0, 0));
        accept.setText("Accept");

        decline.setBackground(new java.awt.Color(153, 204, 255));
        decline.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        decline.setForeground(new java.awt.Color(0, 0, 0));
        decline.setText("Decline");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(accept, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(decline, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accept, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decline, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Friend Requests", jPanel1);

        jScrollPane2.setViewportView(friendList);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Friend List", jPanel2);

        jScrollPane3.setViewportView(friendSuggestions);

        add.setBackground(new java.awt.Color(153, 204, 255));
        add.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add.setForeground(new java.awt.Color(0, 0, 0));
        add.setText("Add Friend");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Friend Suggestions", jPanel3);

        jScrollPane4.setViewportView(changeFriendStatus);

        block.setBackground(new java.awt.Color(153, 204, 255));
        block.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        block.setForeground(new java.awt.Color(0, 0, 0));
        block.setText("Block");

        remove.setBackground(new java.awt.Color(153, 204, 255));
        remove.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        remove.setForeground(new java.awt.Color(0, 0, 0));
        remove.setText("Remove");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(block, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(remove, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(block, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(remove, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Block/Remove Friends", jPanel4);

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

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accept;
    private javax.swing.JButton add;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton remove;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend.SearchPackage;

import Backend.GroupPackage.Group;
import Backend.GroupPackage.GroupDatabase;
import Backend.GroupPackage.GroupSearch;
import Backend.UserPackage.User;
import Backend.UserPackage.UserDatabase;
import Backend.UserPackage.UserSearch;
import Backend.UserPackage.UserSignupSingleton;
import Frontend.GroupPackage.NormalUserUI;
import Frontend.GroupPackage.OtherAdminUI;
import Frontend.GroupPackage.PrimaryAdminUI;
import Frontend.profilePackage.ProfileManagmentForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdelrahman
 */
public class SearchFrame extends javax.swing.JFrame {
    
    private UserSearch userSearch;
    private GroupSearch groupSearch;
    private User currentUser;
    /**
     * Creates new form SearchFrame
     */
    public SearchFrame() {
        initComponents();
        setTitle("Search Engine");
        setLocationRelativeTo(null);
        pack();
        this.currentUser = UserSignupSingleton.getInstance().getUser();
        initCustomComponents();
        
    }
    
    private void initCustomComponents(){
        
        
        searchButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                userSearch = UserSearch.UserSearchFactory.createUserSearch(username);
                /*  Filling out JLIST for users */
                DefaultListModel<String> usersModel = new DefaultListModel<>();
                userList.setModel(usersModel);
                for(User user:userSearch.getSearchUsers()){
                    usersModel.addElement(user.getUserId() + " " + user.getUsername());
                }
                
                addFriend.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                         String line = userList.getSelectedValue();
                         String [] data = line.split(" ");
                         User userInList = UserDatabase.getInstance().getUser(data[0]);
                         userSearch.addFriend(currentUser, userInList);
                    } 
                });
                
                removeFriend.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                         String line = userList.getSelectedValue();
                         String [] data = line.split(" ");
                         User userInList = UserDatabase.getInstance().getUser(data[0]);
                         userSearch.removeFriend(currentUser, userInList);
                    } 
                });
                
                blockUser.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                         String line = userList.getSelectedValue();
                         String [] data = line.split(" ");
                         User userInList = UserDatabase.getInstance().getUser(data[0]);
                         userSearch.blockUser(currentUser, userInList);
                    } 
                });
                
                viewProfile.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String line = userList.getSelectedValue();
                        String [] data = line.split(" ");
                        User userInList = UserDatabase.getInstance().getUser(data[0]);
                        ProfileManagmentForm  profileForm = new ProfileManagmentForm(userInList);
                        profileForm.setVisible(true);  
                    } 
                });
                  
            }
        });
        
        searchButton2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String groupName = groupTextField.getText();
                groupSearch = GroupSearch.GroupSearchFactory.createGroupSearch(groupName);
                DefaultListModel <String> groupsModel = new DefaultListModel<>();
                groupList.setModel(groupsModel);
                 for(Group group:groupSearch.getSearchGroups()){
                    groupsModel.addElement(group.getGroupId() + " " + group.getGroupName() + " " + group.getGroupDescription());
                }
                
                joinGroup.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String line = groupList.getSelectedValue();
                        String [] data = line.split(" ");
                        Group group = GroupDatabase.getInstance().getGroupById(data[0]);
                        groupSearch.joinGroup(currentUser, group);
                        JOptionPane.showMessageDialog(null, "Group request sent to " + group.getGroupId(), "Success", JOptionPane.INFORMATION_MESSAGE);
                    } 
                });
                
                leaveGroup.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String line = groupList.getSelectedValue();
                        String [] data = line.split(" ");
                        Group group = GroupDatabase.getInstance().getGroupById(data[0]);
                        groupSearch.leaveGroup(currentUser.getUserId(), group);
                        JOptionPane.showMessageDialog(null, "Left the group successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } 
                });
                
                
                viewGroup.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String line = groupList.getSelectedValue();
                        String [] data = line.split(" ");
                        Group group = GroupDatabase.getInstance().getGroupById(data[0]);
                     String check = GroupDatabase.getInstance().checkIfInGroup(currentUser.getUserId(), group);
                     if(check.equals("PRIMARY")){
                         PrimaryAdminUI primaryAdmin = new PrimaryAdminUI(group);
                         primaryAdmin.setVisible(true);
                     }
                     else if(check.equals("OTHER")){
                         OtherAdminUI otherAdmin = new OtherAdminUI(group);
                         otherAdmin.setVisible(true);
                     }
                     else if(check.equals("MEMBER")){
                         NormalUserUI normalUser = new NormalUserUI(group);
                         normalUser.setVisible(true);
                     }
                     else {
                         JOptionPane.showMessageDialog(null, "You are not a member in this group", "Fail", JOptionPane.INFORMATION_MESSAGE);
                     }
                     
                    } 
                });   
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

        jLabel1 = new javax.swing.JLabel();
        groupTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        groupList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        userTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        userList = new javax.swing.JList<>();
        blockUser = new javax.swing.JButton();
        viewProfile = new javax.swing.JButton();
        addFriend = new javax.swing.JButton();
        removeFriend = new javax.swing.JButton();
        joinGroup = new javax.swing.JButton();
        leaveGroup = new javax.swing.JButton();
        viewGroup = new javax.swing.JButton();
        searchButton1 = new javax.swing.JButton();
        searchButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(30, 30, 47));

        jLabel1.setBackground(new java.awt.Color(92, 107, 192));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("User Search");
        jLabel1.setOpaque(true);

        groupTextField.setBackground(new java.awt.Color(220, 214, 247));
        groupTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        groupTextField.setForeground(new java.awt.Color(0, 0, 0));

        groupList.setBackground(new java.awt.Color(220, 214, 247));
        groupList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        groupList.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        groupList.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(groupList);

        jLabel2.setBackground(new java.awt.Color(92, 107, 192));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Group Search");
        jLabel2.setOpaque(true);

        userTextField.setBackground(new java.awt.Color(220, 214, 247));
        userTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userTextField.setForeground(new java.awt.Color(0, 0, 0));

        userList.setBackground(new java.awt.Color(220, 214, 247));
        userList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        userList.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userList.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(userList);

        blockUser.setBackground(new java.awt.Color(242, 139, 130));
        blockUser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        blockUser.setForeground(new java.awt.Color(255, 255, 255));
        blockUser.setText("Block User");

        viewProfile.setBackground(new java.awt.Color(144, 202, 249));
        viewProfile.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        viewProfile.setForeground(new java.awt.Color(0, 0, 0));
        viewProfile.setText("View Profile");
        viewProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewProfileActionPerformed(evt);
            }
        });

        addFriend.setBackground(new java.awt.Color(76, 175, 80));
        addFriend.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addFriend.setForeground(new java.awt.Color(255, 255, 255));
        addFriend.setText("Add Friend");
        addFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFriendActionPerformed(evt);
            }
        });

        removeFriend.setBackground(new java.awt.Color(242, 139, 130));
        removeFriend.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        removeFriend.setForeground(new java.awt.Color(255, 255, 255));
        removeFriend.setText("Remove Friend");

        joinGroup.setBackground(new java.awt.Color(76, 175, 80));
        joinGroup.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        joinGroup.setForeground(new java.awt.Color(255, 255, 255));
        joinGroup.setText("Join Group");

        leaveGroup.setBackground(new java.awt.Color(242, 139, 130));
        leaveGroup.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        leaveGroup.setForeground(new java.awt.Color(255, 255, 255));
        leaveGroup.setText("Leave Group");

        viewGroup.setBackground(new java.awt.Color(144, 202, 249));
        viewGroup.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        viewGroup.setForeground(new java.awt.Color(0, 0, 0));
        viewGroup.setText("View Group");

        searchButton1.setBackground(new java.awt.Color(92, 107, 192));
        searchButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        searchButton1.setForeground(new java.awt.Color(255, 255, 255));
        searchButton1.setText("Search");

        searchButton2.setBackground(new java.awt.Color(92, 107, 192));
        searchButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        searchButton2.setForeground(new java.awt.Color(255, 255, 255));
        searchButton2.setText("Search");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(removeFriend, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(addFriend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(leaveGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(joinGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userTextField)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(groupTextField)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(blockUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addComponent(viewProfile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(viewGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(searchButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(blockUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(viewProfile)
                                .addGap(44, 44, 44))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(addFriend)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeFriend)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(groupTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(joinGroup)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(leaveGroup))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(viewGroup)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFriendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addFriendActionPerformed

    private void viewProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewProfileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewProfileActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFriend;
    private javax.swing.JButton blockUser;
    private javax.swing.JList<String> groupList;
    private javax.swing.JTextField groupTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton joinGroup;
    private javax.swing.JButton leaveGroup;
    private javax.swing.JButton removeFriend;
    private javax.swing.JButton searchButton1;
    private javax.swing.JButton searchButton2;
    private javax.swing.JList<String> userList;
    private javax.swing.JTextField userTextField;
    private javax.swing.JButton viewGroup;
    private javax.swing.JButton viewProfile;
    // End of variables declaration//GEN-END:variables
}

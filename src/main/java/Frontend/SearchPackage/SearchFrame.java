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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;

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
                
                
                
                
            }
        });
        
        searchButton2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String groupName = groupTextField.getText();
                groupSearch = GroupSearch.GroupSearchFactory.createGroupSearch(groupName);
                DefaultListModel<String> groupsModel = new DefaultListModel<>();
                userList.setModel(groupsModel);
                 for(Group group:groupSearch.getSearchGroups()){
                    groupsModel.addElement(group.getGroupName() + " " + group.getGroupDescription() + " " + group.getGroupPhoto());
                }
                
                joinGroup.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String line = groupList.getSelectedValue();
                        String [] data = line.split(" ");
                        String groupName = data[0];
                        String groupDescription = data[1];
                        String groupPhoto = data[2];
                        Group group = GroupDatabase.getInstance().getGroupByAttributes(groupName, groupDescription, groupPhoto);
                        groupSearch.joinGroup(currentUser, group);
                    } 
                });
                
                leaveGroup.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String line = groupList.getSelectedValue();
                        String [] data = line.split(" ");
                        String groupName = data[0];
                        String groupDescription = data[1];
                        String groupPhoto = data[2];
                        Group group = GroupDatabase.getInstance().getGroupByAttributes(groupName, groupDescription, groupPhoto);
                        groupSearch.leaveGroup(currentUser.getUserId(), group);
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

        jLabel1.setBackground(new java.awt.Color(153, 255, 204));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("User Search");
        jLabel1.setOpaque(true);

        groupTextField.setBackground(new java.awt.Color(204, 204, 255));
        groupTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        groupTextField.setForeground(new java.awt.Color(0, 0, 0));

        groupList.setBackground(new java.awt.Color(204, 204, 255));
        groupList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        groupList.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        groupList.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(groupList);

        jLabel2.setBackground(new java.awt.Color(153, 255, 204));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Group Search");
        jLabel2.setOpaque(true);

        userTextField.setBackground(new java.awt.Color(204, 204, 255));
        userTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userTextField.setForeground(new java.awt.Color(0, 0, 0));

        userList.setBackground(new java.awt.Color(204, 204, 255));
        userList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        userList.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userList.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(userList);

        blockUser.setBackground(new java.awt.Color(255, 204, 204));
        blockUser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        blockUser.setForeground(new java.awt.Color(0, 0, 0));
        blockUser.setText("Block User");

        viewProfile.setBackground(new java.awt.Color(255, 204, 204));
        viewProfile.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        viewProfile.setForeground(new java.awt.Color(0, 0, 0));
        viewProfile.setText("View Profile");

        addFriend.setBackground(new java.awt.Color(255, 204, 204));
        addFriend.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addFriend.setForeground(new java.awt.Color(0, 0, 0));
        addFriend.setText("Add Friend");

        removeFriend.setBackground(new java.awt.Color(255, 204, 204));
        removeFriend.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        removeFriend.setForeground(new java.awt.Color(0, 0, 0));
        removeFriend.setText("Remove Friend");

        joinGroup.setBackground(new java.awt.Color(255, 204, 204));
        joinGroup.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        joinGroup.setForeground(new java.awt.Color(0, 0, 0));
        joinGroup.setText("Join Group");

        leaveGroup.setBackground(new java.awt.Color(255, 204, 204));
        leaveGroup.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        leaveGroup.setForeground(new java.awt.Color(0, 0, 0));
        leaveGroup.setText("Leave Group");

        viewGroup.setBackground(new java.awt.Color(255, 204, 204));
        viewGroup.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        viewGroup.setForeground(new java.awt.Color(0, 0, 0));
        viewGroup.setText("View Group");

        searchButton1.setBackground(new java.awt.Color(153, 255, 204));
        searchButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        searchButton1.setForeground(new java.awt.Color(0, 0, 0));
        searchButton1.setText("Search");

        searchButton2.setBackground(new java.awt.Color(153, 255, 204));
        searchButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        searchButton2.setForeground(new java.awt.Color(0, 0, 0));
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(removeFriend, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                .addComponent(addFriend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(joinGroup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(leaveGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(blockUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(viewProfile)
                                .addGap(26, 26, 26))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(addFriend)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(removeFriend)
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(groupTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(joinGroup)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(leaveGroup)
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(viewGroup)))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

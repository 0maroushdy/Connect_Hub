/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend.GroupPackage;

import Backend.ContentPackage.ContentDataBase;
import Backend.ContentPackage.Post;
import Backend.GroupPackage.Group;
import Backend.GroupPackage.GroupDatabase;
import Backend.UserPackage.User;
import Backend.UserPackage.UserDatabase;
import Backend.UserPackage.UserSignupSingleton;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Abdelrahman
 */
public class OtherAdminUI extends javax.swing.JFrame {
    
    private User currentUser;
    private Group group;
    private UserDatabase userDatabase;
    /**
     * Creates new form OtherAdminUI
     */
    public OtherAdminUI(Group group) {
        initComponents();
        setTitle("OtherAdminUI");
        setLocationRelativeTo(null);
        pack();
        this.group = group;
        this.currentUser = UserSignupSingleton.getInstance().getUser();
        this.userDatabase = UserDatabase.getInstance();
        initCustomComponents();
    }
    
    private void initCustomComponents(){
        DefaultListModel <String> membersListModel = new DefaultListModel();
        
        membersList.setModel(membersListModel);
                     /* Filling Out List */
        membersListModel.addElement(group.getGroupPrimaryAdminId() + " " + this.userDatabase.getUser(group.getGroupPrimaryAdminId()).getUsername());
        
        for(String id:group.getGroupMemberIds()){
            membersListModel.addElement(id + " " + this.userDatabase.getUser(id).getUsername());
        }
        
        for(String id:group.getGroupOtherAdminsIds()){
            membersListModel.addElement(id + " " + this.userDatabase.getUser(id).getUsername());
        }
        
        JPanel postsContainer = new JPanel();
          postsContainer.setLayout(new BoxLayout(postsContainer, BoxLayout.Y_AXIS));

        for (Post post : ContentDataBase.getInstance().getPosts()) {
          if (post.getGroupId().equals(group.getGroupId())) {
        PostPanell postPanel = new PostPanell(post,postsContainer);
        postsContainer.add(postPanel);
         }
        }

         // Wrap postsContainer in JScrollPane
       JScrollPane scrollPane = new JScrollPane(postsContainer);
       scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
       scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
       scrollPane.setPreferredSize(new Dimension(600,500));
             // Ensure postsContainer respects its contents
          postsContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
          postsPanel.setLayout(new BorderLayout());
            // Add the scrollPane to your main panel
          postsPanel.add(scrollPane, BorderLayout.CENTER);
          postsPanel.revalidate();
          postsPanel.repaint();
        
        approveRequest.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               String line = membersList.getSelectedValue();
               String [] data = line.split(" ");
               if(currentUser.getUserId().equals(data[0])){
                    JOptionPane.showMessageDialog(null, "you are already a member of this group", "Fail", JOptionPane.INFORMATION_MESSAGE);
               }
               else if(GroupDatabase.getInstance().acceptGroupRequest(currentUser.getUserId(),data[0], group)){
                   JOptionPane.showMessageDialog(null, "Accepted member request with id " + data[0], "Success", JOptionPane.INFORMATION_MESSAGE);
                   UserDatabase.getInstance().getUser(currentUser.getUserId()).getNotificationManager().addFriendRequestNotification(group.getGroupId()+" "+" "+group.getGroupName()+" "+"|You have Been Accepted to Group: "+ group.getGroupName());

              }
              else {
                  JOptionPane.showMessageDialog(null, "You are already a member of this group", "Fail", JOptionPane.INFORMATION_MESSAGE);
              }
            }   
        });
        
        declineRequest.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               String line = membersList.getSelectedValue();
               String [] data = line.split(" ");
               if(currentUser.getUserId().equals(data[0])){
                   JOptionPane.showMessageDialog(null, "you are already a member of this group", "Fail", JOptionPane.INFORMATION_MESSAGE);
               }
               else if(GroupDatabase.getInstance().declineGroupRequest(currentUser.getUserId(),data[0], group)){
                  JOptionPane.showMessageDialog(null, "Declined member request with id " + data[0], "Success", JOptionPane.INFORMATION_MESSAGE);
              }
              else {
                  JOptionPane.showMessageDialog(null, "You are already a member of this group", "Fail", JOptionPane.INFORMATION_MESSAGE);
              }
            }   
        });
        
        
        removeMember.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               String line = membersList.getSelectedValue();
               String [] data = line.split(" ");
              if(currentUser.getUserId().equals(data[0])){
                  JOptionPane.showMessageDialog(null, "Cant remove yourself", "Fail", JOptionPane.INFORMATION_MESSAGE);
              }
              else if(GroupDatabase.getInstance().removeNormalMember(currentUser.getUserId(),data[0], group)){
                  JOptionPane.showMessageDialog(null, "Removed member with id " + data[0], "Success", JOptionPane.INFORMATION_MESSAGE);
              }
              else {
                  JOptionPane.showMessageDialog(null, "Failed to remove member", "Fail", JOptionPane.INFORMATION_MESSAGE);
              }
            }   
        });
        
        
          addPost.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                AddGroupPost post = new AddGroupPost(group);
                post.setVisible(true);

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
        jScrollPane1 = new javax.swing.JScrollPane();
        membersList = new javax.swing.JList<>();
        removeMember = new javax.swing.JButton();
        approveRequest = new javax.swing.JButton();
        declineRequest = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        postsPanel = new javax.swing.JPanel();
        addPost = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setBackground(new java.awt.Color(92, 107, 192));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("All Members In The Group");
        jLabel1.setOpaque(true);

        membersList.setBackground(new java.awt.Color(255, 255, 255));
        membersList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        membersList.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        membersList.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(membersList);

        removeMember.setBackground(new java.awt.Color(255, 102, 102));
        removeMember.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        removeMember.setText("Remove Member");

        approveRequest.setBackground(new java.awt.Color(76, 175, 80));
        approveRequest.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        approveRequest.setText("Approve Request");

        declineRequest.setBackground(new java.awt.Color(255, 102, 102));
        declineRequest.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        declineRequest.setText("Decline Request");

        jLabel2.setBackground(new java.awt.Color(92, 107, 192));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Posts");
        jLabel2.setOpaque(true);

        postsPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout postsPanelLayout = new javax.swing.GroupLayout(postsPanel);
        postsPanel.setLayout(postsPanelLayout);
        postsPanelLayout.setHorizontalGroup(
            postsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        postsPanelLayout.setVerticalGroup(
            postsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        addPost.setBackground(new java.awt.Color(76, 175, 80));
        addPost.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        addPost.setForeground(new java.awt.Color(255, 255, 255));
        addPost.setText("Add post");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                    .addComponent(postsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(declineRequest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeMember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(approveRequest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addPost, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(postsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(approveRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(declineRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(addPost, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(removeMember, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPost;
    private javax.swing.JButton approveRequest;
    private javax.swing.JButton declineRequest;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> membersList;
    private javax.swing.JPanel postsPanel;
    private javax.swing.JButton removeMember;
    // End of variables declaration//GEN-END:variables
}

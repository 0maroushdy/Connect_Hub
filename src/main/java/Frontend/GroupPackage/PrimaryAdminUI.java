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
import java.awt.FlowLayout;
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
public class PrimaryAdminUI extends javax.swing.JFrame {
    
    private User currentUser;
    private Group group;
    private UserDatabase userDatabase;
    private ContentDataBase contentDatabase;
    /**
     * Creates new form PrimaryAdminUI
     */
    public PrimaryAdminUI(Group group) {
        initComponents();
        setTitle("PrimaryAdminUI");
        setLocationRelativeTo(null);
        pack();
        this.group = group;
        this.currentUser = UserSignupSingleton.getInstance().getUser();
        this.userDatabase = UserDatabase.getInstance();
        this.contentDatabase = ContentDataBase.getInstance();
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
        
        for(String id:group.getGroupRequestsIds()){
            membersListModel.addElement(id + " " + this.userDatabase.getUser(id).getUsername() + " " + "(Request Pending)");
        }
          JPanel postsContainer = new JPanel();
          postsContainer.setLayout(new BoxLayout(postsContainer, BoxLayout.Y_AXIS));

        for (Post post : ContentDataBase.getInstance().getPosts()) {
          if (post.getGroupId().equals(group.getGroupId())) {
        PostPanell postPanel = new PostPanell(post);
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
       
        promote.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               String line = membersList.getSelectedValue();
               String [] data = line.split(" ");
              if(GroupDatabase.getInstance().promoteOtherAdmin(currentUser.getUserId(),data[0],group)){
                  JOptionPane.showMessageDialog(null, "Promoted member with id " + data[0], "Success", JOptionPane.INFORMATION_MESSAGE);
              }
              else {
                  JOptionPane.showMessageDialog(null, "Failed to promote member", "Fail", JOptionPane.INFORMATION_MESSAGE);
              }
            } 
        });
        
         demote.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               String line = membersList.getSelectedValue();
               String [] data = line.split(" ");
              if(GroupDatabase.getInstance().demoteOtherAdmin(currentUser.getUserId(),data[0],group)){
                  JOptionPane.showMessageDialog(null, "Demoted member with id " + data[0], "Success", JOptionPane.INFORMATION_MESSAGE);
              }
              else {
                  JOptionPane.showMessageDialog(null, "Failed to demote member", "Fail", JOptionPane.INFORMATION_MESSAGE);
              }
            } 
        });
         
        removeMember.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               String line = membersList.getSelectedValue();
               int ind = membersList.getSelectedIndex();
               String [] data = line.split(" ");
              if(GroupDatabase.getInstance().removeAnyGroupMember(currentUser.getUserId(),data[0], group)){
                  JOptionPane.showMessageDialog(null, "Removed member with id " + data[0], "Success", JOptionPane.INFORMATION_MESSAGE);
              }
              else {
                  JOptionPane.showMessageDialog(null, "Failed to remove member", "Fail", JOptionPane.INFORMATION_MESSAGE);
              }
            } 
        });
        
        
        deleteGroup.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              if(GroupDatabase.getInstance().deleteGroup(currentUser.getUserId(), group)){
                  JOptionPane.showMessageDialog(null, "Group is deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
              }
              else {
                  JOptionPane.showMessageDialog(null, "Group cant get deleted", "Fail", JOptionPane.INFORMATION_MESSAGE);
              }
            } 
        });
        
        
        acceptRequest.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               String line = membersList.getSelectedValue();
               String [] data = line.split(" ");
              if(GroupDatabase.getInstance().acceptGroupRequest(currentUser.getUserId(),data[0], group)){
                  JOptionPane.showMessageDialog(null, "Accepted member request with id " + data[0], "Success", JOptionPane.INFORMATION_MESSAGE);
              }
              else {
                  JOptionPane.showMessageDialog(null, "Failed to accept member request", "Fail", JOptionPane.INFORMATION_MESSAGE);
              }
            } 
        });
        
         declineRequest.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               String line = membersList.getSelectedValue();
               String [] data = line.split(" ");
              if(GroupDatabase.getInstance().declineGroupRequest(currentUser.getUserId(),data[0], group)){
                  JOptionPane.showMessageDialog(null, "Declined member request with id " + data[0], "Success", JOptionPane.INFORMATION_MESSAGE);
              }
              else {
                  JOptionPane.showMessageDialog(null, "Failed to decline member request", "Fail", JOptionPane.INFORMATION_MESSAGE);
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
        promote = new javax.swing.JButton();
        demote = new javax.swing.JButton();
        deleteGroup = new javax.swing.JButton();
        removeMember = new javax.swing.JButton();
        postsPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        acceptRequest = new javax.swing.JButton();
        declineRequest = new javax.swing.JButton();
        addPost = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setBackground(new java.awt.Color(92, 107, 192));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Posts ");
        jLabel1.setOpaque(true);

        membersList.setBackground(new java.awt.Color(255, 255, 255));
        membersList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        membersList.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        membersList.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(membersList);

        promote.setBackground(new java.awt.Color(76, 175, 80));
        promote.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        promote.setForeground(new java.awt.Color(255, 255, 255));
        promote.setText("Promote");

        demote.setBackground(new java.awt.Color(255, 102, 102));
        demote.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        demote.setForeground(new java.awt.Color(255, 255, 255));
        demote.setText("Demote");
        demote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demoteActionPerformed(evt);
            }
        });

        deleteGroup.setBackground(new java.awt.Color(255, 102, 102));
        deleteGroup.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        deleteGroup.setForeground(new java.awt.Color(255, 255, 255));
        deleteGroup.setText("Delete Group");

        removeMember.setBackground(new java.awt.Color(255, 102, 102));
        removeMember.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        removeMember.setForeground(new java.awt.Color(255, 255, 255));
        removeMember.setText("Remove Member");
        removeMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMemberActionPerformed(evt);
            }
        });

        postsPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout postsPanelLayout = new javax.swing.GroupLayout(postsPanel);
        postsPanel.setLayout(postsPanelLayout);
        postsPanelLayout.setHorizontalGroup(
            postsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
        );
        postsPanelLayout.setVerticalGroup(
            postsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 531, Short.MAX_VALUE)
        );

        jLabel2.setBackground(new java.awt.Color(92, 107, 192));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("All Members In The Group");
        jLabel2.setOpaque(true);

        acceptRequest.setBackground(new java.awt.Color(76, 175, 80));
        acceptRequest.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        acceptRequest.setForeground(new java.awt.Color(255, 255, 255));
        acceptRequest.setText("Accept Request");
        acceptRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptRequestActionPerformed(evt);
            }
        });

        declineRequest.setBackground(new java.awt.Color(255, 102, 102));
        declineRequest.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        declineRequest.setForeground(new java.awt.Color(255, 255, 255));
        declineRequest.setText("Decline Request");
        declineRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineRequestActionPerformed(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(postsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(demote, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(promote, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(deleteGroup, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(acceptRequest, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(declineRequest, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addPost, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(removeMember))))
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
                        .addGap(44, 44, 44)
                        .addComponent(promote, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(demote, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(removeMember, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(acceptRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(declineRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addPost, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(156, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 2, Short.MAX_VALUE)
                                .addComponent(postsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void demoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_demoteActionPerformed

    private void removeMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMemberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeMemberActionPerformed

    private void declineRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineRequestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_declineRequestActionPerformed

    private void acceptRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptRequestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acceptRequestActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptRequest;
    private javax.swing.JButton addPost;
    private javax.swing.JButton declineRequest;
    private javax.swing.JButton deleteGroup;
    private javax.swing.JButton demote;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> membersList;
    private javax.swing.JPanel postsPanel;
    private javax.swing.JButton promote;
    private javax.swing.JButton removeMember;
    // End of variables declaration//GEN-END:variables
}

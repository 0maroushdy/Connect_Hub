/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend.GroupPackage;

import Backend.GroupiPackage.Groupi;
import Backend.GroupiPackage.GroupDatabase;
import Backend.UserPackage.User;
import Backend.UserPackage.UserDatabase;
import Backend.UserPackage.UserSignupSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdelrahman
 */
public class PrimaryAdminUI extends javax.swing.JFrame {
    
    private User currentUser;
    private Groupi group;
    private UserDatabase userDatabase;
    /**
     * Creates new form PrimaryAdminUI
     */
    public PrimaryAdminUI(Groupi group) {
        initComponents();
        setTitle("PrimaryAdminUI");
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
        
        for(String id:group.getGroupRequestsIds()){
            membersListModel.addElement(id + " " + this.userDatabase.getUser(id).getUsername() + " " + "(Request Pending)");
        }
        
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
        managePosts = new javax.swing.JButton();
        removeMember = new javax.swing.JButton();
        postsPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        acceptRequest = new javax.swing.JButton();
        declineRequest = new javax.swing.JButton();

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

        managePosts.setBackground(new java.awt.Color(144, 202, 249));
        managePosts.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        managePosts.setForeground(new java.awt.Color(0, 0, 0));
        managePosts.setText("Manage Posts");

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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        postsPanelLayout.setVerticalGroup(
            postsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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

        declineRequest.setBackground(new java.awt.Color(255, 102, 102));
        declineRequest.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        declineRequest.setForeground(new java.awt.Color(255, 255, 255));
        declineRequest.setText("Decline Request");
        declineRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineRequestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(postsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(deleteGroup)
                                    .addComponent(managePosts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(demote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(promote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(acceptRequest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(declineRequest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(removeMember))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(promote, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(demote, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(managePosts, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(removeMember, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acceptRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(declineRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(postsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))))
                .addContainerGap(10, Short.MAX_VALUE))
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

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptRequest;
    private javax.swing.JButton declineRequest;
    private javax.swing.JButton deleteGroup;
    private javax.swing.JButton demote;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton managePosts;
    private javax.swing.JList<String> membersList;
    private javax.swing.JPanel postsPanel;
    private javax.swing.JButton promote;
    private javax.swing.JButton removeMember;
    // End of variables declaration//GEN-END:variables
}

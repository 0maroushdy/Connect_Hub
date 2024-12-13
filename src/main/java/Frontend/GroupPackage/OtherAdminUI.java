/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend.GroupPackage;

import Backend.UserPackage.User;
import Backend.UserPackage.UserDatabase;
import Backend.UserPackage.UserSignupSingleton;
import GroupPackage.Group;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

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

    private void initCustomComponents() {
        DefaultListModel<String> membersListModel = new DefaultListModel();

        membersList.setModel(membersListModel);
        /* Filling Out List */
        membersListModel.addElement(group.getHandler().getMainAdminId() + " " + this.userDatabase.getUser(group.getHandler().getMainAdminId()).getUsername());

        for (String id : group.getHandler().getMemberIds()) {
            membersListModel.addElement(id + " " + this.userDatabase.getUser(id).getUsername());
        }

        for (String id : group.getHandler().getAdminIds()) {
            membersListModel.addElement(id + " " + this.userDatabase.getUser(id).getUsername());
        }

        approveRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String line = membersList.getSelectedValue();
                String[] data = line.split(" ");
                try {

                    group.getHandler().approveJoinRequest(currentUser.getUserId().toString(), line);

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to accept member request", "Fail", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showMessageDialog(null, "Accepted member request with id " + data[0], "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        declineRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String line = membersList.getSelectedValue();
                String[] data = line.split(" ");
                try {

                    group.getHandler().rejectJoinRequest(currentUser.getUserId().toString(), line);

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Declined member request with id " + data[0], "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showMessageDialog(null, "Failed to decline member request", "Fail", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        removeMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String line = membersList.getSelectedValue();
                String[] data = line.split(" ");

                try {

                    group.getHandler().removeMember(currentUser.getUserId().toString(), line);

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to remove member", "Fail", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showMessageDialog(null, "Removed member with id " + data[0], "Success", JOptionPane.INFORMATION_MESSAGE);
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
        managePosts = new javax.swing.JButton();
        approveRequest = new javax.swing.JButton();
        declineRequest = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        postsPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        managePosts.setBackground(new java.awt.Color(144, 202, 249));
        managePosts.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        managePosts.setForeground(new java.awt.Color(0, 0, 0));
        managePosts.setText("Manage Posts");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addComponent(postsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(declineRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(managePosts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(removeMember, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                    .addComponent(approveRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                    .addComponent(postsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(approveRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(declineRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(managePosts, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(removeMember, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton approveRequest;
    private javax.swing.JButton declineRequest;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton managePosts;
    private javax.swing.JList<String> membersList;
    private javax.swing.JPanel postsPanel;
    private javax.swing.JButton removeMember;
    // End of variables declaration//GEN-END:variables
}

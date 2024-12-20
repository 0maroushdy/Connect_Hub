/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend.GroupPackage;

import Backend.ContentPackage.CommentDatabase;
import Backend.ContentPackage.Post;
import Backend.UserPackage.User;
import Backend.UserPackage.UserSignupSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdelrahman
 */
public class AddComment extends javax.swing.JFrame {
    
    private User currentUser;
    private Post post;
    /**
     * Creates new form AddComment
     */
    public AddComment(User currentUser,Post post) {
        initComponents();
        this.currentUser = currentUser;
        this.post = post;
        setTitle("Add Comment");
        setLocationRelativeTo(null);
        pack();
        initCustomComponents();
    }
    
    private void initCustomComponents(){
          
        comment.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               String commentContent = content.getText();
              if(CommentDatabase.getInstance().addComment(post, currentUser, commentContent)){
                  JOptionPane.showMessageDialog(null, "Comment added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
              }
                else{
                  JOptionPane.showMessageDialog(null, "Missing fields", "Fail", JOptionPane.INFORMATION_MESSAGE);
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
        content = new javax.swing.JTextField();
        comment = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(204, 204, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Comment Content");
        jLabel1.setOpaque(true);

        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        content.setForeground(new java.awt.Color(0, 0, 0));

        comment.setBackground(new java.awt.Color(153, 204, 255));
        comment.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        comment.setForeground(new java.awt.Color(0, 0, 0));
        comment.setText("Comment");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(content)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(comment, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(comment, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton comment;
    private javax.swing.JTextField content;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend.GroupPackage;

import Backend.ContentPackage.ContentDataBase;
import Backend.ContentPackage.Post;
import Backend.GroupPackage.Group;
import Backend.UserPackage.UserDatabase;
import Backend.UserPackage.UserSignupSingleton;
import static Files.FILEPATHS.USERFILE;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdelrahman
 */
public class AddGroupPost extends javax.swing.JFrame {
     
    private String imagePath;
    private Group group;
    /**
     * Creates new form AddGroupPost
     */
    public AddGroupPost(Group group) {
        initComponents();
        this.group = group;
        setTitle("AddGroupPost");
        setLocationRelativeTo(null);
        pack();
        initCustomComponents();
    }
    
    private void initCustomComponents(){
        
        uploadPost.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String postText = text.getText();
                if(postText != null && postText.length()!=0 && imagePath!=null && imagePath.length()!=0){
                 Post post = new Post.Builder(
                group.getGroupId(),
                UserSignupSingleton.getInstance().getUser().getUserId(),
                postText
        )
                .setImagePath(imagePath)
                .build();
                 post.uplode();
                      JOptionPane.showMessageDialog(null, "Post has been apploaded successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                      ContentDataBase.getInstance().addContent(post);
                      UserDatabase.getInstance().saveUsersToFile(USERFILE);
                      setVisible(false);
                      
//                      for(int i=0; i < group.getGroupMemberIds().size(); i++ ){
//                          UserDatabase.getInstance().getUser(USERFILE)
//                      }
                        for(String ID:group.getGroupMemberIds()){
                            UserDatabase.getInstance().getUser(ID).getNotificationManager().addGroupActivityNotification(
                                         UserSignupSingleton.getInstance().getUser().getUsername() +
                                         " Published a Post at Group: "+group.getGroupName() );
                        }
                        UserDatabase.getInstance().getUser(UserSignupSingleton.getInstance().getUser().getUserId()).getNotificationManager().addFriendRequestNotification("Post Was added to Group: "+ group.getGroupName());

                }
                else{
                    JOptionPane.showMessageDialog(null, "Failed to add post", "Fail", JOptionPane.INFORMATION_MESSAGE);
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

        lblImage = new javax.swing.JLabel();
        text = new javax.swing.JTextField();
        uploadPost = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblImage.setBackground(new java.awt.Color(255, 255, 255));
        lblImage.setOpaque(true);

        text.setBackground(new java.awt.Color(255, 255, 255));

        uploadPost.setBackground(new java.awt.Color(153, 204, 255));
        uploadPost.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        uploadPost.setForeground(new java.awt.Color(0, 0, 0));
        uploadPost.setText("Upload Post");

        jButton2.setBackground(new java.awt.Color(153, 204, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Upload Image");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(uploadPost, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(uploadPost, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();

            // Load the selected image
            ImageIcon selectedImage = new ImageIcon(file.getAbsolutePath());

            // Scale the image to fit the JLabel dimensions
            Image scaledImage = selectedImage.getImage().getScaledInstance(
                lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);

            // Set the scaled image as the JLabel icon
            lblImage.setIcon(new ImageIcon(scaledImage));
            imagePath = file.getAbsolutePath();}
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTextField text;
    private javax.swing.JButton uploadPost;
    // End of variables declaration//GEN-END:variables
}

package Frontend.profilePackage;
import java.lang.*;
import Backend.UserPackage.User;
import Backend.UserPackage.UserDatabase;
import Backend.UserPackage.UserSignupSingleton;
import Backend.UserProfilePackage.overSizeInputException;
import static Files.FILEPATHS.USERFILE;
import Frontend.UserPackage.FriendsGui;
import Frontend.UserPackage.News;
import java.awt.Image;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


/**
 *
 * @author Omar
 */
public class PublicProfileManagmentForm extends javax.swing.JFrame {
    

    User MainUser = UserSignupSingleton.getInstance().getUser();
    User currentUser = new User();
    
    String newProfilePhoto = currentUser.getUserProfile().getProfilePhoto();
    String newProfileCover = currentUser.getUserProfile().getProfileCover();
    String newProfileBio   = currentUser.getUserProfile().getProfileBio();
    

    // ----------------** Constractor **------------------
  
        public PublicProfileManagmentForm() {
        }
        
        public PublicProfileManagmentForm(User user) {
        this.currentUser = UserDatabase.getInstance().getUser(user.getUserId());
        initComponents();
        setDefaultImages();
        setDefaultText();
        super.setVisible(true);
        super.setTitle("Profile");
        setLocationRelativeTo(null);
    }
    
    
    private void setProfileImg() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            ImageIcon defaultProfileIcon = new ImageIcon(file.getPath());
            Image scaledProfile = defaultProfileIcon.getImage().getScaledInstance(
            lblProfilePhoto.getWidth(), lblProfilePhoto.getHeight(), Image.SCALE_SMOOTH);
            lblProfilePhoto.setIcon(new ImageIcon(scaledProfile));
         }
    }
    
    
    private void setDefaultImages() {
        
    if(this.newProfilePhoto != ""){
        // Load the selected image
       ImageIcon selectedImage = new ImageIcon(newProfilePhoto);
       ImageIcon selectedImageCover = new ImageIcon(newProfileCover);

       // Scale the image to fit the JLabel dimensions
       Image scaledImage = selectedImage.getImage().getScaledInstance(
        lblProfilePhoto.getWidth(), lblProfilePhoto.getHeight(), Image.SCALE_SMOOTH);
       
       Image scaledCover = selectedImageCover.getImage().getScaledInstance(
        lblCoverPhoto.getWidth(), lblCoverPhoto.getHeight(), Image.SCALE_SMOOTH);

       // Set the scaled image as the JLabel icon
       lblProfilePhoto.setIcon(new ImageIcon(scaledImage));  
       lblCoverPhoto.setIcon(new ImageIcon(scaledCover));  
       
    } else {
    // Default Cover Photo
    ImageIcon defaultCoverIcon = new ImageIcon("resources/default_cover.jpg");
    Image scaledCover = defaultCoverIcon.getImage().getScaledInstance(
        lblCoverPhoto.getWidth(), lblCoverPhoto.getHeight(), Image.SCALE_SMOOTH);
    lblCoverPhoto.setIcon(new ImageIcon(scaledCover));

    // Default Profile Photo
    ImageIcon defaultProfileIcon = new ImageIcon("resources/default_profile.jpg");
    Image scaledProfile = defaultProfileIcon.getImage().getScaledInstance(
        lblProfilePhoto.getWidth(), lblProfilePhoto.getHeight(), Image.SCALE_SMOOTH);
    lblProfilePhoto.setIcon(new ImageIcon(scaledProfile));
    }
} 
    
    public void setDefaultText (){
        // setting the user name of the profile page
        lblName1.setText(currentUser.getUsername());
        
        // setting the Bio of the profile page
        String bioTxt = currentUser.getUserProfile().getProfileBio();
        lblBio.setText(bioTxt);
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCoverPhoto = new javax.swing.JLabel();
        lblProfilePhoto = new javax.swing.JLabel();
        btnEditCoverPhoto = new javax.swing.JButton();
        lblName1 = new javax.swing.JLabel();
        lblBio = new javax.swing.JLabel();
        btnPosts = new javax.swing.JButton();
        btnLogOut = new javax.swing.JButton();
        btnFriends = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblCoverPhoto.setBackground(new java.awt.Color(102, 102, 102));
        lblCoverPhoto.setAutoscrolls(true);
        lblCoverPhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCoverPhotoMouseClicked(evt);
            }
        });

        btnEditCoverPhoto.setBackground(new java.awt.Color(0, 153, 153));
        btnEditCoverPhoto.setForeground(new java.awt.Color(255, 255, 255));
        btnEditCoverPhoto.setText("Add Friend");
        btnEditCoverPhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditCoverPhotoMouseClicked(evt);
            }
        });
        btnEditCoverPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCoverPhotoActionPerformed(evt);
            }
        });

        lblName1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblName1.setText("User Name");

        lblBio.setText("this is a bio text, this is a bio text,  this is a bio text.    ");

        btnPosts.setBackground(new java.awt.Color(255, 204, 51));
        btnPosts.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnPosts.setText("View Posts");
        btnPosts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPostsMouseClicked(evt);
            }
        });
        btnPosts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPostsActionPerformed(evt);
            }
        });

        btnLogOut.setBackground(new java.awt.Color(0, 51, 51));
        btnLogOut.setForeground(new java.awt.Color(255, 255, 255));
        btnLogOut.setText("Back");
        btnLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogOutMouseClicked(evt);
            }
        });
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        btnFriends.setBackground(new java.awt.Color(255, 153, 102));
        btnFriends.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFriends.setText("View Friends");
        btnFriends.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFriendsMouseClicked(evt);
            }
        });
        btnFriends.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFriendsActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(213, 217, 225));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCoverPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLogOut)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblProfilePhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPosts, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFriends, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblName1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBio, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(99, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEditCoverPhoto)
                        .addGap(17, 17, 17))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblCoverPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProfilePhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEditCoverPhoto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblName1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblBio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPosts)
                    .addComponent(btnFriends))
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogOut)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblCoverPhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCoverPhotoMouseClicked
    }//GEN-LAST:event_lblCoverPhotoMouseClicked

    private void btnPostsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPostsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPostsMouseClicked

    private void btnPostsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPostsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPostsActionPerformed

    private void btnLogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogOutMouseClicked
        // TODO add your handling code here:
        this.dispose();
        try {
            new News().setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(PublicProfileManagmentForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLogOutMouseClicked

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        // TODO add your handling code here:
        dispose();
        try {
            new News();
        } catch (IOException ex) {
            Logger.getLogger(PublicProfileManagmentForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnFriendsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFriendsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFriendsMouseClicked

    private void btnFriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFriendsActionPerformed
        // TODO add your handling code here:
        FriendsGui friends = new FriendsGui();
        friends.setVisible(true);
    }//GEN-LAST:event_btnFriendsActionPerformed

    private void btnEditCoverPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCoverPhotoActionPerformed
        // TODO add your handling code here:
        // gotoit
    }//GEN-LAST:event_btnEditCoverPhotoActionPerformed

    private void btnEditCoverPhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditCoverPhotoMouseClicked

    }//GEN-LAST:event_btnEditCoverPhotoMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PublicProfileManagmentForm().setVisible(true);
                String Name;
                String Bio;
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditCoverPhoto;
    private javax.swing.JButton btnFriends;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnPosts;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBio;
    private javax.swing.JLabel lblCoverPhoto;
    private javax.swing.JLabel lblName1;
    private javax.swing.JLabel lblProfilePhoto;
    // End of variables declaration//GEN-END:variables
}

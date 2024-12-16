/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Frontend.GroupPackage;

import Backend.ContentPackage.ContentDataBase;
import Backend.ContentPackage.Post;
import Backend.UserPackage.User;
import Backend.UserPackage.UserDatabase;
import Backend.UserPackage.UserSignupSingleton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Abdelrahman
 */
public class PostPanell extends javax.swing.JPanel {
    private User currentUser;
    private Post post;
    private ContentDataBase contentDatabase;
    private JPanel postsContainer;
    private boolean isClicked = false;
    /**
     * Creates new form PostPanell
     */
    public PostPanell(Post post,JPanel postsContainer) {
        initComponents();
        this.post = post;
        this.postsContainer = postsContainer;
        this.currentUser = UserSignupSingleton.getInstance().getUser();
       // setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.contentDatabase = ContentDataBase.getInstance();
        initCustomComponents();
    }
    
     private void initCustomComponents(){
        
        lblAuthorName.setText(UserDatabase.getInstance().getUser(post.getAuthorId()).getUsername());
        lblTimeStamp.setText(post.getTimestamp());
        lblContentText.setText(post.getText());
        addPostPhoto();
        lblLikeCounter.setText(post.getPostLikes().size() + " ");
        
        btnDelete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               // System.out.println(ContentDataBase.getInstance().getPosts().size());
                for(Post postt:contentDatabase.getPosts()){
                    if(postt.getContentId().equals(post.getContentId())){
                        contentDatabase.removePost(postt);
                        JOptionPane.showMessageDialog(null, "Deleted Post", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                  for (Component component : postsContainer.getComponents()) {
                    if (component instanceof PostPanell) {
                        PostPanell postPanel = (PostPanell) component;
                        if (postPanel.getPost().getContentId().equals(post.getContentId())) {
                            postsContainer.remove(postPanel);
                            break; // Exit loop once the panel is removed
                        }
                    }
                }
                postsContainer.revalidate();
                postsContainer.repaint();
                  
                  
            }  
        });
        
        btnEdit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                EditPostFrame edit = new EditPostFrame(post);
                edit.setVisible(true);  
            }  
        });
        
        setBorder(BorderFactory.createLineBorder(Color.RED));
        
        if(post.getPostLikes().containsKey(currentUser.getUserId())){ btnLike.setBackground(Color.blue);
        isClicked = true;
        btnLike.setText("Liked");}
        
        btnLike.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                 if (isClicked) {
                    btnLike.setText("Like");
                    btnLike.setBackground(Color.WHITE);
                    ContentDataBase.getInstance().removeLike(currentUser, post);
                    lblLikeCounter.setText(post.getPostLikes().size() + " ");
                    isClicked = false;
                } else {
                    btnLike.setText("Liked");
                    btnLike.setBackground(Color.blue);
                    ContentDataBase.getInstance().likePost(currentUser, post);
                    lblLikeCounter.setText(post.getPostLikes().size() + " ");
                    isClicked = true;
                }
                
            }  
        });
        
        
        btnAddComment.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                AddComment comment = new AddComment(currentUser,post);
                comment.setVisible(true);
            }  
        });
        
        btnShowComments.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                AllPostComments comments = new AllPostComments(post);
                comments.setVisible(true); 
            }  
        });
        
        
        
    }
    
    
    private void addPostPhoto(){

            // Load the selected image
            ImageIcon selectedImage = new ImageIcon(post.getImagePath());
            
            // Scale the image to fit the JLabel dimensions
            Image scaledImage = selectedImage.getImage().getScaledInstance(
                200, 200, Image.SCALE_SMOOTH);

            // Set the scaled image as the JLabel icon
            lblContentPhoto.setIcon(new ImageIcon(scaledImage));  
        
    }
    
    public Dimension getPreferredSize() {
    return new Dimension(500, 400); // Adjust width and height as needed
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAuthorName = new javax.swing.JLabel();
        lblTimeStamp = new javax.swing.JLabel();
        lblContentText = new javax.swing.JLabel();
        lblContentPhoto = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnLike = new javax.swing.JButton();
        btnAddComment = new javax.swing.JButton();
        lblLikeCounter = new javax.swing.JLabel();
        btnShowComments = new javax.swing.JButton();

        lblAuthorName.setBackground(new java.awt.Color(255, 255, 255));
        lblAuthorName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAuthorName.setForeground(new java.awt.Color(0, 0, 0));
        lblAuthorName.setOpaque(true);

        lblTimeStamp.setBackground(new java.awt.Color(255, 255, 255));
        lblTimeStamp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTimeStamp.setForeground(new java.awt.Color(0, 0, 0));
        lblTimeStamp.setOpaque(true);

        lblContentText.setBackground(new java.awt.Color(255, 255, 255));
        lblContentText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblContentText.setForeground(new java.awt.Color(0, 0, 0));
        lblContentText.setOpaque(true);

        lblContentPhoto.setBackground(new java.awt.Color(255, 255, 255));
        lblContentPhoto.setOpaque(true);

        btnDelete.setBackground(new java.awt.Color(255, 102, 102));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDelete.setText("Delete");

        btnEdit.setBackground(new java.awt.Color(92, 107, 192));
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEdit.setText("Edit");

        btnLike.setBackground(new java.awt.Color(255, 255, 255));
        btnLike.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLike.setForeground(new java.awt.Color(0, 0, 0));
        btnLike.setText("Like");

        btnAddComment.setBackground(new java.awt.Color(92, 107, 192));
        btnAddComment.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAddComment.setText("Add Comment");

        lblLikeCounter.setBackground(new java.awt.Color(255, 255, 255));
        lblLikeCounter.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblLikeCounter.setForeground(new java.awt.Color(0, 0, 0));
        lblLikeCounter.setOpaque(true);

        btnShowComments.setBackground(new java.awt.Color(92, 107, 192));
        btnShowComments.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnShowComments.setText("All comments");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAuthorName, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTimeStamp, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblContentText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblContentPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLike, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLikeCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddComment)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnShowComments)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTimeStamp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAuthorName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblContentText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblContentPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblLikeCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddComment, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLike, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShowComments, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddComment;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLike;
    private javax.swing.JButton btnShowComments;
    private javax.swing.JLabel lblAuthorName;
    private javax.swing.JLabel lblContentPhoto;
    private javax.swing.JLabel lblContentText;
    private javax.swing.JLabel lblLikeCounter;
    private javax.swing.JLabel lblTimeStamp;
    // End of variables declaration//GEN-END:variables

    private Post getPost() {
        return this.post;
    }
}

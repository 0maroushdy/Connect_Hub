/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Frontend.GroupPackage;

import Backend.ContentPackage.ContentDataBase;
import Backend.ContentPackage.Post;
import Backend.UserPackage.UserDatabase;
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
    
    private Post post;
    private ContentDataBase contentDatabase;
    private JPanel postsContainer;
    /**
     * Creates new form PostPanell
     */
    public PostPanell(Post post,JPanel postsContainer) {
        initComponents();
        this.post = post;
        this.postsContainer = postsContainer;
       // setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.contentDatabase = ContentDataBase.getInstance();
        initCustomComponents();
    }
    
     private void initCustomComponents(){
        
        lblAuthorName.setText(UserDatabase.getInstance().getUser(post.getAuthorId()).getUsername());
        lblTimeStamp.setText(post.getTimestamp());
        lblContentText.setText(post.getText());
        addPostPhoto();
        
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
        btnReact = new javax.swing.JButton();
        btnComment = new javax.swing.JButton();
        lblReactionCounter = new javax.swing.JLabel();
        lblCommentCounter = new javax.swing.JLabel();

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

        btnReact.setBackground(new java.awt.Color(92, 107, 192));
        btnReact.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnReact.setText("React");

        btnComment.setBackground(new java.awt.Color(92, 107, 192));
        btnComment.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnComment.setText("Comment");

        lblReactionCounter.setBackground(new java.awt.Color(255, 255, 255));
        lblReactionCounter.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblReactionCounter.setForeground(new java.awt.Color(0, 0, 0));
        lblReactionCounter.setOpaque(true);

        lblCommentCounter.setBackground(new java.awt.Color(255, 255, 255));
        lblCommentCounter.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCommentCounter.setForeground(new java.awt.Color(0, 0, 0));
        lblCommentCounter.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAuthorName, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTimeStamp, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblContentText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblContentPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnReact, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                .addGap(10, 10, 10))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblReactionCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCommentCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnComment))))
                .addContainerGap())
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblReactionCounter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCommentCounter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComment, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReact, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComment;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReact;
    private javax.swing.JLabel lblAuthorName;
    private javax.swing.JLabel lblCommentCounter;
    private javax.swing.JLabel lblContentPhoto;
    private javax.swing.JLabel lblContentText;
    private javax.swing.JLabel lblReactionCounter;
    private javax.swing.JLabel lblTimeStamp;
    // End of variables declaration//GEN-END:variables

    private Post getPost() {
        return this.post;
    }
}
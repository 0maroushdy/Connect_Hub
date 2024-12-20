/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frontend.ContentPackage;

/**
 *
 * @author moustafa
 */
import javax.swing.*;
import java.awt.*;

public class testFrame2 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(testFrame2::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Frame setup
        JFrame frame = new JFrame("User Profile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setPreferredSize(new Dimension(600, 150));
        topPanel.setBackground(Color.BLACK);

        // Profile Picture Circle
        JLabel profilePicture = new JLabel();
        profilePicture.setBounds(20, 20, 100, 100);
        profilePicture.setOpaque(true);
        profilePicture.setBackground(Color.DARK_GRAY);
        profilePicture.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        topPanel.add(profilePicture);

        // Name, Status, FriendNum
        JLabel nameLabel = new JLabel("Name | Status | FriendNum");
        nameLabel.setBounds(140, 20, 400, 30);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(nameLabel);

        // Bio
        JLabel bioLabel = new JLabel("<html>Bio: fdgf ksdgjs fdsglkjsgs sk;fg sfdgljk slkjsdfg jsfgjlksf</html>");
        bioLabel.setBounds(140, 60, 400, 50);
        bioLabel.setForeground(Color.WHITE);
        bioLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(bioLabel);

        // Tab Panel
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.BLACK);
        tabbedPane.setForeground(Color.WHITE);

        JPanel postsPanel = new JPanel();
        postsPanel.setBackground(Color.BLACK);

        JPanel storiesPanel = new JPanel();
        storiesPanel.setBackground(Color.BLACK);

        JPanel friendListPanel = new JPanel();
        friendListPanel.setBackground(Color.BLACK);

        JPanel friendSuggestionPanel = new JPanel();
        friendSuggestionPanel.setBackground(Color.BLACK);

        tabbedPane.addTab("Posts", postsPanel);
        tabbedPane.addTab("Stories", storiesPanel);
        tabbedPane.addTab("Friend List", friendListPanel);
        tabbedPane.addTab("Friend Suggestion", friendSuggestionPanel);

        // Add Button
        JButton addButton = new JButton("Add");
        addButton.setBackground(Color.GRAY);
        addButton.setForeground(Color.WHITE);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.add(addButton);

        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);
        bottomPanel.add(backButton);

        // Assemble Main Panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Frame Assembly
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
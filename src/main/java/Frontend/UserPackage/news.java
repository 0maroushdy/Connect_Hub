package Frontend.UserPackage;

import Backend.UserPackage.User;
import Backend.UserPackage.FriendshipManagement;
import Backend.ContentPackage.Post;
import Backend.ContentPackage.Story;
import Backend.UserPackage.UserSignupSingleton;
import Backend.ContentPackage.ContentDataBase;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class news extends javax.swing.JFrame {

    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;

    public news() throws IOException {
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();

        this.setTitle("Newsfeed");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Load the image as a BufferedImage
        BufferedImage image = ImageIO.read(new File("C:\\New folder\\news\\src\\main\\resources\\Main_bg.png"));

        // Create an ImageIcon from the BufferedImage
        ImageIcon icon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        panel1.setBackground(Color.LIGHT_GRAY);
        panel2.setBackground(Color.LIGHT_GRAY);
        panel3.setBackground(Color.LIGHT_GRAY);
        panel4.setBackground(Color.LIGHT_GRAY);
        panel5.setBackground(Color.LIGHT_GRAY);

        // Set BoxLayout for the panels to arrange components vertically
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));

        // Load content
        refreshContent();

        // Wrap panels with JScrollPane to make them scrollable
        JScrollPane scrollPane1 = new JScrollPane(panel1);
        JScrollPane scrollPane2 = new JScrollPane(panel2);
        JScrollPane scrollPane3 = new JScrollPane(panel3);
        JScrollPane scrollPane4 = new JScrollPane(panel4);
        JScrollPane scrollPane5 = new JScrollPane(panel5);

        // Add the tabs with the ImageIcon
        tabbedPane.addTab("Friend List", icon, scrollPane1, "shank7 foo");
        tabbedPane.addTab("Friend Suggestions", icon, scrollPane3, "shank7 baz");
        tabbedPane.addTab("Content Creation Area", icon, scrollPane4, "shank7 bar");
        tabbedPane.addTab("Posts", icon, scrollPane2, "shank7 bar");
        tabbedPane.addTab("Stories", icon, scrollPane5, "Shank7 foo");

        // Set fixed width for the tabs
        tabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
            @Override
            protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
                return 156; // Fixed width for tabs
            }
        });

        this.add(tabbedPane, BorderLayout.CENTER);

        // Create a JPanel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Set horizontal and vertical gaps

        // Create and style the buttons
        JButton button1 = createStyledButton("Button 1");
        JButton refreshButton = createStyledButton("Refresh");
        JButton button3 = createStyledButton("Button 3");

        // Add action listeners to the buttons
        button1.addActionListener(e -> JOptionPane.showMessageDialog(this, "Button 1 clicked!"));
        refreshButton.addActionListener(e -> refreshContent());
        button3.addActionListener(e -> JOptionPane.showMessageDialog(this, "Button 3 clicked!"));

        // Add buttons to the panel
        buttonPanel.add(button1);
        buttonPanel.add(refreshButton);
        buttonPanel.add(button3);

        buttonPanel.setBackground(new Color(10, 49, 86));

        // Add the button panel to the south of the frame
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);

    }

    private JPanel postComp(Post post) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding

        // Create labels for the author name and timestamp
        JLabel authorLabel = new JLabel(post.getAuthor().getUsername());
        JLabel timestampLabel = new JLabel(post.getTimestamp());

        // Create a panel for the top section to hold the author name and timestamp
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(authorLabel, BorderLayout.WEST);
        topPanel.add(timestampLabel, BorderLayout.EAST);

        // Create a label for the post text
        JLabel textLabel = new JLabel(post.getText());
        textLabel.setVerticalAlignment(SwingConstants.TOP); // Align text to the top

        // Load the image for the post
        JLabel imageLabel = new JLabel();
        try {
            BufferedImage image = ImageIO.read(new File(post.getImagePath()));
            imageLabel.setIcon(new ImageIcon(image));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the image
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add components to the panel
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(textLabel, BorderLayout.CENTER);
        panel.add(imageLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel sroryComp(Story story) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding

        // Create labels for the author name and timestamp
        JLabel authorLabel = new JLabel(story.getAuthor().getUsername());
        JLabel timestampLabel = new JLabel(story.getTimestamp());

        // Create a panel for the top section to hold the author name and timestamp
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(authorLabel, BorderLayout.WEST);
        topPanel.add(timestampLabel, BorderLayout.EAST);

        // Create a label for the post text
        JLabel textLabel = new JLabel(story.getText());
        textLabel.setVerticalAlignment(SwingConstants.TOP); // Align text to the top

        // Load the image for the post
        JLabel imageLabel = new JLabel();
        try {
            BufferedImage image = ImageIO.read(new File(story.getImagePath()));
            imageLabel.setIcon(new ImageIcon(image));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the image
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add components to the panel
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(textLabel, BorderLayout.CENTER);
        panel.add(imageLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel friendComp(User friend) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Load the user's image and scale it to 50x50 dimensions
        JLabel imageLabel = new JLabel();
        try {
            BufferedImage image = ImageIO.read(new File("C:\\New folder\\news\\src\\main\\resources\\Main_bg.png"));
            Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create labels for the username and status
        JLabel usernameLabel = new JLabel(""); // Assuming the User class has a getUsername() method
        JLabel statusLabel = new JLabel(""); // Assuming the User class has a getStatus() method

        // Set the layout and add components
        panel.setLayout(new BorderLayout());
        panel.add(imageLabel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(usernameLabel);
        panel.add(centerPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(statusLabel);
        panel.add(rightPanel, BorderLayout.EAST);

        return panel;
    }

    private void refreshContent() {
        panel1.removeAll();
        panel2.removeAll();
        panel3.removeAll();
        panel4.removeAll();
        panel5.removeAll();

        for (Post post : ContentDataBase.getInstance().getFriendsPosts(UserSignupSingleton.getInstance().getUser())) {
            JPanel component = postComp(post);
            panel2.add(component);
        }
        for (Story story : ContentDataBase.getInstance().getFriendsStories(UserSignupSingleton.getInstance().getUser())) {
            JPanel component = sroryComp(story);
            panel5.add(component);
        }
//        for (User friend : UserSignupSingleton.getInstance().getUser().getUserFriends()) {
//            JPanel component = friendComp(friend);
//            panel1.add(component);
//        }
//        for (User friend : FriendshipManagement.FriendshipManagementFactory.create().suggestFriends(UserSignupSingleton.getInstance().getUser())) {
//            JPanel component = friendComp(friend);
//            panel3.add(component);
//        }

        panel1.revalidate();
        panel2.revalidate();
        panel3.revalidate();
        panel4.revalidate();
        panel5.revalidate();

        panel1.repaint();
        panel2.repaint();
        panel3.repaint();
        panel4.repaint();
        panel5.repaint();
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(84, 110, 122)); // Set background color (SteelBlue)
        button.setForeground(Color.WHITE); // Set text color
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Set font
        button.setBorder(new EmptyBorder(10, 60, 10, 60)); // Add padding
        return button;
    }

    public static void main(String[] args) throws IOException {
        new news();
    }
}

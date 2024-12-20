package Frontend.UserPackage;

import Frontend.ContentPackage.CreateContentP;
import Frontend.UserPackage.FriendsGui;
import Backend.UserPackage.User;
import Backend.UserPackage.FriendshipManagement;
import Backend.ContentPackage.Post;
import Backend.ContentPackage.Story;
import Backend.UserPackage.UserSignupSingleton;
import Backend.ContentPackage.ContentDataBase;
import Backend.GroupPackage.Group;
import Backend.GroupPackage.GroupDatabase;
import Backend.UserPackage.UserDatabase;
import Backend.UserProfilePackage.overSizeInputException;
import Files.FILEPATHS;
import static Files.FILEPATHS.USERFILE;
import Frontend.GroupPackage.CreateGroup;
import Frontend.NotificationPackage.NotiForm;
import Frontend.SearchPackage.SearchFrame;
import Frontend.profilePackage.ProfileManagmentForm;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;

public class News extends javax.swing.JFrame {

    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel7;

    public News() throws IOException {
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        panel7= new JPanel();
        
        
        this.setTitle("Newsfeed");
        this.setSize(1100,800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Load the image as a BufferedImage
        BufferedImage image = ImageIO.read(new File(FILEPATHS.ROCKLY));

        // Create an ImageIcon from the BufferedImage
        ImageIcon icon = new ImageIcon(image.getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        panel1.setBackground(Color.LIGHT_GRAY);
        panel2.setBackground(Color.LIGHT_GRAY);
        panel3.setBackground(Color.LIGHT_GRAY);
        panel4.setBackground(Color.LIGHT_GRAY);
        panel5.setBackground(Color.LIGHT_GRAY);
        panel6.setBackground(Color.LIGHT_GRAY);
        panel7.setBackground(Color.LIGHT_GRAY);

        // Set BoxLayout for the panels to arrange components vertically
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
        panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));
        panel7.setLayout(new BoxLayout(panel7, BoxLayout.Y_AXIS));
        // Load content
        refreshContent();
        

        // Wrap panels with JScrollPane to make them scrollable
        JScrollPane scrollPane1 = new JScrollPane(panel1);
        JScrollPane scrollPane2 = new JScrollPane(panel2);
        JScrollPane scrollPane3 = new JScrollPane(panel3);
        JScrollPane scrollPane4 = new JScrollPane(panel4);
        JScrollPane scrollPane5 = new JScrollPane(panel5);
        JScrollPane scrollPane6 = new JScrollPane(panel6);
        JScrollPane scrollPane7 = new JScrollPane(panel7);

        // Add the tabs with the ImageIcon
        tabbedPane.addTab("Friend List", icon, scrollPane1, "shank7 foo");
        tabbedPane.addTab("Friend Suggestions", icon, scrollPane3, "shank7 baz");
        tabbedPane.addTab("Content Creation Area", icon, scrollPane4, "shank7 bar");
        tabbedPane.addTab("Posts", icon, scrollPane2, "shank7 bar");
        tabbedPane.addTab("Stories", icon, scrollPane5, "Shank7 foo");
        tabbedPane.addTab("Group Suggestions", icon, scrollPane6,"shank");
        tabbedPane.addTab("Joined Groups", icon, scrollPane7,"shank3");

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
        JButton button1 = createStyledButton("Logout");
        JButton searchButton = createStyledButton("SearchEngine");
        JButton refreshButton = createStyledButton("refreshEngine");
        JButton button3 = createStyledButton("Manage Friends");
        JButton profileBtn = createStyledButton("profile");
        JButton createGroupBtn = createStyledButton("CreateGroup");
        JButton notificationBtn = createStyledButton("Notfications");
      

        // Add action listeners to the buttons
        button1.addActionListener(e -> systemLogout());
       // searchButton.addActionListener(e -> SwingUtilities.invokeLater(() -> refreshContent()));
       searchButton.addActionListener(e -> searchMethod());
        button3.addActionListener(e -> friendsManage());
        profileBtn.addActionListener(e -> {
            try {
                profileBtnAction();
            } catch (overSizeInputException ex) {
                Logger.getLogger(News.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        createGroupBtn.addActionListener(e -> createGroup());
        refreshButton.addActionListener(e -> refresh());
        notificationBtn.addActionListener(e -> notificationAction());

        // Add buttons to the panel
       buttonPanel.add(button1);
       buttonPanel.add(profileBtn);
        buttonPanel.add(createGroupBtn);
        buttonPanel.add(searchButton);
        buttonPanel.add(button3);
        buttonPanel.add(refreshButton);
        buttonPanel.add(notificationBtn);
       // buttonPanel.add(profileBtn);
       
        
        buttonPanel.setBackground(new Color(10, 49, 86));

        // Add the button panel to the south of the frame
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);

    }

    private JPanel postComp(Post post) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding

        // Create labels for the author name and timestamp
        JLabel authorLabel = new JLabel(UserDatabase.getInstance().getUser(post.getAuthorId()).getUsername());
      //  System.out.println(post.getAuthor().getUsername());
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

    private JPanel storyComp(Story story) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding

        // Create labels for the author name and timestamp
        JLabel authorLabel = new JLabel(UserDatabase.getInstance().getUser(story.getAuthorId()).getUsername());
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
    
    
    private JPanel groupSuggestionsComp(Group group) {
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

    // Load the user's image and scale it to 50x50 dimensions
    JLabel imageLabel = new JLabel();
    try {
        BufferedImage image = ImageIO.read(new File(group.getGroupPhoto()));
        Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Create labels for the username and status
    JLabel nameLabel = new JLabel(group.getGroupName()); 
    JLabel descriptionLabel = new JLabel(group.getGroupDescription());

    // Set constraints and add the image to the panel
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(0, 0, 0, 50); // Right padding for space between image and username
    panel.add(imageLabel, gbc);

    // Set constraints and add the username to the panel
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.insets = new Insets(0, 20, 0, 0); // Right padding for space between username and status
    panel.add(nameLabel, gbc);

    // Set constraints and add the status to the panel
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.insets = new Insets(0, 500, 0,0); // No additional padding
    panel.add(descriptionLabel, gbc);

    return panel;
}
    
    
 private JPanel groupsJoinedComp(Group group) {
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

    // Load the user's image and scale it to 50x50 dimensions
    JLabel imageLabel = new JLabel();
    try {
        BufferedImage image = ImageIO.read(new File(group.getGroupPhoto()));
        Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Create labels for the username and status
    JLabel nameLabel = new JLabel(group.getGroupName()); 
    JLabel descriptionLabel = new JLabel(group.getGroupDescription());

    // Set constraints and add the image to the panel
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(0, 0, 0, 50); // Right padding for space between image and username
    panel.add(imageLabel, gbc);

    // Set constraints and add the username to the panel
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.insets = new Insets(0, 20, 0, 0); // Right padding for space between username and status
    panel.add(nameLabel, gbc);

    // Set constraints and add the status to the panel
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.insets = new Insets(0, 500, 0,0); // No additional padding
    panel.add(descriptionLabel, gbc);

    return panel;
}   
    
    

private JPanel friendComp(User friend) {
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

    // Load the user's image and scale it to 50x50 dimensions
    JLabel imageLabel = new JLabel();
    try {
        BufferedImage image = ImageIO.read(new File(FILEPATHS.ROCKLY));
        Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Create labels for the username and status
    JLabel usernameLabel = new JLabel(friend.getUsername()); 
    JLabel statusLabel = new JLabel(friend.getUserStatus());

    // Set constraints and add the image to the panel
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(0, 0, 0, 50); // Right padding for space between image and username
    panel.add(imageLabel, gbc);

    // Set constraints and add the username to the panel
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.insets = new Insets(0, 20, 0, 0); // Right padding for space between username and status
    panel.add(usernameLabel, gbc);

    // Set constraints and add the status to the panel
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.insets = new Insets(0, 500, 0,0); // No additional padding
    panel.add(statusLabel, gbc);

    return panel;
}
      /*  Move to backend */
    private void refreshContent() {
        panel1.removeAll();
        panel2.removeAll();
        panel3.removeAll();
        panel4.removeAll();
        panel5.removeAll();
        panel6.removeAll();
        panel7.removeAll();
        
        ContentDataBase.getInstance().update();  
        UserDatabase.getInstance().saveUsersToFile(USERFILE);
        UserDatabase.getInstance().loadUsersFromFile(USERFILE);
       // UserDatabase.getInstance().reloadUsersFromFile(USERFILE);
        
      JPanel friendListPanel = new JPanel();
    friendListPanel.setLayout(new BoxLayout(friendListPanel, BoxLayout.Y_AXIS));
    
    JPanel postsPanel = new JPanel();
    postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
    
    JPanel storiesPanel = new JPanel();
    storiesPanel.setLayout(new BoxLayout(storiesPanel, BoxLayout.Y_AXIS));
    
    JPanel suggestionsPanel = new JPanel();
    suggestionsPanel.setLayout(new BoxLayout(suggestionsPanel, BoxLayout.Y_AXIS));
    
    JPanel groupSuggestionsPanel = new JPanel();
    groupSuggestionsPanel.setLayout(new BoxLayout(groupSuggestionsPanel, BoxLayout.Y_AXIS));
    
    JPanel joinedGroupsPanel = new JPanel();
    joinedGroupsPanel.setLayout(new BoxLayout(joinedGroupsPanel, BoxLayout.Y_AXIS));

    for (Post post : ContentDataBase.Query.getFriendsPosts(UserSignupSingleton.getInstance().getUser())) {
        if(post.getGroupId()==null || post.getGroupId().length()==0){
        JPanel component = postComp(post);
        postsPanel.add(component, 0);} // Add to the top
    }
    for (Story story : ContentDataBase.Query.getFriendsStories(UserSignupSingleton.getInstance().getUser())) {
        JPanel component = storyComp(story);
        storiesPanel.add(component, 0); // Add to the top
    }
    for (User friend : UserDatabase.getInstance().getUsers()) {
        if(UserSignupSingleton.getInstance().getUser().getUserFriends().contains(friend.getUserId())){
        JPanel component = friendComp(friend);
        component.setBackground(Color.white);
         Dimension minimumSize = new Dimension(800, 50); component.setMinimumSize(minimumSize);
        Dimension maximumSize = new Dimension(800, 50); component.setMaximumSize(maximumSize);
        friendListPanel.add(component, 0); // Add to the top
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        component.setBorder(lineBorder);}
    }
    for (User friend : FriendshipManagement.FriendshipManagementFactory.create().suggestFriends(UserSignupSingleton.getInstance().getUser())) {
        JPanel component = friendComp(friend);
        component.setBackground(Color.white);
        Dimension minimumSize = new Dimension(800, 50); component.setMinimumSize(minimumSize);
        Dimension maximumSize = new Dimension(800, 50); component.setMaximumSize(maximumSize);
        suggestionsPanel.add(component, 0); // Add to the top
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        component.setBorder(lineBorder);
    }
    for (Group group : GroupDatabase.getInstance().getGroups()) {
        if(!UserSignupSingleton.getInstance().getUser().checkIfInJoinedGroups(group)){
        JPanel component = groupSuggestionsComp(group);
        component.setBackground(Color.white);
        Dimension minimumSize = new Dimension(800, 50); component.setMinimumSize(minimumSize);
        Dimension maximumSize = new Dimension(800, 50); component.setMaximumSize(maximumSize);
        groupSuggestionsPanel.add(component, 0); // Add to the top
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        component.setBorder(lineBorder);}
    }
    for (Group group : GroupDatabase.getInstance().getGroups()) {
        if(UserSignupSingleton.getInstance().getUser().checkIfInJoinedGroups(group)){
        JPanel component = groupSuggestionsComp(group);
        component.setBackground(Color.white);
        Dimension minimumSize = new Dimension(800, 50); component.setMinimumSize(minimumSize);
        Dimension maximumSize = new Dimension(800, 50); component.setMaximumSize(maximumSize);
        joinedGroupsPanel.add(component, 0); // Add to the top
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        component.setBorder(lineBorder);}
    }
    
    panel1.add(friendListPanel);
    panel2.add(postsPanel);
    panel3.add(suggestionsPanel);
    panel4.add(new CreateContentP());
    panel5.add(storiesPanel);
    panel6.add(groupSuggestionsPanel);
    panel7.add(joinedGroupsPanel);
    
        panel1.revalidate();
        panel2.revalidate();
        panel3.revalidate();
        panel4.revalidate();
        panel5.revalidate();
        panel6.revalidate();
        panel7.revalidate();

        panel1.repaint();
        panel2.repaint();
        panel3.repaint();
        panel4.repaint();
        panel5.repaint();
        panel6.repaint();
        panel7.repaint();
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(84, 110, 122)); // Set background color (SteelBlue)
        button.setForeground(Color.WHITE); // Set text color
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Set font
        button.setBorder(new EmptyBorder(10, 15, 10, 15)); // Add padding
        return button;
    }
    
    private void systemLogout(){
       // UserDatabase.getInstance().loadUsersFromFile(USERFILE);
        UserSignupSingleton.getInstance().getUser().userLogout();
        this.dispose();
        new WelcomeFrame();
    }
    
    private void friendsManage(){
        //UserDatabase.getInstance().loadUsersFromFile(USERFILE);
        FriendsGui friendsGui = new FriendsGui();
        friendsGui.setVisible(true);
    }
    
    private void profileManage(){
        ProfileManagmentForm profileForm = new ProfileManagmentForm();
        profileForm.setVisible(true);
    }
    
      private void profileBtnAction() throws overSizeInputException { 
        this.dispose();
        new ProfileManagmentForm().setVisible(true);

    } 
      
    private void searchMethod(){
        SearchFrame searchFrame = new SearchFrame();
        searchFrame.setVisible(true);
    }
    
    private void createGroup(){
        CreateGroup create = new CreateGroup();
        create.setVisible(true);
    }
    
    private void refresh(){
        UserDatabase.getInstance().loadUsersFromFile(USERFILE);
    }
    
    public void notificationAction(){
//        this.setVisible(false);
      //  this.dispose();
      //  new NotiForm();
        NotiForm form = new NotiForm();
        form.setVisible(true);
        
    }
    

    public static void main(String[] args) throws IOException {
        new News();
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frontend.ContentPackage;

/**
 *
 * @author moustafa
 */
import Backend.UserPackage.UserSignupSingleton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class testFrame extends JFrame {

    public testFrame() {
        UserSignupSingleton.getInstance().setUser(new Backend.UserPackage.User());
        
        setTitle("Content Frame");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CreateContentP contentPanel = new CreateContentP();

        add(contentPanel);

        pack();

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new testFrame();
        });
    }
}


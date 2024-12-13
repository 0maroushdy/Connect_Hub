/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.UserPackage;

import static Files.FILEPATHS.USERFILE;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

/**
 *
 * @author Abdelrahman
 */
public class UserSignupSingleton {

    private static UserSignupSingleton userInstance;
    private User user;

    private UserSignupSingleton() {
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static UserSignupSingleton getInstance() {
        if (userInstance == null) {
            synchronized (UserSignupSingleton.class) {
                if (userInstance == null) {
                    userInstance = new UserSignupSingleton();
                }
            }
        }
        return userInstance;
    }

    public boolean userSignup(String email, String username, String password, LocalDate dateOfBirth) throws NoSuchAlgorithmException {

        if (!ValidationUtil.validateUserInput(email, username, password, dateOfBirth)) {
            return false;
        }

        if (!ValidationUtil.validateUserEmail(email)) {
            return false;
        }

        user = User.UserFactory.create(null,email, username, password, dateOfBirth, "online",true);
        UserDatabase.getInstance().addUser(user);
        UserDatabase.getInstance().saveUsersToFile();
        this.setUser(user);
       // System.out.println(UserDatabase.getInstance().getUniqueCounter());
        return true;

    }
}

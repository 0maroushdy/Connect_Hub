/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.UserPackage;

/**
 *
 * @author moustafa
 */
public class MainUser {

    private static User mainUser;

    public static void setMainUser(User mainUser) {
        if (MainUser.mainUser == null) {
            MainUser.mainUser = mainUser;
        } else {
            throw new UnsupportedOperationException("Attribute has already been set and cannot be changed");
        }

    }

    public static User getMainUser() {
        return mainUser;
    }

}

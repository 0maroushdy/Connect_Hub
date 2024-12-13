package Backend.UserPackage;

import Backend.ContentPackage.JSONUtils;
import static Files.FILEPATHS.USERFILE;
import java.util.ArrayList;
import org.json.*;
import java.io.IOException;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.AuthenticationException;

/**
 *
 * @author Abdelrahman
 */
public final class UserDatabase {

    private static UserDatabase user_database;
    private TreeSet<User> users;

    private UserDatabase() {
        this.users = new TreeSet<>();
    }

// <editor-fold defaultstate="collapsed" desc="Geters">
    public static synchronized UserDatabase getInstance() {
        if (user_database == null) {
            user_database = new UserDatabase();

        }
        return user_database;
    }

    public TreeSet<User> getUsers() {
        return this.users;
    }

    public ArrayList<User> getUsersByUsername(String username) {
        ArrayList<User> ans = new ArrayList<>();
        for (User user : this.users) {
            if (user.getUsername().equals(username)) {
                ans.add(user);
            }
        }
        return ans;
    }

    public User getUserByEmail(String userEmail) {
        for (User user : this.users) {
            if (user.getUserEmail().equals(userEmail)) {
                return user;
            }
        }
        return null;
    }

    public User getUser(String userId) {
        for (User user : this.users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
// </editor-fold>

    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can't be null");
        }
        this.users.add(user);
    }

    public void userLogin(String email, String password) throws AuthenticationException {
        User user = UserDatabase.getInstance().getUserByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User can't be null");
        }

        if (!user.getUserPassword().equals(HashingUtil.generateUserHashedPassword(password))) {
            throw new AuthenticationException("Password incorrect");
        }

        user.setUserStatus("online");
        UserSignupSingleton.getInstance().setUser(user);
        saveUsersToFile();
    }

    public void saveUsersToFile() {
        JSONArray jsonArray = new JSONArray();
        for(User user : this.users){
            jsonArray.put(user.toJSON());
        }

        try {
            JSONUtils.writeToFile(USERFILE, jsonArray);
        } catch (IOException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadUsersFromFile(String filePath) {
        try {
//            GroupDataBase.getInstance().getGroups().removeAll(GroupDatabase.getInstance().getGroups());
            
            JSONArray jsonArray = JSONUtils.readFromFile(USERFILE);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                User user = User.fromJson(jsonObject);
                this.addUser(user);
            }
        } catch (IOException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

package Backend.UserPackage;

import Backend.ContentPackage.JSONUtils;
import Backend.UserProfilePackage.UserProfile;
import GroupPackage.Group;
import GroupPackage.GroupDataBase;
import java.util.Set;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;
import org.json.*;

/**
 *
 * @author Abdelrahman
 */
public class User implements Comparable<User> {

    private UUID userId;
    private String email;
    private String username;
    private String password;
    private String dateOfBirth;
    private String status;

    private UserProfile profile; // adding userProfile attribute --> making composition

    private Set<UUID> friends;
    private Set<UUID> blockedUsers;
    private Set<FriendRequest> sentFriendRequests;
    private Set<FriendRequest> receivedFriendRequests;

    private Set<Group> userJoinedGroups;

// <editor-fold defaultstate="collapsed" desc="Constructors">
    private User(UUID userId, String email, String username, String password, LocalDate dateOfBirth, String status) {
        if (userId != null) {
            this.userId = userId;
        } else {
            this.userId = UUID.randomUUID();
        }

        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth.toString();
        this.status = status;
        this.friends = new HashSet<>();
        this.blockedUsers = new HashSet<>();
        this.sentFriendRequests = new HashSet<>();
        this.receivedFriendRequests = new HashSet<>();
        this.profile = new UserProfile();
    }

    private User(UUID userId, String email, String username, String password, LocalDate dateOfBirth, String status, UserProfile profile) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth.toString();
        this.status = status;
        this.friends = new HashSet<>();
        this.blockedUsers = new HashSet<>();
        this.sentFriendRequests = new HashSet<>();
        this.receivedFriendRequests = new HashSet<>();
        this.profile = profile;
        this.userJoinedGroups = new HashSet<>();
    }

    private User(UUID userId, String email, String username,
            String password, boolean passwordAlreadyHashed,
            LocalDate dateOfBirth, String status, UserProfile profile,
            Set<UUID> friends,
            Set<UUID> blockedUsers,
            Set<FriendRequest> sentFriendRequests,
            Set<FriendRequest> receivedFriendRequests,
            Set<Group> userJoinedGroups) {

        this.userId = userId;
        this.email = email;
        this.username = username;

        if (passwordAlreadyHashed) {
            this.password = password;
        } else {
            this.password = HashingUtil.generateUserHashedPassword(password);
        }

        this.dateOfBirth = dateOfBirth.toString();
        this.status = status;

        this.friends = (friends == null) ? new HashSet<>() : friends;
        this.blockedUsers = (blockedUsers == null) ? new HashSet<>() : blockedUsers;
        this.sentFriendRequests = (sentFriendRequests == null) ? new HashSet<>() : sentFriendRequests;
        this.receivedFriendRequests = (receivedFriendRequests == null) ? new HashSet<>() : receivedFriendRequests;
        this.receivedFriendRequests = (receivedFriendRequests == null) ? new HashSet<>() : receivedFriendRequests;
        
        this.profile = profile;
        
        this.userJoinedGroups = (userJoinedGroups == null) ? new HashSet<>() : userJoinedGroups;
    }

    public User() {
        this.friends = new HashSet<>();
        this.blockedUsers = new HashSet<>();
        this.sentFriendRequests = new HashSet<>();
        this.receivedFriendRequests = new HashSet<>();
        this.profile = new UserProfile();
        this.userJoinedGroups = new HashSet<>();
    }

    public User(User.Builder builder) {
        this(builder.userId,
                builder.email,
                builder.username,
                builder.password,
                builder.passwordAlreadyHashed,
                builder.dateOfBirth,
                builder.status,
                builder.profile,
                builder.friends,
                builder.blockedUsers,
                builder.sentFriendRequests,
                builder.receivedFriendRequests,
                builder.userJoinedGroups);
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Getters">
    public UUID getUserId() {
        return this.userId;
    }

    public String getUserEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }

    public String getUserDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getUserStatus() {
        return this.status;
    }

    public String getUserPassword() {
        return this.password;
    }

    public Set<UUID> getUserFriends() {
        return this.friends;
    }

    public Set<UUID> getUserBlockedUsers() {
        return this.blockedUsers;
    }

    public Set<FriendRequest> getUserSentFriendRequests() {
        return this.sentFriendRequests;
    }

    public Set<FriendRequest> getUserReceivedFriendRequests() {
        return this.receivedFriendRequests;
    }

    public UserProfile getUserProfile() {
        return this.profile;
    }

    public Set<Group> getUserJoinedGroups() {
        return this.userJoinedGroups;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Setters">
    public void setUserPassword(String unHashedPassword) throws NoSuchAlgorithmException {
        this.password = HashingUtil.generateUserHashedPassword(unHashedPassword);
    }

    public void setUserPassword(String password, boolean wantToHash) throws NoSuchAlgorithmException {
        if (wantToHash == true) {
            this.password = HashingUtil.generateUserHashedPassword(password);
        } else {
            this.password = password;
        }
    }

    public void setUserDateOfBirth(LocalDate date) {
        this.dateOfBirth = date.toString();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserStatus(String status) {
        this.status = status;
    }

    public boolean setUserEmail(String email) {
        if (ValidationUtil.validateUserEmail(email)) {
            this.email = email;
        } else {
            return false;
        }

        return true;
    }

    public void setStatusAcceptedFriendRequest(FriendRequest request) {
        for (FriendRequest requestt : this.sentFriendRequests) {
            if (requestt.getRequestSenderId().equals(request.getRequestSenderId()) && requestt.getRequestReceiverId().equals(request.getRequestReceiverId())) {
                requestt.setRequestStatus(FriendRequest.Status.Accepted);
            }

        }
    }

    public void setStatusDeclinedFriendRequest(FriendRequest request) {
        for (FriendRequest requestt : this.sentFriendRequests) {
            if (requestt.getRequestSenderId().equals(request.getRequestSenderId()) && requestt.getRequestReceiverId().equals(request.getRequestReceiverId())) {
                requestt.setRequestStatus(FriendRequest.Status.Declined);
            }

        }
    }

    public void setUserJoinedGroups() {
        this.userJoinedGroups = new HashSet<>();
    }
// </editor-fold>

    public void userLogout() {
        UserSignupSingleton.getInstance().getUser().setUserStatus("offline");
        UserDatabase.getInstance().saveUsersToFile();
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("UserId", this.userId);
        jsonObject.put("Username", this.username);
        jsonObject.put("Email", this.email);
        jsonObject.put("Password", this.password);
        jsonObject.put("Status", this.status);
        jsonObject.put("DateOfBirth", this.dateOfBirth);
        jsonObject.put("Profile", this.profile.toJSON());
        jsonObject.put("Friends", new JSONArray(this.friends));
        jsonObject.put("BlockedUsers", new JSONArray(this.blockedUsers));
        jsonObject.put("SentFriendRequests", new JSONArray(this.sentFriendRequests));
        jsonObject.put("ReceivedFriendRequests", new JSONArray(this.receivedFriendRequests));
        jsonObject.put("userJoinedGroups", new JSONArray(this.userJoinedGroups));

        return jsonObject;
    }

    public static User fromJson(JSONObject jsonObject) {
        User user = new User();
        user.dateOfBirth = jsonObject.getString("DateOfBirth");
        user.status = jsonObject.getString("Status");
        user.email = jsonObject.getString("Email");
        user.username = jsonObject.getString("Username");
        user.userId = UUID.fromString(jsonObject.getString("UserId"));
        user.password = jsonObject.getString("Password");

        //#############################################################################################
        // user.date = LocalDate.parse(dateOfBirth, DateTimeFormatter.ISO_LOCAL_DATE);
        JSONObject profileObj = jsonObject.getJSONObject("Profile");
        user.profile = new UserProfile(profileObj.getString("profilePhoto"),
                profileObj.getString("profileCover"),
                profileObj.getString("bio"));
        //#############################################################################################

        user.friends = JSONUtils.loadCollection(jsonObject, "Friends", HashSet::new)
                .stream()
                .map(UUID::fromString)
                .collect(Collectors.toCollection(HashSet::new));

        user.blockedUsers = JSONUtils.loadCollection(jsonObject, "BlockedUsers", HashSet::new)
                .stream()
                .map(UUID::fromString)
                .collect(Collectors.toCollection(HashSet::new));
//#############################################################################################
        JSONArray sentRequestsArray = jsonObject.getJSONArray("SentFriendRequests");
        for (int i = 0; i < sentRequestsArray.length(); i++) {
            JSONObject requestJson = sentRequestsArray.getJSONObject(i);
            FriendRequest request = FriendRequest.fromJson(requestJson);
            if (request != null) { // Ensure the deserialization didn't fail
                user.sentFriendRequests.add(request);
            } else {
                System.err.println("Warning: Failed to deserialize sent friend request at index " + i);
            }
        }

        // Deserialize received friend requests
        // user.receivedFriendRequests = new HashSet<>();
        JSONArray receivedRequestsArray = jsonObject.getJSONArray("ReceivedFriendRequests");
        for (int i = 0; i < receivedRequestsArray.length(); i++) {
            JSONObject requestJson = receivedRequestsArray.getJSONObject(i);
            FriendRequest request = FriendRequest.fromJson(requestJson);
            if (request != null) { // Ensure the deserialization didn't fail
                user.receivedFriendRequests.add(request);
            } else {
                System.err.println("Warning: Failed to deserialize received friend request at index " + i);
            }
        }

        JSONArray groupsArray = jsonObject.getJSONArray("userJoinedGroups");
        for (int i = 0; i < groupsArray.length(); i++) {
            JSONObject groupJson = groupsArray.getJSONObject(i);
            Group group = Group.fromJSON(groupJson);
            if (group != null) { // Ensure the deserialization didn't fail
                user.userJoinedGroups.add(group);
//                if (!GroupDataBase.getInstance().checkIfGroupExists(group)) {
//                    GroupDataBase.getInstance().addGroup(group);
//                }
            } else {
                System.err.println("Warning: Failed to deserialize received friend request at index " + i);
            }
        }
        //#############################################################################################
        return user;
    }

    public boolean isUserBlocked(User user) {
        return this.blockedUsers.contains(user);
    }

    /*  public ArrayList <User> suggestFriends(){
        ArrayList <User> suggestions = new ArrayList<>();
        for(User user:UserDatabase.getInstance().getUsers()){
             System.out.println(user.userToString());
            if(!this.getUserFriends().contains(user) && !this.getUserBlockedUsers().contains(user) && !this.getUserId().equals(user.getUserId())) suggestions.add(user);
          //  System.out.println(differentUser.getUserId());
        }
        return suggestions;
    } */
    @Override
    public String toString() {
        String ans = "";
        ans += this.userId;
        ans += " ";
        ans += this.username;
        ans += " ";
        ans += this.email;
        ans += " ";
        ans += this.password;
        ans += " ";
        ans += this.dateOfBirth;
        ans += " ";
        ans += this.status;
        return ans;
    }

    @Override
    public int compareTo(User o) {
        return this.userId.compareTo(o.userId);
    }

    public static class UserFactory {

        public static User create(UUID userId, String email, String username, String password, LocalDate dateOfBirth, String status, boolean wanttohash) throws NoSuchAlgorithmException {
            String hashedPassword = HashingUtil.generateUserHashedPassword(password);
            User user = new User(userId, email, username, hashedPassword, dateOfBirth, status);
            user.setUserPassword(password, wanttohash);
            return user;
        }

        public static User create(UUID userId, String email, String username, String password, LocalDate dateOfBirth, String status, UserProfile profile, boolean wanttohash) throws NoSuchAlgorithmException {
            String hashedPassword = HashingUtil.generateUserHashedPassword(password);
            User user = new User(userId, email, username, hashedPassword, dateOfBirth, status, profile);
            user.setUserPassword(password, wanttohash);
            return user;
        }

    }

    public static class Builder {

        private UUID userId;
        private String email;
        private String username;
        private String password;
        private boolean passwordAlreadyHashed;

        private LocalDate dateOfBirth;
        private String status;

        private UserProfile profile; // adding userProfile attribute --> making composition

        private Set<UUID> friends;
        private Set<UUID> blockedUsers;
        private Set<FriendRequest> sentFriendRequests;
        private Set<FriendRequest> receivedFriendRequests;

        private Set<Group> userJoinedGroups;

        public Builder(String email, String password, String username, LocalDate dateOfBirth) {
            if (email == null || email.isEmpty()
                    || password == null || password.isEmpty()
                    || username == null || username.isEmpty()
                    || dateOfBirth == null) {
                throw new IllegalArgumentException("email, username, password and dateOfBirth cannot be null.");
            }

            this.email = email;
            this.password = password;
            this.username = username;
            this.dateOfBirth = dateOfBirth;

            //default values
            this.passwordAlreadyHashed = false;
            //
        }

        public Builder setUserId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setProfile(UserProfile profile) {
            this.profile = profile;
            return this;
        }

        public Builder setFriends(Set<UUID> friends) {
            this.friends = friends;
            return this;
        }

        public Builder setBlockedUsers(Set<UUID> blockedUsers) {
            this.blockedUsers = blockedUsers;
            return this;
        }

        public Builder setSentFriendRequests(Set<FriendRequest> sentFriendRequests) {
            this.sentFriendRequests = sentFriendRequests;
            return this;
        }

        public Builder setReceivedFriendRequests(Set<FriendRequest> receivedFriendRequests) {
            this.receivedFriendRequests = receivedFriendRequests;
            return this;
        }

        public Builder setUserJoinedGroups(Set<Group> userJoinedGroups) {
            this.userJoinedGroups = userJoinedGroups;
            return this;
        }

        public Builder passwordIsAlreadyHashed() {
            this.passwordAlreadyHashed = true;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}

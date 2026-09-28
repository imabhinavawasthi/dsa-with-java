package service;

import model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private final Map<Integer, User> userMap;

    public UserService() {
        this.userMap = new HashMap<>();
    }

    public void registerUser(User user) {
        userMap.put(user.getId(), user);
    }

    public User getUser(int userId) {
        return userMap.get(userId);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    public boolean containsUser(int userId) {
        return userMap.containsKey(userId);
    }
}

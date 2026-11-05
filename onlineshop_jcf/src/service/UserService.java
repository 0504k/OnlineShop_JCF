package service;
import model.User;
import java.util.*;
public class UserService {
    private Set<User> users = new HashSet<>();
    private Map<Integer, User> usersById = new HashMap<>();

    public synchronized void addUser(User user) {
        if (user == null || usersById.containsKey(user.getId())) {
            throw new IllegalArgumentException("User already exists or is invalid.");
        }

        users.add(user);
        usersById.put(user.getId(), user);
    }

    public synchronized User getUserById(int id) {
        return usersById.get(id);
    }

    public synchronized boolean removeUser(int id) {
        User user = usersById.remove(id);

        if (user == null) {
            return false;
        }

        users.remove(user);
        return true;
    }

    public synchronized void updateUser(User user) {
        if (user == null || !usersById.containsKey(user.getId())) {
            throw new IllegalArgumentException("User not found.");
        }

        User oldUser = usersById.put(user.getId(), user);
        users.remove(oldUser);
        users.add(user);
    }

    public synchronized List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public synchronized int getUserCount() {
        return users.size();
    }}
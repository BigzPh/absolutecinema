/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package absolutecinema;

/**
 *
 * @author carlo
 */
import java.util.ArrayList;

public class Identification {
    private static ArrayList<User> users = new ArrayList<>();

    static {
        // Predefined users
        users.add(new User("user1", "12345", "User1"));
        users.add(new User("maloi", "saksesSiCamat", "Maloi Ricalde"));
        users.add(new User("h", "h", "h"));
    }
    public static boolean deleteUser(String username, String password) {
    for (User user : users) {
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            users.remove(user); // This deletes the entire user, including fullName
            return true;
        }
    }
    return false;
}

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}


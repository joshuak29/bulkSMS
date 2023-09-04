package com.bulksms.users;

import java.util.ArrayList;

public class UserDb {
    private static int userCount = 2;
    private static ArrayList<User> users = new ArrayList<>();

    static {
        users.add(new User("Rukundo Joshua", true, "josue", "password"));
        users.add(new User("Yangande Osee", false, "osee", "password"));
    }

    public static void addUser(User user) {
        users.add(user);
        userCount++;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User getUser(int id) {
        if(id >= users.size()) {
            return users.get(0);
        }
        return users.get(id);
    }

    public static User deleteUser(int id) {
        if(id >= users.size()) {
            return users.remove(id);
        }
        userCount--;
        return users.remove(id);
    }
}

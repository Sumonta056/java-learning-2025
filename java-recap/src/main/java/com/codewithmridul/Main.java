=package com.codewithmridul;

import java.util.ArrayList;
import java.util.List;

class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.email = email;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

public class Main {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("sumonta", "gg.com"));
        users.add(new User("sumonta", "gg.com"));
        users.add(new User("sumonta", "gg.com"));
        users.add(new User("sumonta", "gg.com"));

        for (User user : users) {
            System.out.println(user.getName());
        }

    }
}
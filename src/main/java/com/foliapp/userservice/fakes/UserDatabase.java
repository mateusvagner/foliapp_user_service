package com.foliapp.userservice.fakes;

import java.util.ArrayList;
import java.util.List;

import com.foliapp.userservice.domain.User;

public class UserDatabase {
    private static UserDatabase instance;

    // TODO Should be a list of UserData
    public List<User> users = new ArrayList<>();

    public static UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }
}

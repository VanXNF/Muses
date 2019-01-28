package com.victorxu.muses.db.service;

import com.victorxu.muses.Muses;
import com.victorxu.muses.db.entity.User;
import com.victorxu.muses.db.entity.UserDao;

public class UserService {

    public static void addUser(User user) {
        Muses.getDaoInstant().getUserDao().insert(user);
    }

    public static void addUser(String name, String token) {
        User user = new User();
        user.setName(name);
        user.setToken(token);
        addUser(user);
    }

    public static void updateUser(User user) {
        Muses.getDaoInstant().getUserDao().update(user);
    }

    public static void updateUserById(long id, String token) {
        User user = getUserById(id);
        user.setToken(token);
        updateUser(user);
    }

    public static void updateUserByName(String name, String token) {
        User user = getUserByName(name);
        user.setToken(token);
        updateUser(user);
    }

    public static void deleteUser(User user) {
        Muses.getDaoInstant().getUserDao().delete(user);
    }

    public static void deleteUserById(long id) {
        Muses.getDaoInstant().getUserDao().deleteByKey(id);
    }

    public static void deleteUserByName(String name) {
        deleteUser(getUserByName(name));
    }

    public static User getUserById(long id) {
        return Muses.getDaoInstant().getUserDao().load(id);
    }

    public static User getUserByName(String name) {
        return Muses.getDaoInstant().getUserDao().queryBuilder()
                .where(UserDao.Properties.Name.eq(name)).list().get(0);
    }
}

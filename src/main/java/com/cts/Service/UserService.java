package com.cts.Service;

import com.cts.Model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User updateUser(User user);

    List<User> getAllUsers();

    User getUserById(int id);

    void deleteUser(int id);
}

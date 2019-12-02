package com.h2rd.refactoring.usermanagement;

import java.util.List;

public interface UserDao {
    boolean saveUser(User user);

    List<User> getUsers();

    boolean deleteUser(User userToDelete);

    boolean updateUser(User userToUpdate);

    User findUser(String email);
}

package com.h2rd.refactoring.usermanagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements Serializable, UserDao {
    public List<User> users = new ArrayList<User>();
    private static UserDaoImpl userDao;
    public static UserDaoImpl getUserDao() {
        // lazy initialization
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }
    public boolean saveUser(User user) {
        if (user != null) {
            synchronized (this) {
                boolean flag=true;
                for(User u : users) {
                    if (u.equals(user)) {
                        flag = false;
                    }
                }
                if(flag) {
                    return users.add(user);
                }else {
                    return flag;
                }

            }
        }
        return false;

    }

    public List<User> getUsers() {
        return this.users;
    }

    public boolean deleteUser(User userToDelete) {
        if(userToDelete!=null) {
            for (User user : users) {
                if (user.equals(userToDelete)) {
                    return users.remove(user);
                }
            }
        }
        return false;
    }

    public boolean updateUser(User userToUpdate) {
        if(userToUpdate!=null) {
            synchronized (this) {
                boolean flag=false;
                for (User user : users) {
                    if (user.getEmail().equals(userToUpdate.getEmail())) {
                        user.setName(userToUpdate.getName());
                        user.setRoles(userToUpdate.getRoles());
                        flag =true;
                        return flag;
                    }
                }
            }
        }
        return false;
    }
    /*
     * through emial to find user
     * @see com.h2rd.refactoring.dao.UserDao#findUser(java.lang.String)
     */
    public User findUser(String email) {
        for (User user : users) {
            if (email.equals(user.getEmail())) {
                return user;
            }
        }
        return null;
    }

}

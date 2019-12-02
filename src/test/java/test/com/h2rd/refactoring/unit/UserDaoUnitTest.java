package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDaoImpl;

import org.junit.Test;
import org.junit.After;
import junit.framework.Assert;

import java.util.Arrays;
import java.util.List;

public class UserDaoUnitTest {
    UserDaoImpl userDao;

    @After
    public void clear() {
        if (userDao.users != null) {
            userDao.users.clear();
            ;
        }

    }

    public void createUser() {
        userDao = UserDaoImpl.getUserDao();
        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        userDao.saveUser(user);
    }

    @Test
    public void saveUserTest() {
        userDao = UserDaoImpl.getUserDao();
        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        Assert.assertEquals(true, userDao.saveUser(user));
        Assert.assertEquals(false, userDao.saveUser(null));

        createUser();
        User user2 = new User();
        user2.setName("Fake Name");
        user2.setEmail("fake@email.com");
        user2.setRoles(Arrays.asList("admin", "master"));
        Assert.assertFalse(userDao.saveUser(user2));
    }

    @Test
    public void getUsersTest() {
        userDao = UserDaoImpl.getUserDao();
        List<User> list = userDao.getUsers();
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void deleteUserTest() {
        createUser();
        userDao = UserDaoImpl.getUserDao();
        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
        Assert.assertEquals(true, userDao.deleteUser(user));

        User user2 = new User();
        user2.setName("Fake Name");
        user2.setEmail("real@email.com");
        user2.setRoles(Arrays.asList("admin", "master"));
        Assert.assertFalse(userDao.deleteUser(user2));
        Assert.assertFalse(userDao.deleteUser(null));

    }

    @Test
    public void updateUserTest() {
        createUser();
        userDao = UserDaoImpl.getUserDao();
        User user2 = new User();
        user2.setName("Real Name");
        user2.setEmail("fake@email.com");
        user2.setRoles(Arrays.asList("master"));
        Assert.assertEquals(true, userDao.updateUser(user2));

        User user3 = new User();
        user3.setName("Family Name");
        user3.setEmail("real@email.com");
        user3.setRoles(Arrays.asList("admin", "master"));
        Assert.assertEquals(false, userDao.updateUser(user3));
        Assert.assertFalse(userDao.updateUser(null));

    }

    @Test
    public void findUserTest() {
        createUser();
        userDao = UserDaoImpl.getUserDao();
        Assert.assertNotNull(userDao.findUser("fake@email.com"));
        Assert.assertNull(userDao.findUser("real@email.com"));
    }
}
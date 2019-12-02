package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.usermanagement.UserDaoImpl;
import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.web.UserResource;


import org.junit.After;
import org.junit.Test;
import junit.framework.Assert;

import java.util.Arrays;

import javax.ws.rs.core.Response;

public class UserResourceUnitTest {

    UserResource userResource;
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
    public void getUsersTest() {

        userResource = new UserResource();
        createUser();
        Response response = userResource.getUsers();
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void addUserTest() {
        userResource = new UserResource();
        Response response = userResource.addUser("fake user", "fake@user.com", Arrays.asList("admin", "master"));
        Assert.assertEquals(200, response.getStatus());
        Response response1 = userResource.addUser("", "fake@user.com", Arrays.asList("admin", "master"));
        Assert.assertEquals(400, response1.getStatus());
        Response response2 = userResource.addUser("fake user", "", Arrays.asList("admin", "master"));
        Assert.assertEquals(400, response2.getStatus());
        Response response3 = userResource.addUser("fake user", "fake@user.com", Arrays.asList(new String[] {}));
        Assert.assertEquals(400, response3.getStatus());
        Response response4 = userResource.addUser("fake user", "fake@user.com", Arrays.asList(new String[] { "" }));
        Assert.assertEquals(400, response4.getStatus());

        createUser();
        Response response5 = userResource.addUser("Fake Name", "fake@email.com", Arrays.asList("admin", "master"));
        Assert.assertEquals(409, response5.getStatus());
    }

    @Test
    public void updateUserTest() {
        userResource = new UserResource();
        Response response = userResource.updateUser("fake user", "fake@user.com", Arrays.asList("admin", "master"));
        Assert.assertEquals(409, response.getStatus());
        Response response1 = userResource.updateUser("", "fake@user.com", Arrays.asList("admin", "master"));
        Assert.assertEquals(400, response1.getStatus());
        Response response2 = userResource.updateUser("fake user", "", Arrays.asList("admin", "master"));
        Assert.assertEquals(400, response2.getStatus());
        Response response3 = userResource.updateUser("fake user", "fake@user.com", Arrays.asList(new String[] {}));
        Assert.assertEquals(400, response3.getStatus());
        Response response4 = userResource.updateUser("fake user", "fake@user.com", Arrays.asList(new String[] { "" }));
        Assert.assertEquals(400, response4.getStatus());

        createUser();
        Response response5 = userResource.updateUser("Real Name", "fake@email.com", Arrays.asList("admin", "master"));
        Assert.assertEquals(200, response5.getStatus());
    }

    @Test
    public void deleteUserTest() {
        userResource = new UserResource();
        Response response = userResource.deleteUser("fake user", "fake@user.com", Arrays.asList("admin", "master"));
        Assert.assertEquals(409, response.getStatus());
        Response response1 = userResource.deleteUser("", "fake@user.com", Arrays.asList("admin", "master"));
        Assert.assertEquals(400, response1.getStatus());
        Response response2 = userResource.deleteUser("fake user", "", Arrays.asList("admin", "master"));
        Assert.assertEquals(400, response2.getStatus());
        Response response3 = userResource.deleteUser("fake user", "fake@user.com", Arrays.asList(new String[] {}));
        Assert.assertEquals(400, response3.getStatus());
        Response response4 = userResource.deleteUser("fake user", "fake@user.com", Arrays.asList(new String[] { "" }));
        Assert.assertEquals(400, response4.getStatus());

        createUser();
        Response response5 = userResource.deleteUser("Real Name", "fake@email.com", Arrays.asList("admin", "master"));
        Assert.assertEquals(200, response5.getStatus());
    }

    @Test
    public void findUserTest() {
        userResource = new UserResource();
        Response response = userResource.findUser("fake@user.com");
        Assert.assertEquals(409, response.getStatus());
        Response response2 = userResource.findUser("");
        Assert.assertEquals(400, response2.getStatus());
        Response response3 = userResource.findUser(null);
        Assert.assertEquals(400, response3.getStatus());
        createUser();
        Response response4 = userResource.findUser("fake@email.com");
        Assert.assertEquals(200, response4.getStatus());
    }
}

package test.com.h2rd.refactoring.integration;

import java.util.Arrays;

import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.junit.Test;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.web.UserResource;

public class UserIntegrationTest {

    public User setUp() {
        User integration = new User();
        integration.setName("integration");
        integration.setEmail("initial@integration.com");
        integration.setRoles(Arrays.asList("admin", "master"));
        return integration;

    }

    @Test
    public void userActionTest() {
        UserResource userResource = new UserResource();
        User integration = setUp();
        Response response = userResource.findUser(integration.getEmail());
        Assert.assertEquals(409, response.getStatus());
        response = userResource.addUser(integration.getName(), integration.getEmail(), Arrays.asList(new String[] {}));
        Assert.assertEquals(400, response.getStatus());
        response = userResource.addUser(integration.getName(), integration.getEmail(),
                Arrays.asList(new String[] { "" }));
        Assert.assertEquals(400, response.getStatus());
        response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
        Assert.assertEquals(200, response.getStatus());
        response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
        Assert.assertEquals(409, response.getStatus());

        response = userResource.findUser(integration.getEmail());
        Assert.assertEquals(200, response.getStatus());
        integration.setName("Update");
        integration.setRoles(Arrays.asList("master"));
        response = userResource.updateUser(integration.getName(), integration.getEmail(), integration.getRoles());
        Assert.assertEquals(200, response.getStatus());

        response = userResource.deleteUser(integration.getName(), integration.getEmail(), integration.getRoles());
        Assert.assertEquals(200, response.getStatus());

        response = userResource.findUser(integration.getEmail());
        Assert.assertEquals(409, response.getStatus());

        response = userResource.getUsers();
        Assert.assertEquals(200, response.getStatus());
    }

}

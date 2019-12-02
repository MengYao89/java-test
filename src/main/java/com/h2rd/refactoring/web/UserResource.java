package com.h2rd.refactoring.web;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDaoImpl;
import com.h2rd.refactoring.util.Message;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Repository;

import java.util.List;

@Path("/users")
@Repository
public class UserResource {

    public UserDaoImpl userDao;

    @GET
    @Path("add/")
    public Response addUser(@QueryParam("name") String name, @QueryParam("email") String email,
                            @QueryParam("role") List<String> roles) {

        User user = new User();
        if (name == null || "".equals(name)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Message.PARAMETER_ERROR).build();
        }
        user.setName(name);
        if (email == null || "".equals(email)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Message.PARAMETER_ERROR).build();
        }
        user.setEmail(email);
        if (roles.size() == 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Message.PARAMETER_ERROR).build();
        }
        if (roles.size() == 1 && roles.get(0).equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Message.PARAMETER_ERROR).build();
        }
        user.setRoles(roles);

        if (userDao == null) {
            userDao = UserDaoImpl.getUserDao();
        }

        if (userDao.saveUser(user)) {
            return Response.status(Response.Status.OK).entity(Message.ADD_SUCCESSFUL).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(Message.ADD_FAILED).build();
        }

    }

    @GET
    @Path("update/")
    public Response updateUser(@QueryParam("name") String name, @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles) {

        User user = new User();
        if (name == null || "".equals(name)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Message.PARAMETER_ERROR).build();
        }
        user.setName(name);
        if (email == null || "".equals(email)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Message.PARAMETER_ERROR).build();
        }
        user.setEmail(email);
        if (roles.size() == 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Message.PARAMETER_ERROR).build();
        }
        if (roles.size() == 1 && roles.get(0).equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Message.PARAMETER_ERROR).build();
        }
        user.setRoles(roles);

        if (userDao == null) {
            userDao = UserDaoImpl.getUserDao();
        }

        if (userDao.updateUser(user)) {
            return Response.status(Response.Status.OK).entity(Message.UPDATE_SUCCESSFUL).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(Message.UPDATE_FAILED).build();
        }

    }

    @GET
    @Path("delete/")
    public Response deleteUser(@QueryParam("name") String name, @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles) {
        User user = new User();
        if (name == null || "".equals(name)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Message.PARAMETER_ERROR).build();
        }
        user.setName(name);
        if (email == null || "".equals(email)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Message.PARAMETER_ERROR).build();
        }
        user.setEmail(email);
        if (roles.size() == 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Message.PARAMETER_ERROR).build();
        }
        if (roles.size() == 1 && roles.get(0).equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Message.PARAMETER_ERROR).build();
        }
        user.setRoles(roles);

        if (userDao == null) {
            userDao = UserDaoImpl.getUserDao();
        }

        if (userDao.deleteUser(user)) {
            return Response.status(Response.Status.OK).entity(Message.DELETE_SUCCESSFUL).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(Message.DELETE_FAILED).build();
        }

    }

    @GET
    @Path("find/")
    public Response getUsers() {

        if (userDao == null) {
            userDao = UserDaoImpl.getUserDao();
        }
        List<User> users = userDao.getUsers();
        GenericEntity<List<User>> usersEntity = new GenericEntity<List<User>>(users) {
        };
        return Response.status(Response.Status.OK).entity(usersEntity).build();
    }

    @GET
    @Path("search/")
    public Response findUser(@QueryParam("email") String email) {

        if (userDao == null) {
            userDao = UserDaoImpl.getUserDao();
        }
        if (email == null || "".equals(email)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Message.PARAMETER_ERROR).build();
        } else {
            User user = userDao.findUser(email);

            if (user == null) {
                return Response.status(Response.Status.CONFLICT).entity(Message.SEARCH_FAILED).build();
            } else {
                return Response.status(Response.Status.OK).entity(user.toString()).build();
            }

        }

    }
}

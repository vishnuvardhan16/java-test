package com.h2rd.refactoring.web;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.utils.ValidateEmail;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.BadRequestException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Path("/users")
@Service
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource{
    
    private static ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
    		"classpath:/application-config.xml"	
    	});
    
    public UserDao userDao = context.getBean(UserDao.class);

    @POST
    @Path("add/")
    public Response addUser(@QueryParam("name") String name,
                            @QueryParam("email") String email,
                            @QueryParam("role") List<String> roles) {

        User user = new User();
        
        if(!name.isEmpty()) {
        	user.setName(name);
        }
        
        if(!email.isEmpty() || CollectionUtils.isEmpty(roles)) {
        	if(ValidateEmail.validate(email)) {
        		user.setEmail(email);
        	} 	
        } else {
        	throw new BadRequestException("Invalid email address or roles is not provided");
        }

        user.setRoles(roles);

        user = userDao.saveUser(user);
        if(user != null) {
        	return Response.ok().entity(user).build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("update/")
    public Response updateUser(@QueryParam("name") String name,
                               @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRoles(roles);
        userDao.users.add(user);

        user = userDao.updateUser(user);
        if (user != null) {
        	return Response.ok().entity(user).build();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("delete/")
    public Response deleteUser(@QueryParam("name") String name) {
        
    	if(name == null) {
    		throw new BadRequestException("Invalid name");
    	}
    	
        String username = userDao.deleteUser(name);
        if(username.equals(name)) {
        	return Response.ok().entity(name).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("find/")
    public Response getUsers() {
    	        
    	List<User> users = userDao.getUsers();
    	if (users == null) {
    		users = new ArrayList<User>();
    	}

        GenericEntity<List<User>> usersEntity = new GenericEntity<List<User>>(users) {};
        return Response.status(200).entity(usersEntity).build();
    }

    @GET
    @Path("search/")
    public Response findUser(@QueryParam("name") String name) {

    	if(name == null) {
    		throw new BadRequestException("Provide username");
    	}

        User user = userDao.findUser(name);
        return Response.ok().entity(user).build();
    }
}

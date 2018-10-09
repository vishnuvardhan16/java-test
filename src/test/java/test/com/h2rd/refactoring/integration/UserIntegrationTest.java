package test.com.h2rd.refactoring.integration;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.utils.SpringConfig;
import com.h2rd.refactoring.web.UserResource;

public class UserIntegrationTest {
	
	private static ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);    
	public UserDao userDao = (UserDao) ctx.getBean("userDao");
	
	private UserResource userResource;
	private User integration;
	
	@Before
	public void setup() {
		
		userResource = new UserResource();
		
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("admin");
		
		integration = new User();
        integration.setName("integration");
        integration.setEmail("initial@integration.com");
        integration.setRoles(roles);
        
        userDao.users.add(integration);
	}
	
	@Test
	public void createUserTest() {		
		
        Response response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
        Assert.assertEquals(200, response.getStatus());
	}

	@Test
	public void updateUserTest() {
		
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("master");
        
        User updated = new User();
        updated.setName("integration");
        updated.setEmail("updated@integration.com");
        updated.setRoles(roles);
        
        Response response = userResource.updateUser(updated.getName(), updated.getEmail(), updated.getRoles());
        Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	public void deleteUserTest() {
		
		Response response = userResource.deleteUser(integration.getName());
		Assert.assertEquals(200, response.getStatus());
	}
	
	@Test
	public void findUserTest() {
		
		Response response = userResource.findUser(integration.getName());
		Assert.assertEquals(200, response.getStatus());
	}
}

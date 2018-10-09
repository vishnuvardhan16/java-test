package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.utils.SpringConfig;
import com.h2rd.refactoring.web.UserResource;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.core.Response;

public class UserResourceUnitTest {

    UserResource userResource = new UserResource();
    
    private static ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);    
	public UserDao userDao = (UserDao) ctx.getBean("userDao");
	
	private static final String NAME = "Fake Name";
	private static final String EMAIL = "fake@email.com";
	
	User user;

	@Before
	public void setup() {
	   	user = new User();
        user.setName(NAME);
        user.setEmail(EMAIL);
        user.setRoles(Arrays.asList("admin", "master"));
	}
	
    @Test
    public void getUsersTest() {

        User user = new User();
        user.setName("fake user");
        user.setEmail("fake@user.com");
        userDao.saveUser(user);

        Response response = userResource.getUsers();
        Assert.assertEquals(200, response.getStatus());
    }
    
    @Test
    public void addUserTest() {
    	
    	ArrayList<String> roles = new ArrayList<String>();
    	roles.add("admin");
    	
    	Response response = userResource.addUser("fake user", "fake@email.com", roles);
    	Assert.assertEquals(200, response.getStatus());
    }
    
    @Test
    public void updateUserTest() {
    	
    	ArrayList<String> roles = new ArrayList<String>();
		roles.add("user");
        
        Response response = userResource.updateUser("integration", "fake@email.com", roles);
        Assert.assertEquals(200, response.getStatus());
    	
    }
    
    @Test
    public void findUserTest() {
    	
    	Response response = userResource.findUser(NAME);
    	Assert.assertEquals(200, response.getStatus());
    }
    
    @Ignore
    public void deleteUserTest() {
    	
    	Response response = userResource.deleteUser(NAME);
    	Assert.assertEquals(200, response.getStatus());
    	
    }
}

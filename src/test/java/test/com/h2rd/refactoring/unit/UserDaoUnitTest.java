package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.utils.SpringConfig;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.ArrayList;
import java.util.Arrays;

public class UserDaoUnitTest {
	
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
        
        userDao.users.add(user);
    }
    
    @Test
    public void saveUserTest() {

        userDao.saveUser(user);
        Assert.assertEquals(EMAIL, user.getEmail());
    }
    
    @Test
    public void updateUserTest() {
    	ArrayList<String> roles = new ArrayList<String>();
		roles.add("user");
        
        User userToUpdate = new User();
        userToUpdate.setName("integration");
        userToUpdate.setEmail("fake@email.com");
        userToUpdate.setRoles(roles);
        
        user = userDao.updateUser(userToUpdate);
        Assert.assertEquals("integration", user.getName());
    }
    
    @Test
    public void findUserTest() {

    	user = userDao.findUser(NAME);
    	Assert.assertEquals(NAME, user.getName());
    }
    
    @Test
    public void deleteUserTest() {
    	
    	String result = userDao.deleteUser(user.getName());
    	//User result = userDao.findUser(user.getName());
    	Assert.assertEquals(NAME, result);
    }
    
}
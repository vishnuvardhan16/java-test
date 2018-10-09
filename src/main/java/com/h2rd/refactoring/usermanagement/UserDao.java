package com.h2rd.refactoring.usermanagement;

import java.util.ArrayList;
import java.util.ListIterator;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    public ArrayList<User> users = new ArrayList<User>();

    public User saveUser(User user) {
        if(user != null) {
        	users.add(user);
        	return user;
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        try {
            return users;
        } catch (Throwable e) {
            System.out.println("error");
            return null;
        }
    }

    public String deleteUser(String userToDelete) {
        try {
        	if(users != null) {
        		ListIterator<User> iterator = users.listIterator();
        	
        		while(iterator.hasNext()) {
        			if(iterator.next().getName().equals(userToDelete)) {
        				iterator.remove();
        				return userToDelete;
        			}
        		}
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User updateUser(User userToUpdate) {
        try {
        	if(users != null) {
        		for (User user : users) {
        			if (user.getEmail() == userToUpdate.getEmail()) {
        				user.setName(userToUpdate.getName());
        				user.setRoles(userToUpdate.getRoles());
        				return user;
        			}
        		}
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findUser(String name) {
        try {
        	if(users != null) {
        		for (User user : users) {
        			if (user.getName() == name) {
        				return user;
        			}
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //to-do
    public User searchUser(String name) {
    
    	throw new UnsupportedOperationException();
    }
}

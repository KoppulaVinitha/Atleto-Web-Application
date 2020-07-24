package com.me.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.UserException;
import com.me.pojo.Email;
import com.me.pojo.ClasList;
import com.me.pojo.User;


public class UserDAO extends DAO {

	public UserDAO() {
	}

	public User get(String username, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}
	}
	
	public User getUserName(String username) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username");
			q.setString("username", username);
				
			User user = (User) q.uniqueResult();
			commit();
			return user;
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}
	}
	
	public Email getByEmail(String email) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Email where emailAddress = :email");
			q.setString("email", email);
				
			Email e = (Email) q.uniqueResult();
			commit();
			return e;
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get email " + email, e);
		}
	}
	
	public User get(int userId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where personID= :personID");
			q.setInteger("personID", userId);		
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userId, e);
		}
	}

	public User register(User u)
			throws UserException {
		try {
			begin();
			System.out.println("inside DAO");

			Email email = new Email(u.getEmail().getEmailAddress());
			User user = new User(u.getUsername(),u.getPassword(),u.getRole());

			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setEmail(email);
			user.setRole(u.getRole());
			user.setGym(u.getGym());
			email.setUser(user);
			getSession().save(user);
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}

	public void delete(User user) throws UserException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user " + user.getUsername(), e);
		}
	}
	
	public void update(User user, ClasList clas) throws UserException {
        try {
//        	getSession().close();
            begin();
            user.getClaslists().add(clas);
//            getSession().update(user);
            getSession().merge(user);
            commit();
            System.out.println("Updated the user with the clas");
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not update the user", e);
        }
    }
}
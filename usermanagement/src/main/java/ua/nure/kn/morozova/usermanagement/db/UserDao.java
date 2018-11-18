package ua.nure.kn.morozova.usermanagement.db;

import java.util.Collection;

import ua.nure.kn.morozova.usermanagement.User;

public interface UserDao {
	User create(User user) throws DatabaseException; 
	void update (User user) throws DatabaseException;
	void delete(User user) throws DatabaseException;
	User find(Long id) throws DatabaseException;
	Collection findAll() throws DatabaseException;
}

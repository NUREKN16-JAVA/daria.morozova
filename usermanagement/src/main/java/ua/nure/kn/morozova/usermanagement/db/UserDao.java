package ua.nure.kn.morozova.usermanagement.db;

import java.util.Collection;

import ua.nure.kn.morozova.usermanagement.User;

public interface UserDao {
	/**
	 * Add new record to DB with user
	 * @param user with null id
	 * @return modified user with auto generated id from DB
	 * @throws DatabaseException
	 */
	
	User create(User user) throws DatabaseException; 
	/**
	 * 
	 * @param user
	 * @throws DatabaseException
	 */
	void update (User user) throws DatabaseException;
	/**
	 * 
	 * @param user
	 * @throws DatabaseException
	 */
	void delete(User user) throws DatabaseException;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws DatabaseException
	 */
	User find(Long id) throws DatabaseException;
	/**
	 * 
	 * @return
	 * @throws DatabaseException
	 */
	Collection findAll() throws DatabaseException;
	
	 void setConnectionFactory(ConnectionFactory connectionFactory);
	
}

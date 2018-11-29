package ua.nure.kn.morozova.usermanagement.db;

import java.util.Collection;

import ua.nure.kn.morozova.usermanagement.User;

public interface UserDao {
	/**
	 * Adds new record to DB with user
	 * @param user with null id
	 * @return modified user with auto generated id from DB
	 * @throws DatabaseException
	 */
	User create(User user) throws DatabaseException; 
	
	
	/**
	 * Changes the data of a record in DB
	 * @param user
	 * @throws DatabaseException
	 */
	void update (User user) throws DatabaseException;
	
	
	/**
	 * Removes a record from DB. 
	 * @param user 
	 * @throws DatabaseException
	 */
	void delete(User user) throws DatabaseException;
	
	
	/**
	 * Retrieves a record from DB
	 * @param id of a user
	 * @return user
	 * @throws DatabaseException
	 */
	User find(Long id) throws DatabaseException;
	
	
	/**
	 * Retrieves all records from DB
	 * @return result set with all users
	 * @throws DatabaseException
	 */
	Collection findAll() throws DatabaseException;
	

	void setConnectionFactory(ConnectionFactory connectionFactory);
	
}

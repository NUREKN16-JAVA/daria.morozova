package ua.nure.kn.morozova.usermanagement.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import ua.nure.kn.morozova.usermanagement.User;

public class HsqldbUserDao implements UserDao {
	

	private ConnectionFactory connectionFactory;
	
	// для создания соединений в реализации DAO
	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	
	
	
	@Override
	public User create(User user) throws DatabaseException {
		try {
			Connection connetion = connectionFactory.createConnection();
			String sql = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
			PreparedStatement statement = connetion.prepareStatement(sql);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirth().getTime()));
			
			int n = statement.executeUpdate();
			if(n!=1) {
				throw new DatabaseException("Number of the inserted rows: " + n);
			}
			return user;
			
		} catch (DatabaseException e) {
			throw e;
			
		} catch (SQLException e) {
			throw new DatabaseException();
		}
		
	}

	@Override
	public void update(User user) throws DatabaseException {
	
	}

	@Override
	public void delete(User user) throws DatabaseException {
		
	}

	@Override
	public User find(Long id) throws DatabaseException {
	
		return null;
	}

	@Override
	public Collection findAll() throws DatabaseException {
	
		return null;
	}

}

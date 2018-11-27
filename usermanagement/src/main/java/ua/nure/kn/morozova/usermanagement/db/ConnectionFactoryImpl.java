package ua.nure.kn.morozova.usermanagement.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactoryImpl implements ConnectionFactory {

	@Override
	public Connection createConnection() throws DatabaseException {

		try {
			Class.forName(driver);				//Регистрируем драйвер
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		// creation of db connection
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}

	}

	private String user;
	private String password;
	private String url;
	private String driver;

	public ConnectionFactoryImpl(String user, String password, String url, String driver) {
		this.user = user;
		this.password = password;
		this.url = url;
		this.driver = driver;

	}
}

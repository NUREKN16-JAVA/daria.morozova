package ua.nure.kn.morozova.usermanagement.db;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class ConnectionFactoryImplTest {

	@Test
	public void testConnectionCreator() {
		ConnectionFactory conFactory = new ConnectionFactoryImpl("sa", "", "jdbc:hsqldb:file:db/usermanagement", "org.hsqldb.jdbcDriver");
		Connection connection = null;
		try {
			connection = conFactory.createConnection();
			assertNotNull(connection);
		} catch (DatabaseException e) {

			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}

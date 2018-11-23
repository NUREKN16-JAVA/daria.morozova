package ua.nure.kn.morozova.usermanagement.db;

import static org.junit.Assert.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Test;

import junit.framework.TestCase;
import ua.nure.kn.morozova.usermanagement.User;

public class HsqldbUserDaoTest extends DatabaseTestCase  {

	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;

	@Override
	protected void setUp() throws Exception {
		connectionFactory = new ConnectionFactoryImpl();
		dao = new HsqldbUserDao(connectionFactory);		
		
		}
		
	

	
	@Test
	public void testCreate() {
		try {
			
			User user = new User();
			user.setFirstName("Dasha");
			user.setLastName("Morozova");
			Date date = new Date(98, 2, 21);
			user.setDateOfBirth(date);

			assertNull(user.getId());
			user = dao.create(user);

			assertNotNull(user);
			assertNotNull(user.getId());

		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}

	}


	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		ConnectionFactory connectionFactory = new ConnectionFactoryImpl();
		return new DatabaseConnection(connectionFactory.createConnection());
	}


	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}

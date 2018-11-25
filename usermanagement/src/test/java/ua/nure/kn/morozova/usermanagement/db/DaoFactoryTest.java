package ua.nure.kn.morozova.usermanagement.db;

import static org.junit.Assert.*;

import org.junit.Test;

public class DaoFactoryTest {

	@Test
	public void testGetUserDao() {
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull(daoFactory);
			assertNotNull("DaoFactory instance is null", daoFactory);
				
			UserDao userDao = daoFactory.getUserDao();
			assertNotNull(userDao);
			assertNotNull("UserDao instance is null", userDao);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
		
	}

}

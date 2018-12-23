package ua.nure.kn.morozova.usermanagement.db;


import java.util.Collection;
import java.util.Date;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Test;
import ua.nure.kn.morozova.usermanagement.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {

	private static final String FIRST_NAME_ETALON = "Dasha";
	private static final String LAST_NAME_ETALON = "Morozova";
	private static final Long ID_ETALON = 1000L;
	private UserDao dao;
	private ConnectionFactory connectionFactory;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		connectionFactory = new ConnectionFactoryImpl("SA", "", "jdbc:hsqldb:file:D:/databases/usermanagement/usermanagement",
				"org.hsqldb.jdbcDriver");
		dao = new HsqldbUserDao(connectionFactory);
	}

	@Test
	public void testCreate() {
		try {

			User user = new User();
			user.setFirstName(FIRST_NAME_ETALON);
			user.setLastName(LAST_NAME_ETALON);
			user.setDateOfBirth(new Date());

			assertNull(user.getId());
			user = dao.create(user);

			assertNotNull(user);
			assertNotNull(user.getId());

		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}

	}

	@Test
	public void testFind() {
		try {
			User user = new User();
			user.setFirstName(FIRST_NAME_ETALON);
			user.setLastName(LAST_NAME_ETALON);
			user.setDateOfBirth(new Date());
			user.setId(ID_ETALON);

			assertEquals(user, dao.find(user.getId()));
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}

	}

	@Test
	public void testUpdate() {
		try {

			User updatedUser = new User();
			updatedUser.setFirstName("updatedFirstName");
			updatedUser.setLastName("updatedLastName");
			updatedUser.setDateOfBirth(new Date());
			updatedUser.setId(ID_ETALON);

			dao.update(updatedUser);

			User updatedUserFromDB = dao.find(ID_ETALON);
			assertNotNull(updatedUserFromDB);
			assertEquals("updatedFirstName", updatedUserFromDB.getFirstName());
			assertEquals("updatedLastName", updatedUserFromDB.getLastName());

		} catch (DatabaseException e) {

			e.printStackTrace();
			fail(e.toString());
		}

	}

	@Test
	public void testDelete() {

		try {
			User user = new User();
			user.setFirstName("deletedFirstName");
			user.setLastName("deletedLastName");
			user.setDateOfBirth(new Date());
			user = dao.create(user);

			assertNotNull(user);

			User chekedUser = dao.find(user.getId());
			assertNotNull(chekedUser);
			dao.delete(user);
			chekedUser = dao.find(user.getId());

			assertNull(chekedUser.getId());

		} catch (DatabaseException e) {

			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Test
	public void testFindAll() {
		try {
			Collection<?> collection = dao.findAll();
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size:", 2, collection.size());

		} catch (DatabaseException e) {

			e.printStackTrace();
			fail(e.toString());
		}

	}

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		ConnectionFactory connectionFactory = new ConnectionFactoryImpl("SA", "", "jdbc:hsqldb:file:D:/databases/usermanagement/usermanagement",
				"org.hsqldb.jdbcDriver");
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}

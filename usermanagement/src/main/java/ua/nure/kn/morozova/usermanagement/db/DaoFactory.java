package ua.nure.kn.morozova.usermanagement.db;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import javax.management.RuntimeErrorException;

public class DaoFactory {

	private static final String USER_DAO = "ua.nure.kn.morozova.usermanagement.db.UserDao";
	private final Properties properties;
	

    private final static DaoFactory INSTANCE = new DaoFactory();
    
    public static DaoFactory getInstance() {
        return INSTANCE;
    }
    
    
	public DaoFactory() {
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private ConnectionFactory getConnection() {
		String user = properties.getProperty("connection.user");
		String password = properties.getProperty("connection.password");
		String url = properties.getProperty("connection.url");
		String driver = properties.getProperty("connection.driver");
		
		return new ConnectionFactoryImpl(user, password, url, driver); 
	}

	public UserDao getUserDao() {
		UserDao result = null;
		
	
			try {
			Class myclass = myclass = Class.forName(properties.getProperty(USER_DAO));
				result = (UserDao) myclass.newInstance();
				result.setConnectionFactory(getConnectionFactory());
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		
		return result;											
	}

	private ConnectionFactory getConnectionFactory() {
		return new ConnectionFactoryImpl("sa", "", "jdbc:hsqldb:file:db/usermanagement", "org.hsqldb.jdbcDriver");
	}

}

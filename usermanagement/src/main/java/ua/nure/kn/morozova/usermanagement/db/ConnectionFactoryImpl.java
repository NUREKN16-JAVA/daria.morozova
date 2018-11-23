package ua.nure.kn.morozova.usermanagement.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactoryImpl implements ConnectionFactory {

	@Override
	public Connection createConnection() throws DatabaseException {
		String driver="org.hsqldb.jdbcDriver";
		String url="jdbc:hsqldb:file:db/usermanagement"; //адрес, где находится база
		String user="sa"; 
		String password="";
		

		//проверка наличия драйвера
		try {
			Class.forName(driver);						//динамическая загрузка класса драйвера в память
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} 
		
		
		//созд-е соединения с БД
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		
	}
	
}


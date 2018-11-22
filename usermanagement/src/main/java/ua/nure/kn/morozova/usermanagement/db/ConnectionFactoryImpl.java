package ua.nure.kn.morozova.usermanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {

	@Override
	public Connection createConnection() throws DatabaseException {
		String driver="org.hsqldb.jdbcDriver";
		String url="jdbc:hsqldb:file:db/usermanagement"; //адрес, где находится база
		String user="sa"; 
		String password="";
		
		
		//проверка наличия драйвера
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException(e);
//		} 
//		
//		
//		//созд-е соединения с БД
//		try {
//			return DriverManager.getConnection(url, user, password);
//		} catch (SQLException e) {
//			throw new DatabaseException(e);
//		}
//	}
//}

		 Connection con = null;
try {
    //Registering the HSQLDB JDBC driver
    Class.forName("org.hsqldb.jdbc.JDBCDriver");
    //Creating the connection with HSQLDB
    con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
    if (con!= null){
       System.out.println("Connection created successfully");
       
    }else{
       System.out.println("Problem with creating connection");
    }
 
 }  catch (Exception e) {
    e.printStackTrace(System.out);
 }
}
}
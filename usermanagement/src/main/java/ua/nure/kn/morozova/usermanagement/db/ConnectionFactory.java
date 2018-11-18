package ua.nure.kn.morozova.usermanagement.db;

import java.sql.Connection;

public interface ConnectionFactory {

	Connection createConnection() throws DatabaseException;
}

package sqlutils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnUtils {

	public static Connection getMySQLConnection() throws Exception {
		// Note: Change the connection parameters accordingly.
		String hostName = "127.0.0.1"; // "aa113r1zv2dzzkx.ctu7qijg4l1w.us-east-2.rds.amazonaws.com"; // "127.0.0.1";
		String dbName = "sys";
		String userName = "root";
		String password = "passport";
		return getMySQLConnection(hostName, dbName, userName, password);
	}

	public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
			throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName; // + "?";
		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		return conn;
	}
}

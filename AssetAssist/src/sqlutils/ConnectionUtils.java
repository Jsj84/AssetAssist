package sqlutils;

import java.sql.Connection;

public class ConnectionUtils {

	public static Connection getConnection() throws Exception {
		return MySQLConnUtils.getMySQLConnection();
	}

	public static void closeQuietly(Connection conn) {
		try {
			conn.close();

		} catch (Exception e) {
		}

	}

	public static void rollbackQuietly(Connection conn) {
		try {
			conn.rollback();
		} catch (Exception e) {
		}
	}

}
package sqlutils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class MySQLConnUtils {

	private static DataSource datasource = null;

	static {
		init();
	}

	private static void init() {
		String hostName = "aa1b0plcckesu6i.ctu7qijg4l1w.us-east-2.rds.amazonaws.com";
		String dbName = "asset_assist";
		String userName = "root";
		String password = "passport";
		String port = "3306";

		String connectionURL = "jdbc:mysql://" + hostName + ":" + port + "/" + dbName + "?user=" + userName
				+ "&password=" + password + "&autoReconnect=true&useSSL=false";

		PoolProperties p = new PoolProperties();
		p.setUrl(connectionURL);
		p.setDriverClassName("com.mysql.jdbc.Driver");
		p.setUsername(userName);
		p.setPassword(password);
		p.setJmxEnabled(true);
		p.setTestWhileIdle(false);
		p.setTestOnBorrow(true);
		p.setValidationQuery("SELECT 1");
		p.setTestOnReturn(false);
		p.setValidationInterval(30000);
		p.setTimeBetweenEvictionRunsMillis(30000);
		p.setMaxActive(100);
		p.setInitialSize(10);
		p.setMaxWait(10000);
		p.setRemoveAbandonedTimeout(300);
		p.setMinEvictableIdleTimeMillis(30000);
		p.setMinIdle(10);
		p.setLogAbandoned(true);
		p.setRemoveAbandoned(true);
		p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.ResetAbandonedTimer");

		datasource = new DataSource();
		datasource.setPoolProperties(p);
	}

	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
		return datasource.getConnection();
	}
}

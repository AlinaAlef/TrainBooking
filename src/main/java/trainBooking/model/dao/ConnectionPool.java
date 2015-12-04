package trainBooking.model.dao;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

	private static ConnectionPool datasource;
	private static BasicDataSource ds;
	static Logger log = Logger.getLogger(ConnectionPool.class.getName());

	static {
		ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUsername("root");
		ds.setPassword("asxz");
		ds.setUrl("jdbc:mysql://localhost:3306/booking?autoReconnect=true&useUnicode=true");
	}

	private ConnectionPool() {

	}

	public static synchronized ConnectionPool getInstance() throws IOException, SQLException, PropertyVetoException {
		if (datasource == null) {
			System.out.println("null");
			datasource = new ConnectionPool();
			return datasource;
		} else {
			return datasource;
		}
	}

	public static synchronized Connection getConnection() throws SQLException {
		try {
			getInstance();
		} catch (IOException e) {
			Logger.getLogger(ConnectionPool.class).error(e.getMessage());
		} catch (PropertyVetoException e) {
			Logger.getLogger(ConnectionPool.class).error(e.getMessage());
		}
		return ds.getConnection();
	}
}
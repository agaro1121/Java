package storage;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
public class ConnectionManager {
	private static Connection connection;
	private DataSource dataSource;
	private Context initContext;
	private Context envContext;
	private static Logger connectionLog = Logger.getLogger("DBConnection");

	private ConnectionManager(){
		try {
			initContext = new InitialContext();
			envContext  = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/myoracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static class CMHolder{
		public static final ConnectionManager INSTANCE = new ConnectionManager();
	}

	public static ConnectionManager getInstance(){
		return CMHolder.INSTANCE;
	}

	public Connection getConnection(){
		try {
			connection = dataSource.getConnection();
			connectionLog.info("Database Connection Opened");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void disconnect(){
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Problem closing connection");
			e.printStackTrace();
		}
		connectionLog.info("Database Connection Closed");
	}
	
	public static void main(String[] args){
		ConnectionManager.getInstance().getConnection();
		ConnectionManager.disconnect();
	}

}

package storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
public class ConnectionManager {
	private Properties properties = new Properties();
	private FileInputStream fileLoader;
	private static Connection connection;

	private ConnectionManager(){
		try {
			fileLoader = new FileInputStream(SQL_Literals.properties_XML);
			properties.loadFromXML(fileLoader);
			Class.forName(properties.getProperty("driver"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
			connection = DriverManager.getConnection(properties.getProperty("urlHome"), 
					properties.getProperty("username"),
					properties.getProperty("password"));
			//TODO logit
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
		//TODO logit
	}

}

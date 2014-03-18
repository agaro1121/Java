package tests;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
public class tempDB {
	private Properties properties = new Properties();
	private InputStream fileLoader;
	private static Connection connection;

	private tempDB(){
		try {
			fileLoader = this.getClass().getResourceAsStream("/jdbcProperties.xml");
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
		public static final tempDB INSTANCE = new tempDB();
	}

	public static tempDB getInstance(){
		return CMHolder.INSTANCE;
	}

	public Connection getConnection(){
		try {
			connection = DriverManager.getConnection(properties.getProperty("urlHome"), 
					properties.getProperty("username"),
					properties.getProperty("password"));
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
	}
}

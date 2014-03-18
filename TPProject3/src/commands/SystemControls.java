package commands;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.apache.log4j.Logger;

import storage.ConnectionManager;


public class SystemControls{
	private Logger admin = Logger.getLogger("AdminLog");
	private static String username;
	private Connection connection;
	private PreparedStatement generalStatement;
	private Properties queries;
	private InputStream is;

	public static void setUsername(String inputUsername){
		username = inputUsername;
	}

	public SystemControls(){
		queries = new Properties();
		InputStream is = this.getClass().getResourceAsStream("/Queries.xml");
		try {
			queries.loadFromXML(is);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void changeStatus(String newStatus){
		
		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(queries.getProperty("changeStatus"));

			generalStatement.setString(1, newStatus);
			generalStatement.setString(2, username);

			generalStatement.execute();
			connection.commit();
			
			admin.info(username + " - " + newStatus);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.disconnect();
		}
	}

	public void addUser(){
		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(queries.getProperty("verifyUser"));

			generalStatement.setString(1, username);

			generalStatement.execute();
			connection.commit();
			
			admin.info("Verified " + username);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.disconnect();
		}
	}
}

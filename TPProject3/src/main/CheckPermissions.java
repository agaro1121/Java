package main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import storage.ConnectionManager;

public class CheckPermissions{
	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement generalStatement;
	private List<String> permissions = new ArrayList<String>();
	private Properties queries;
	private InputStream is;
	
	public List<String> execute(String username) {
		connection = ConnectionManager.getInstance().getConnection();
		queries = new Properties();
		is = this.getClass().getResourceAsStream("/Queries.xml");
		try {
			queries.loadFromXML(is);
			generalStatement = connection.prepareStatement(queries.getProperty("getUsersTypes"));
			generalStatement.setString(1, username);
			resultSet = generalStatement.executeQuery();
			
			while(resultSet.next()){
				permissions.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			ConnectionManager.disconnect();
		}
		return permissions;
	}
}









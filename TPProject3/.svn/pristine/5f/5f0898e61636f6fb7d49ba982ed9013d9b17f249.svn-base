package main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import storage.ConnectionManager;

public class ChangePassword {
private static ChangePassword rp = new ChangePassword();
private Connection connection;
private PreparedStatement generalStatement;
	
	private ChangePassword(){}
	public static ChangePassword getInstance(){
		return rp;
	}
	
	public void reset(String username, String password, int type){
		Properties queries = new Properties();
		InputStream is = this.getClass().getResourceAsStream("/Queries.xml");

		
		connection = ConnectionManager.getInstance().getConnection();
		try {
			queries.loadFromXML(is);
			generalStatement = connection.prepareStatement(queries.getProperty("resetPassword"));
//			generalStatement = connection.prepareStatement(SQL_Literals.resetPassword);
			
			if(type == 0){
			generalStatement.setString(1, username);
			}else{
				generalStatement.setString(1, password);
			}
			generalStatement.setString(2, username);
			
			generalStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			ConnectionManager.disconnect();
		}
		
	}
}

package main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import storage.ConnectionManager;
import classInterfaces.iModel;

public class mLogin implements iModel{
	private Map<String,String> loginInfo = new HashMap<String,String>();
	private Logger loginLog = Logger.getLogger("LoginLog");
	private Connection connection;
	private PreparedStatement generalStatement;
	private ResultSet resultSet;
	private String username;
	private String password;
	
	
	public mLogin(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public boolean execute(){
		Properties queries = new Properties();
		InputStream is = this.getClass().getResourceAsStream("/Queries.xml");

		boolean isVerified = false;
		try {
			queries.loadFromXML(is);
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(queries.getProperty("getLoginCredentials"));
			resultSet = generalStatement.executeQuery();
			while(resultSet.next()){
				loginInfo.put(resultSet.getString(1),resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		isVerified = ((password.equals(loginInfo.get(username))) ? isVerified=true:isVerified); 
		if(isVerified){
			loginLog.trace(username + " Logged in");
		}else{loginLog.trace(username + " attempted to Log in");} 
		return isVerified;
	}
}

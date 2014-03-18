package main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.apache.log4j.Logger;

import storage.ConnectionManager;
import storage.User;
import classInterfaces.iModel;

public class mRegister implements iModel{
	private static int id;
	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement generalStatement;
	private Properties queries;
	private Logger registerUser = Logger.getLogger("VerifyUsers");
	private boolean complete = false;
	private User newUser;
	
	public mRegister(User newUser){
		this.newUser = newUser;
		try {
			queries = new Properties();
			InputStream is = this.getClass().getResourceAsStream("/Queries.xml");
			queries.loadFromXML(is);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean execute(){
		
		try {
			setID();

			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(queries.getProperty("appendUser"));
			
			id++;
			generalStatement.setInt(1, id);
			generalStatement.setString(2, newUser.getFirstname());
			generalStatement.setString(3, newUser.getLastname());
			generalStatement.setString(4, newUser.getFirstname()+".".concat(newUser.getLastname()).concat(String.valueOf(id)));
			generalStatement.setString(5, newUser.getPassword());
			generalStatement.setString(6, String.valueOf(newUser.getVerified()));
			generalStatement.setString(7, newUser.getStatus());
			generalStatement.execute();

			generalStatement = connection.prepareStatement(queries.getProperty("appendUserType"));
			generalStatement.setInt(1, id);

			if(newUser.isAdmin()){
				generalStatement.setInt(2, 1);
				generalStatement.execute();
			}
			if(newUser.isBroker()){
				generalStatement.setInt(2, 2);
				generalStatement.execute();
			}
			if(newUser.isSE_Manager()){
				generalStatement.setInt(2, 3);
				generalStatement.execute();
			}
			if(newUser.isShare_Holder()){
				generalStatement.setInt(2, 4);
				generalStatement.execute();
			}

			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.disconnect();
		}
		registerUser.info(newUser.getFirstname()+".".concat(newUser.getLastname()).concat(String.valueOf(id)) + " wishes to be verified");
		complete = true;
		
		return complete;
	}

	public void setID(){
		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(queries.getProperty("getMaxID"));
			resultSet = generalStatement.executeQuery();
			while(resultSet.next())
			{
				id = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.disconnect();
		}
	}
}

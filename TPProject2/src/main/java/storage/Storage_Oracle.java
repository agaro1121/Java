package storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import classInterfaces.Interface_Storage;

public class Storage_Oracle implements Interface_Storage{
	private static int id;
	private Connection connection;
	private PreparedStatement generalStatement;
	private Properties properties = new Properties();
	private FileInputStream fileLoader;
	private ResultSet resultSet;
	private Statement statement;
	private List<User> users = new ArrayList<User>();
	private Map<String,String> loginInfo = new HashMap<String,String>();



	public Storage_Oracle(){
		try {
			fileLoader = new FileInputStream(SQL_Literals.properties_XML);
			properties.loadFromXML(fileLoader);

			setID();

		} catch (FileNotFoundException e) {
			System.out.println("Error Loading XML SQL Properties");
			e.printStackTrace();
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setID(){
		try {
			connection = ConnectionManager.getInstance().getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_Literals.getMaxID);
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

	public List<User> readAllUsers(){
		try {
			connection = ConnectionManager.getInstance().getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_Literals.getAllUsers);

			while (resultSet.next()) {
				User temp = new User();
				temp.setUserID(Integer.parseInt(resultSet.getString("USER_ID")));
				temp.setFirstname(resultSet.getString("FIRSTNAME"));
				temp.setLastname(resultSet.getString("LASTNAME"));
				temp.setUsername(resultSet.getString("USERNAME"));
				temp.setPassword(resultSet.getString("PASSWORD"));
				temp.setIsVerified(Boolean.parseBoolean(resultSet.getString("ISVERIFIED")));
				temp.setStatus(resultSet.getString("STATUS"));
				users.add(temp);
				loginInfo.put(temp.getUsername(), temp.getPassword());
			}
		} catch (SQLException e) {
			System.out.println("Problem with select statement");
			e.printStackTrace();
		}
		finally{
			ConnectionManager.disconnect();
		}
		return users;
	}

	public Map<String, String> getLoginHash(){
		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(SQL_Literals.getLoginCredentials);
			resultSet = generalStatement.executeQuery();
			while(resultSet.next()){
				loginInfo.put(resultSet.getString(1),resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.disconnect();
		}
		return loginInfo;
	}

	public void appendUser(User newUser){
		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(SQL_Literals.appendUser);

			id++;
			generalStatement.setInt(1, id);
			generalStatement.setString(2, newUser.getFirstname());
			generalStatement.setString(3, newUser.getLastname());
			generalStatement.setString(4, newUser.getFirstname()+".".concat(newUser.getLastname())+".".concat(String.valueOf(id)));
			generalStatement.setString(5, newUser.getPassword());
			generalStatement.setString(6, String.valueOf(newUser.getIsVerified()));
			generalStatement.setString(7, newUser.getStatus());
			generalStatement.execute();

			generalStatement = connection.prepareStatement(SQL_Literals.appendUserType);
			generalStatement.setInt(1, id);

			if(newUser.getIsAdmin()){
				generalStatement.setInt(2, 1);
				generalStatement.execute();
			}
			if(newUser.getIsBroker()){
				generalStatement.setInt(2, 2);
				generalStatement.execute();
			}
			if(newUser.getIsSE_Manager()){
				generalStatement.setInt(2, 3);
				generalStatement.execute();
			}
			if(newUser.getIsShare_Holder()){
				generalStatement.setInt(2, 4);
				generalStatement.execute();
			}

			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.disconnect();
		}
	}


	//Lists users whether they are admin, share_holder, se_manager etc..
	public Map<String,String> getUserByType(int type){
		Map<String,String> users = new HashMap<String,String>();
		try {
			connection = ConnectionManager.getInstance().getConnection();
			if(type == 0){
				generalStatement = connection.prepareStatement(SQL_Literals.getAllUsersByType);
			}else{
				generalStatement = connection.prepareStatement(SQL_Literals.getUserByType);
				generalStatement.setInt(1, type);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			resultSet = generalStatement.executeQuery();
			while(resultSet.next()){
				users.put(resultSet.getString(1), resultSet.getString(2));
			}
		} catch (SQLException e) {
			System.out.println("Error Type Query");
			e.printStackTrace();
		}finally{
			ConnectionManager.disconnect();
		}
		return users;
	}

	public List<String> getUserByStatus(String command){
		List<String> users = new ArrayList<String>();

		try {
			connection = ConnectionManager.getInstance().getConnection();
			if(command == Status.ADD_USER){
				generalStatement = connection.prepareStatement(SQL_Literals.listUnverifiedUsers);
			}
			if(command == Status.REMOVE_USER){
				generalStatement = connection.prepareStatement(SQL_Literals.listUserstoRemove);
			}
			if(command == Status.BAN_USER){
				generalStatement = connection.prepareStatement(SQL_Literals.listUsersToBan);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			resultSet = generalStatement.executeQuery();
			while(resultSet.next()){
				users.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			System.out.println("Storage Oracle: Listing Error");
			e.printStackTrace();
		}finally{
			ConnectionManager.disconnect();
		}
		return users;
	}
}
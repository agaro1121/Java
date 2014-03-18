package storage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import classInterfaces.iStorage;

public class Storage_Oracle implements iStorage{
	private Connection connection;
	private PreparedStatement generalStatement;
	private ResultSet resultSet;
	private Statement statement;
	private List<User> users = new ArrayList<User>();
	private Map<String,String> loginInfo = new HashMap<String,String>();
	private Properties queries;
	private InputStream is;

	public Storage_Oracle(){
		queries = new Properties();
		is = this.getClass().getResourceAsStream("/Queries.xml");
		try {
			queries.loadFromXML(is);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<User> readAllUsers(){

		try {
			connection = ConnectionManager.getInstance().getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(queries.getProperty("getAllUsers"));

			while (resultSet.next()) {
				//				User temp = new User();
				User temp = new User(Integer.parseInt(resultSet.getString("USER_ID")),
						resultSet.getString("FIRSTNAME"),
						resultSet.getString("LASTNAME"),
						resultSet.getString("USERNAME"),
						resultSet.getString("PASSWORD")
						);
				temp.setVerified(Boolean.parseBoolean(resultSet.getString("ISVERIFIED")));
				temp.setStatus(resultSet.getString("STATUS"));

				//				temp.setUserID(Integer.parseInt(resultSet.getString("USER_ID")));
				//				temp.setFirstname(resultSet.getString("FIRSTNAME"));
				//				temp.setLastname(resultSet.getString("LASTNAME"));
				//				temp.setUsername(resultSet.getString("USERNAME"));
				//				temp.setPassword(resultSet.getString("PASSWORD"));
				//				temp.setVerified(Boolean.parseBoolean(resultSet.getString("ISVERIFIED")));
				//				temp.setStatus(resultSet.getString("STATUS"));
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


	//Lists users whether they are admin, share_holder, se_manager etc..
	public Map<String,String> getUserByType(int type){
		Map<String,String> users = new HashMap<String,String>();
		try {
			connection = ConnectionManager.getInstance().getConnection();
			if(type == 0){
				generalStatement = connection.prepareStatement(queries.getProperty("getAllUsersByType"));
			}else{
				generalStatement = connection.prepareStatement(queries.getProperty("getUserByType"));
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
			if(command == AppLiterals.ADD_USER){
				generalStatement = connection.prepareStatement(queries.getProperty("listUnverifiedUsers"));
			}
			if(command == AppLiterals.REMOVE_USER){
				generalStatement = connection.prepareStatement(queries.getProperty("listUserstoRemove"));
			}
			if(command == AppLiterals.BAN_USER){
				generalStatement = connection.prepareStatement(queries.getProperty("listUsersToBan"));
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

	public List<Object[]> getUserRequests(){
		List<Object[]> data = new ArrayList<Object[]>();
		
		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(queries.getProperty("getAllUserRequests"));
			resultSet = generalStatement.executeQuery();
			
			while(resultSet.next()){
				data.add(new Object[]{resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),
						resultSet.getInt(5),resultSet.getString(6)});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.disconnect();
		}
		
		return data;
	}

	public List<String> getColumnNames(){
		List<String> columnNames = new ArrayList<String>();

		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(queries.getProperty("getAllUserRequests"));
			resultSet = generalStatement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			
			int columnCount = rsmd.getColumnCount();

			for (int i = 1; i < columnCount + 1; i++ ) {
				columnNames.add(rsmd.getColumnName(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.disconnect();
		}
		return columnNames;
	}

	public boolean updateUserRequest(int request_id, String newStatus){
		System.out.println(request_id + " "+newStatus);
		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(queries.getProperty("updateUserRequest"));
			
			generalStatement.setString(1, newStatus);
			generalStatement.setInt(2, request_id);
			
			generalStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.disconnect();
		}
		return true;
	}
	
}
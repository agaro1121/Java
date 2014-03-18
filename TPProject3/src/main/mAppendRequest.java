package main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import storage.ConnectionManager;
import storage.UserRequest;
import classInterfaces.iModel;

public class mAppendRequest implements iModel{
	private boolean complete = false;
	private UserRequest newUserRequest;
	private Connection connection;
	private PreparedStatement generalStatement;
	private Properties queries = new Properties();
	private InputStream is;

	public mAppendRequest(UserRequest newUserRequest){
		this.newUserRequest = newUserRequest;
		is = this.getClass().getResourceAsStream("/Queries.xml");
		try {
			queries.loadFromXML(is);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public boolean execute() {
		connection = ConnectionManager.getInstance().getConnection();
		try {
			generalStatement = connection.prepareStatement(queries.getProperty("submitRequest"));
			generalStatement.setInt(1, newUserRequest.getUser_ID());
			generalStatement.setString(2, newUserRequest.getDescription());
			generalStatement.setString(3, newUserRequest.getStatus());
			generalStatement.setInt(4, newUserRequest.getAdmin_ID());

			generalStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.disconnect();
		}
		complete = true;
		return complete;
	}

}

package commandReceivers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import storage.ConnectionManager;
import storage.SQL_Literals;
import classInterfaces.Interface_Receiver;


public class Receiver_Admin implements Interface_Receiver{
	private Logger admin = Logger.getLogger("AdminLog");
	private static String username;
	private Connection connection;
	private PreparedStatement generalStatement;

	public static void setUsername(String inputUsername){
		username = inputUsername;
	}

	public void changeStatus(String newStatus){
		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(SQL_Literals.changeStatus);

			generalStatement.setString(1, newStatus);
			generalStatement.setString(2, username);

			generalStatement.execute();
			connection.commit();
			
			admin.info("Changed " + username + " status to " + newStatus);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.disconnect();
		}
	}

	public void addUser(){
		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(SQL_Literals.verifyUser);

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

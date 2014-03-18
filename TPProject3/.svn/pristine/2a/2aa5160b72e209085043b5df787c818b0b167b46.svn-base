package actions;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storage.ConnectionManager;
import classInterfaces.iAction;

public class aCreateRequest implements iAction {
	private Connection connection;
	private PreparedStatement generalStatement;
	private ResultSet resultSet;
	private Properties queries = new Properties();
	private int requestID;

	public aCreateRequest(){
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

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		boolean complete = false;
		try {
			getMaxRequestID();
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(queries.getProperty("submitRequest"));
			
			requestID++;
			generalStatement.setInt(1, Integer.parseInt(request.getSession(false).getAttribute("UserID").toString()));
			generalStatement.setString(2, request.getParameter("description").toString());
			generalStatement.setString(3, "New");
			generalStatement.setInt(4, Integer.parseInt(request.getParameter("admin").toString()));
			generalStatement.setInt(5, requestID);
			
			generalStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.disconnect();
		}
		complete = true;
		
		return (complete) ? "Success.jsp":"Fail.jsp";
	}

	public int getMaxRequestID(){
		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(queries.getProperty("getMaxRequestID"));
			resultSet = generalStatement.executeQuery();
			while(resultSet.next())
			{
				requestID = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionManager.disconnect();
		}

		return 0;
	}

}

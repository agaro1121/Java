package actions;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import storage.ConnectionManager;
import classInterfaces.iAction;

public class aViewAvailableStock implements iAction {
	private Properties queries;
	private Connection connection;
	private PreparedStatement generalStatement;
	private ResultSet resultSet;
	
	public aViewAvailableStock(){
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
		HttpSession session = request.getSession(false);
		List<List<Object>> shares = new ArrayList<List<Object>>();
		List<String> columnNames = new ArrayList<String>();
		
		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(queries.getProperty("availableShares"));
			resultSet =generalStatement.executeQuery();
			
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i < columnCount + 1; i++ ) {
				columnNames.add(rsmd.getColumnName(i));
			}
			
			int index = -1;
			while(resultSet.next()){
				List<Object> share = new ArrayList<Object>();
				index++;
				share.add(resultSet.getInt(1));
				share.add(resultSet.getString(2));
				share.add(resultSet.getString(3));
				share.add(resultSet.getString(4));
				share.add(resultSet.getInt(5));
				share.add(resultSet.getDouble(6));
				shares.add(index,share);
			}
			
			generalStatement = connection.prepareStatement(queries.getProperty("getBrokersByStockID"));
			for (int i = 0; i < shares.size(); i++) {
				List<Object[]> tempB = new ArrayList<Object[]>();
				List<Object> share = shares.get(i);
				generalStatement.setInt(1,(Integer) share.get(0));
				resultSet = generalStatement.executeQuery();
				while(resultSet.next()){
					tempB.add(new Object[]{resultSet.getInt(1),resultSet.getString(2)});
				}
				share.add(tempB);
				shares.set(i, share);
			}
			
			session.setAttribute("aColumns", columnNames);
			session.setAttribute("aShares", shares);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.disconnect();
		}
		
		return null;
	}

}

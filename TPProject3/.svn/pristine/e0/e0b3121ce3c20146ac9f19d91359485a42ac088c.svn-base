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

import storage.ConnectionManager;
import classInterfaces.iAction;

public class aViewStockEx implements iAction {
	private Properties queries;
	private Connection connection;
	private PreparedStatement generalStatement;
	private ResultSet resultSet;

	public aViewStockEx(){
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
		List<String[]> stockEx = new ArrayList<String[]>();
		List<String> columnNames = new ArrayList<String>();
		int arraySize = 0;

		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(queries.getProperty("getStockEx"));

			resultSet = generalStatement.executeQuery();
			
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i < columnCount + 1; i++ ) {
				columnNames.add(rsmd.getColumnName(i));
			}
			
			while(resultSet.next()){
				String[] row =
					{
						resultSet.getString(1).toString(),
						resultSet.getString(2).toString(),
						resultSet.getString(3).toString(),
						resultSet.getString(4).toString(),
						resultSet.getString(5).toString(),
					};
				arraySize = row.length;
				stockEx.add(row);
			}
			request.setAttribute("stockEx", stockEx);
			request.setAttribute("arraySize", arraySize);
			request.setAttribute("columns", columnNames);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.disconnect();
		}
		return "WEB-INF/SHPages/ViewStockEx.jsp";
	}
}

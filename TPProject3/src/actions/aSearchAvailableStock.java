package actions;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storage.ConnectionManager;

import classInterfaces.iAction;

public class aSearchAvailableStock implements iAction {
	private Properties queries;
	private Connection connection;
	private PreparedStatement generalStatement;
	private ResultSet resultSet;
	private List<List<Object>> stocks = new ArrayList<List<Object>>();
	
	public aSearchAvailableStock(){
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
		stocks.clear();
		
		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(queries.getProperty("AvailableStockBy"+request.getParameter("searchby")));
			
			if(request.getParameter("searchby").toString().equals("Price")){
				generalStatement.setDouble(1, Double.parseDouble(request.getParameter("userInput")));
				generalStatement.setDouble(2, Double.parseDouble(request.getParameter("userInput2")));
				
			} else {
				generalStatement.setString(1, "%"+request.getParameter("userInput").toString().toLowerCase()+"%");
			}
			resultSet = generalStatement.executeQuery();
			
			while(resultSet.next()){
				List<Object> stock = new ArrayList<Object>();
				stock.add(resultSet.getInt(1));
				stock.add(resultSet.getString(2));
				stock.add(resultSet.getString(3));
				stock.add(resultSet.getInt(4));
				stock.add(resultSet.getDouble(5));
				stocks.add(stock);
			}

			request.setAttribute("results", stocks);
			request.setAttribute("headers", "Stock ID,Company,Symbol,Available Shares,Current Price");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.disconnect();
		}
		
		
		return "WEB-INF/SHPages/SearchResults.jsp";
	}

}

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

public class aSearchCompany implements iAction {
	private Properties queries;
	private Connection connection;
	private PreparedStatement generalStatement;
	private ResultSet resultSet;
	private List<List<Object>> companies = new ArrayList<List<Object>>();
	
	public aSearchCompany(){
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
		companies.clear();
		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(queries.getProperty("companyBy"+request.getParameter("searchby")));
			generalStatement.setString(1, "%"+request.getParameter("userInput").toString().toLowerCase()+"%");
			resultSet = generalStatement.executeQuery();
			
			int index=-1;
			while(resultSet.next()){
				List<Object> company = new ArrayList<Object>();
				++index;
				company.add(resultSet.getInt(1));
				company.add(resultSet.getString(2));
				company.add(resultSet.getString(3));
				company.add(resultSet.getString(4));
				company.add(resultSet.getString(5));
				company.add(resultSet.getString(6));
				companies.add(index,company);
			}

			request.setAttribute("headers", "Company ID,Name,City,Country,Stock Symbol,Stock Exchange");
			request.setAttribute("results", companies);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.disconnect();
		}
		return "WEB-INF/SHPages/SearchResults.jsp";
	}

}

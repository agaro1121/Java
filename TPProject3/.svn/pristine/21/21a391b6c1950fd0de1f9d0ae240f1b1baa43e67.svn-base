package actions;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

public class aMakeRequest implements iAction {
	private Properties queries;
	private Connection connection;
	private PreparedStatement generalStatement;

	public aMakeRequest(){
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
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		List<List<Object>> stocks = new ArrayList<List<Object>>();
		String nextPage = new String();
		
		if(request.getParameter("transactionType").toString().equals("SELL")){
			stocks = (List<List<Object>>) session.getAttribute("shares");
		} else {
			stocks = (List<List<Object>>) session.getAttribute("aShares");
		}
		
		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(queries.getProperty("makeTradeRequest"));
			
			for (List<Object> objects : stocks) {
				if((Integer)objects.get(0) == Integer.parseInt(request.getParameter("stockID"))){
					if((Integer)objects.get(4) >= Integer.parseInt(request.getParameter("amount"))){

						generalStatement.setInt(1, (Integer)session.getAttribute("UserID"));
						generalStatement.setInt(2, Integer.parseInt(request.getParameter("stockID")));
						generalStatement.setString(3,request.getParameter("transactionType").toString());
						generalStatement.setInt(4,Integer.parseInt(request.getParameter("amount")));
						generalStatement.setInt(5,Integer.parseInt(request.getParameter("broker")));
						generalStatement.setDate(6,sqlDate);
						generalStatement.setString(7,"Pending");
						generalStatement.execute();
						connection.commit();
						
						nextPage = "WEB-INF/SHPages/RequestTrade.jsp";
					} else {
						nextPage = "Fail.jsp";
					}
					break;
				}
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.disconnect();
		}

		return nextPage;
	}

}

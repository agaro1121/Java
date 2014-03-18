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
import javax.servlet.http.HttpSession;

import storage.ConnectionManager;
import classInterfaces.iAction;

public class aSetShareTicker implements iAction {
	private Properties queries;
	private Connection connection;
	private PreparedStatement generalStatement;
	private ResultSet resultSet;
	private String[] input;

	public aSetShareTicker(){
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

	public void setStocks(String[] input){
		this.input = input;
	}
	
	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		StringBuilder tickerData = new StringBuilder();
		int index = 0;
		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(queries.getProperty("ShareTicker"));
			
			if(request.getParameterValues("userInput") != null){
				input = request.getParameterValues("userInput");
			}
			
			for(int i=0; i<input.length;i++){
				index = i + 1;
				generalStatement.setInt(index,Integer.parseInt(input[i]));
			}

			if(input.length < 8){
				int x = 8 - (8 - input.length);
				for(int i=x; i<8;i++){
					index = i + 1;
					generalStatement.setInt(index,0);
				}
			}
			resultSet = generalStatement.executeQuery();
			while(resultSet.next()){
				tickerData.append(resultSet.getString(3)+"  ");
				tickerData.append("$"+resultSet.getDouble(5)+"  ");
				tickerData.append("$"+resultSet.getDouble(6)+"     ");
				double perc = Math.round((resultSet.getDouble(6)/resultSet.getDouble(4))*100)/100.0d;
				tickerData.append("%"+perc+"     | ");
			}
			session.setAttribute("shareTicker", tickerData.toString());
			
			tickerData.delete(0, tickerData.length());
			for (String string : input) {
				tickerData.append(string+",");
			}
			tickerData.deleteCharAt(tickerData.length()-1);
			
			generalStatement = connection.prepareStatement(queries.getProperty("updateTickerData"));
			generalStatement.setString(1, tickerData.toString());
			generalStatement.setInt(2, (Integer)session.getAttribute("UserID"));
			generalStatement.execute();
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.disconnect();
		}
		
		String result = new aUserPref().run(request, response);
		return result;
	}
}

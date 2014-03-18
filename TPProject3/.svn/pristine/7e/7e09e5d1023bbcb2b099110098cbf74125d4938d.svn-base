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

public class aViewBrokers implements iAction {
	private Properties queries;
	private Connection connection;
	private PreparedStatement generalStatement;
	private ResultSet resultSet;
	private List<Object> tempBroker = new ArrayList<Object>();
	private List<List<Object>> brokers = new ArrayList<List<Object>>();
	private StringBuilder work;

	public aViewBrokers(){
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
		try {
			connection = ConnectionManager.getInstance().getConnection();
			generalStatement = connection.prepareStatement(queries.getProperty("getUserByType"));
			generalStatement.setInt(1, 2);
			resultSet = generalStatement.executeQuery();

			int index=-1;
			while(resultSet.next()){
				List<Object> broker = new ArrayList<Object>();
				++index;
				broker.add(resultSet.getInt(1));
				broker.add(resultSet.getString(2));
				brokers.add(index,broker);
			}

			generalStatement = connection.prepareStatement(queries.getProperty("brokerWork"));
			for (int i = 0; i <= index; i++) {
				tempBroker = brokers.get(i);
				generalStatement.setInt(1, (Integer)tempBroker.get(0));
				resultSet = generalStatement.executeQuery();
				work = new StringBuilder();
				while(resultSet.next()){
					work.append(resultSet.getString(1)+"<br>");
				}
				tempBroker.add(work);
				brokers.set(i, tempBroker);
			}

//			generalStatement = connection.prepareStatement(queries.getProperty("brokerMoney"));
//			for (int i = 0; i <= index; i++) {
//				tempBroker = brokers.get(i);
//				generalStatement.setInt(1, (Integer)tempBroker.get(0));
//				generalStatement.setInt(2, (Integer)tempBroker.get(0));
//				generalStatement.setInt(3, (Integer)tempBroker.get(0));
//				generalStatement.setInt(4, (Integer)tempBroker.get(0));
//				resultSet = generalStatement.executeQuery();
//				while(resultSet.next()){
//					try {
//						NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US); 
//						String s = n.format(resultSet.getDouble(3));
//						if(resultSet.getDouble(3)<0){
//							tempBroker.add("-"+s);
//						} else {
//							tempBroker.add(s);
//						}
//					} catch (NumberFormatException e) {
//						e.printStackTrace();
//					}
//				}
//				brokers.set(i, tempBroker);
//			}

			request.setAttribute("brokers", brokers);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.disconnect();
		}
		return "WEB-INF/SHPages/ViewBrokers.jsp";
	}

}

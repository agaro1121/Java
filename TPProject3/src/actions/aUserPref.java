package actions;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.Factory;
import storage.ConnectionManager;
import classInterfaces.iAction;
import classInterfaces.iStorage;

public class aUserPref implements iAction{

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		Map<String,String> admins = new HashMap<String,String>();
			iStorage storage = (iStorage) Factory.getFactory().getInstance("sOracle");
			admins = storage.getUserByType(1);
			session.setAttribute("admins", admins);
			
			if((Boolean) session.getAttribute("isShare_Holder")){
				try {
					Properties queries = new Properties();
					InputStream is = this.getClass().getResourceAsStream("/Queries.xml");
					queries.loadFromXML(is);
					Connection connection = ConnectionManager.getInstance().getConnection();
					PreparedStatement generalStatement = connection.prepareStatement(queries.getProperty("getUserDetails"));
					generalStatement.setString(1, (String)session.getAttribute("Username"));
					ResultSet resultSet = generalStatement.executeQuery();
					while(resultSet.next()){
						session.setAttribute("ticker", resultSet.getString(3));
					}
				} catch (InvalidPropertiesFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		return "WEB-INF/UserPref.jsp";
	}

}

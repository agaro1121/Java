package actions;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import storage.ConnectionManager;
import classInterfaces.iAction;

public class aChooseTheme implements iAction{
	Properties queries;
	Connection connection;
	PreparedStatement generalStatement;
	HttpSession session;
	
	
	public aChooseTheme(){
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
		session = request.getSession(false);
		session.setAttribute("theme", request.getParameter("theme"));
		try{
		connection = ConnectionManager.getInstance().getConnection();
		generalStatement = connection.prepareStatement(queries.getProperty("changeTheme"));
		
		generalStatement.setString(1, request.getParameter("theme"));
		generalStatement.setString(2, request.getSession(false).getAttribute("Username").toString());
		
		generalStatement.execute();
		connection.commit();
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		ConnectionManager.disconnect();
	}
		
		return "WEB-INF/UserPref.jsp";
	}

}

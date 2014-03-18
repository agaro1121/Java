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
import javax.servlet.http.HttpSession;

import main.CheckPermissions;
import main.mLogin;
import storage.ConnectionManager;
import classInterfaces.iAction;

public class aLogin implements iAction {
	private HttpSession session;
	aSetShareTicker test = new aSetShareTicker();

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response){
		String nextPage = new String();
		session = request.getSession(false);
		session.setAttribute("Username", request.getParameter("Username"));
		getUserDetails();

		boolean isVerified = new mLogin(request.getParameter("Username"),request.getParameter("Password")).execute();

		if(isVerified){
			CheckPermissions sc = new CheckPermissions();
			List<String> perm = new ArrayList<String>();

			perm.addAll(sc.execute(request.getParameter("Username")));

			for (String string : perm) {
				if(string.equalsIgnoreCase("Admin")){
					session.setAttribute("isAdmin", true);
				}
				if(string.equalsIgnoreCase("Broker")){
					session.setAttribute("isBroker", true);
				}
				if(string.equalsIgnoreCase("SE Manager")){
					session.setAttribute("isSE_Manager", true);
				}
				if(string.equalsIgnoreCase("Share Holder")){
					session.setAttribute("isShare_Holder", true);
				}
			}
			request.setAttribute("perm", perm);
			nextPage = "WEB-INF/UserOptions.jsp";
		}else{
			nextPage = "Fail.jsp";
		}
		
		if(session.getAttribute("ticker") != null){
			test.run(request, response);
		}
		return nextPage;
	}
	private void getUserDetails(){
		try {

			Properties queries = new Properties();
			InputStream is = this.getClass().getResourceAsStream("/Queries.xml");
			queries.loadFromXML(is);
			Connection connection = ConnectionManager.getInstance().getConnection();
			PreparedStatement generalStatement = connection.prepareStatement(queries.getProperty("getUserDetails"));
			generalStatement.setString(1, (String)session.getAttribute("Username"));
			ResultSet resultSet = generalStatement.executeQuery();
			while(resultSet.next()){
				session.setAttribute("UserID", resultSet.getInt(1));
				session.setAttribute("theme", resultSet.getString(2));
				session.setAttribute("ticker", resultSet.getString(3));
				if(resultSet.getString(3) == null){
				} else {
					String[] tickerID = resultSet.getString(3).toString().split(",");
					test.setStocks(tickerID);
				}

			}

			generalStatement = connection.prepareStatement(queries.getProperty("checkBoxStocks"));
			resultSet = generalStatement.executeQuery();
			List<Object[]> checkBoxStocks = new ArrayList<Object[]>();
			while(resultSet.next()){
				checkBoxStocks.add(new Object[]{resultSet.getInt(1),resultSet.getString(2)});
			}
			session.setAttribute("checkBoxStocks", checkBoxStocks);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.disconnect();
		}
	}
}

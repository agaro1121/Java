package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.ChangePassword;
import classInterfaces.iAction;

public class aChangePassword implements iAction{

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {

			ChangePassword.getInstance().reset((String)request.getSession(false).getAttribute("Username"), request.getParameter("newPassword"), 1);
			return "WEB-INF/UserOptions.jsp";
	
		
	}

}
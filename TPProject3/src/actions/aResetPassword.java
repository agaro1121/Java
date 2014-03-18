package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.ChangePassword;
import classInterfaces.iAction;

public class aResetPassword implements iAction {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {

		ChangePassword.getInstance().reset(request.getParameter("Username"),null,0);
			return "Success.jsp";
		
	}

}

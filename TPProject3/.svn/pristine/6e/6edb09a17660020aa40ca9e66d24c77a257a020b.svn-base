package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classInterfaces.iAction;

public class aLogout implements iAction{

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {

			request.getSession(false).invalidate();

		return "HelloWorld.jsp";
	}

}

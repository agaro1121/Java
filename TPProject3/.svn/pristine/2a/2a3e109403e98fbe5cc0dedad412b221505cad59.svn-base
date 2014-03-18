package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classInterfaces.iAction;

public class aRequestTrade implements iAction {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		
		new aViewShares().run(request, response);
		new aViewAvailableStock().run(request, response);
				
		return "WEB-INF/SHPages/RequestTrade.jsp";
	}

}

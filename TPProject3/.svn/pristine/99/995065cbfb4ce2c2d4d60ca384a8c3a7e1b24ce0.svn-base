package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.Factory;
import classInterfaces.iAction;
import classInterfaces.iView;

public class aAdminPage implements iAction {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {

			Factory.getFactory().getInstance("cAdmin");
			iView view = (iView)Factory.getFactory().getInstance("guiAdmin");
			view.setVisibility(true);
	
		return "WEB-INF/UserOptions.jsp";
	}

}

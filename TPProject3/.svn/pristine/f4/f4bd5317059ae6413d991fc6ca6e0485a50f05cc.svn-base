package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.mRegister;
import storage.User;
import classInterfaces.iAction;

public class aRegister implements iAction {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("registerButton");
		User newUser = new User();
			if(!(request.getParameter("isAdmin") == null)){
				newUser.setAdmin(true);
			}
			if(!(request.getParameter("isBroker") == null)){
				newUser.setBroker(true);
			}
			if(!(request.getParameter("isSE_Manager") == null)){
				newUser.setSE_Manager(true);
			}
			if(!(request.getParameter("isShare_Holder") == null)){
				newUser.setShare_Holder(true);
			}			

			newUser.setFirstname(request.getParameter("firstname").toString());
			newUser.setLastname(request.getParameter("lastname").toString());
			newUser.setPassword(request.getParameter("password").toString());
			
			boolean rSuccess = new mRegister(newUser).execute();
			
			return (rSuccess) ? "Success.jsp" : "Fail.jsp";
			
//			if(rSuccess){
//				response.sendRedirect("Success.jsp");
//				nextPage = "Success.jsp";
//			}else{
//				response.sendRedirect("Fail.jsp");
//				nextPage = "Fail.jsp";
//			}
	}
}

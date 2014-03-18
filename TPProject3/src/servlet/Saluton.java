package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.Factory;

import org.apache.log4j.PropertyConfigurator;

import classInterfaces.iAction;


//TODO become client of specific broker
//TODO figure out how to setup command pattern to work with parameters !!!!!!!!!
//TODO get rid of User setters?
//TODO make sure user refresh table refreshes correctly
//TODO add button for viewing specific admin requests
//TODO move Storage.updateRequest() to command pattern
//TODO monitor un-owned shares they might be interested in???
//TODO create lists of favorite shares in their existing portfolio???
//TODO replace AppLiterals with xml?

@WebServlet("/Saluton")
public class Saluton extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Saluton() {
		PropertyConfigurator.configure(this.getClass().getResourceAsStream("/log4j.properties"));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processInput(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processInput(request,response);
	}
	
	protected void processInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		response.setHeader("Cache-Control", "no-cache");
		//		response.setHeader("Cache-Control", "no-store");
		//		response.setDateHeader("Expires", 0);
		iAction generalAction = (iAction) Factory.getFactory().getInstance(request.getParameter("sender").toString());

		if(request.getSession(false) != null){
			request.getRequestDispatcher(generalAction.run(request, response)).forward(request, response);
		}else{
			response.sendRedirect("Fail.jsp");
		}

	}
}
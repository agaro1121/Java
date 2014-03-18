package classInterfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface iAction {
	
	public String run(HttpServletRequest request, HttpServletResponse response);

}

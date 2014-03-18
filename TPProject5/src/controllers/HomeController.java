package controllers;

import java.math.BigDecimal;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import storage.GenericDAO;
import storage.User;
import storage.UserDAO;

/**
 * The Home controller handles log on, log off, change password, choosing themes, and sending requests to admins
 * for faults and other things 
 * @author anthony.garo
 *
 */

@Component
@Controller
public class HomeController {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private GenericDAO genericDAO;
	@Autowired
	@Qualifier("queries")
	private Properties queries;
	private boolean success;

	@RequestMapping({"/","index","welcome","home","login"})
	public ModelAndView index(ModelMap model){
		model.addAttribute("test", 3);
		User user = new User();
		return new ModelAndView("login","user",user);

	}

	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public String login(ModelMap model, @ModelAttribute("user") User user){
		try {
			String pwFromInput = user.getPassword();
			user = userDAO.getUser(user.getUsername());
			String pwFromDB = user.getPassword();
			user.setPassword(null);
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			attr.getRequest().getSession(false).setAttribute("user", user);
			attr.getRequest().getSession(false).setAttribute("stockExID", userDAO.getUserSE(user.getUserID()));
			if(user.isVerified() && user.isSeManager() && pwFromDB.equals(pwFromInput)){
				success = true;
				return "redirect:/manager";
			}else{
				success = false;
				return "Fail";
			}
			//			return (user.isVerified() && user.isSeManager() && pwFromDB.equals(pwFromInput)) ? "redirect:/manager":"Fail";
		} catch (Exception e) {
			return "Fail";
		}
	}

	@RequestMapping({"/logoff","/logout"})
	public String logOff(ModelMap model){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		attr.getRequest().getSession(false).removeAttribute("user");
		attr.getRequest().getSession(false).invalidate();
		return "redirect:/";
	}

	@RequestMapping(value="/changePassword",method=RequestMethod.POST)
	public String resetPassword(ModelMap model,@RequestParam("newPassword") String newPW){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		User user = (User) attr.getRequest().getSession(false).getAttribute("user");
		genericDAO.executeQuery(queries.getProperty("resetPassword"), new Object[]{newPW,user.getUserID()});
		return "redirect:/manager";
	}

	@RequestMapping(value="/createRequest",method=RequestMethod.POST)
	public String createRequest(ModelMap model, @RequestParam("description") String description, @RequestParam(value="admin",required=false) int admin){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		User user = (User) attr.getRequest().getSession(false).getAttribute("user");
		int requestID = ((BigDecimal) genericDAO.queryFieldReturn("SELECT MAX(request_id) FROM user_request")).intValue() + 1;
		genericDAO.executeQuery(queries.getProperty("submitRequest"), new Object[]{user.getUserID(),
			description,"New",admin,requestID});
		return "redirect:/manager";
	}

	@RequestMapping(value="/chooseTheme")
	public String chooseTheme(ModelMap model,@RequestParam("theme") String theme){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		User user = (User) attr.getRequest().getSession(false).getAttribute("user");
		user.setTheme(theme);
		attr.getRequest().getSession(false).setAttribute("user", user);
		genericDAO.executeQuery(queries.getProperty("chooseTheme"), new Object[]{theme,user.getUserID()});
		return "redirect:/manager";
	}

}

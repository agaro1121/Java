package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import storage.GenericDAO;

/**
 * The Broker controller handles all broker actions
 * @author anthony.garo
 *
 */

@Component
@Controller
public class BrokerController {
	@Autowired
	private GenericDAO genericDAO;
	@Autowired
	@Qualifier("queries")
	private Properties queries;
	private List<List<Object>> objects = new ArrayList<List<Object>>();
	private int stockExID;
	
	
	@RequestMapping(value="/bManagement",method=RequestMethod.GET)
	public String bManagement(ModelMap model){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		stockExID = (Integer) attr.getRequest().getSession(false).getAttribute("stockExID");
		model.addAttribute("objectType", "Broker");
		return "ManagementPage";
	}
	
	@RequestMapping(value="/Brokers",method=RequestMethod.GET)
	public ModelAndView suspendBrokersPage(ModelMap model){
		model.addAttribute("objectType", "Brokers");
		model.addAttribute("action", "suspended");
		model.addAttribute("buttonValue","Suspend");
		model.addAttribute("actionType", 1);
		objects = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("brokerByStockExID"), new Object[]{stockExID});
		return new ModelAndView("ActionPage","objects",objects);
	}
	
	@RequestMapping(value="/tradesB/{userID}",method=RequestMethod.GET)
	public ModelAndView tradesB(ModelMap model, @PathVariable("userID") int userID){
		List<List<Object>> trades = new ArrayList<List<Object>>();
		trades = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("tradesByBroker"), new Object[]{userID,userID,stockExID});
		return new ModelAndView("SpecificTradesB","trades",trades);
	}
	
	@RequestMapping(value={"/SuspendB.do","/unSuspendB.do"},method=RequestMethod.POST)
	public String suspendBrokers(ModelMap model,@RequestParam(value = "userIDs",required = false) Object[] users, @RequestParam("action") String suspend){
		List<Object[]> params = new ArrayList<Object[]>();
		if(users != null){
			for (int i = 0; i < users.length; i++) {
				params.add(new Object[]{suspend, Integer.parseInt(users[i].toString()),stockExID});
			}
			genericDAO.batchUpdate(queries.getProperty("bStatus"), params);
		}
		return "redirect:/bManagement";
	}

	@RequestMapping(value="/unSuspendB",method=RequestMethod.GET)
	public ModelAndView unSuspendPageB(ModelMap model){
		model.addAttribute("objectType", "Brokers");
		model.addAttribute("action", "active");
		model.addAttribute("buttonValue","Un-Suspend");
		model.addAttribute("actionType", 2);
		objects = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("suspendedBrokerByStockExID"), new Object[]{stockExID});
		return new ModelAndView("ActionPage","objects",objects);
	}
	
	@RequestMapping(value="/DenyBrokers",method=RequestMethod.GET)
	public ModelAndView denyB(ModelMap model){
		model.addAttribute("objectType", "Brokers");
		model.addAttribute("action", "denied");
		model.addAttribute("buttonValue","Deny");
		model.addAttribute("actionType", 4);
		objects = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("BrokersToBeDenied"),new Object[]{stockExID});
		return new ModelAndView("ActionPage","objects",objects);
	}
	
	@RequestMapping(value="/ApproveBrokers",method=RequestMethod.GET)
	public ModelAndView approveSH(ModelMap model){
		model.addAttribute("objectType", "Brokers");
		model.addAttribute("action", "active");
		model.addAttribute("buttonValue","Approve");
		model.addAttribute("actionType", 3);
		objects = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("BrokersToBeApproved"),new Object[]{stockExID});
		return new ModelAndView("ActionPage","objects",objects);
	}
	
	@RequestMapping(value={"/DenyB.do","/ApproveB.do"},method=RequestMethod.POST)
	public String denyApproveBroker(ModelMap model,@RequestParam(value = "userIDs",required = false) Object[] users, @RequestParam("action") String action){
		List<Object[]> params = new ArrayList<Object[]>();
		if(users != null){
			for (int i = 0; i < users.length; i++) {
				params.add(new Object[]{Integer.parseInt(users[i].toString()),stockExID,action});
			}
			genericDAO.batchUpdate(queries.getProperty("approveDenyB"), params);
		}
		return "redirect:/bManagement";
	}
	
	@RequestMapping(value="/ApproveCurrentBrokers",method=RequestMethod.GET)
	public ModelAndView approveCurrentB(ModelMap model){
		model.addAttribute("objectType", "Brokers");
		model.addAttribute("action", "active");
		model.addAttribute("buttonValue","Approve");
		model.addAttribute("actionType", 5);
		objects = (List<List<Object>>) genericDAO.queryNoParamsMany(queries.getProperty("currDeniedB"));
		return new ModelAndView("ActionPage","objects",objects);
	}
	@RequestMapping(value={"/ApproveCurrB.do"},method=RequestMethod.POST)
	public String approveCurrentBroker(ModelMap model,@RequestParam(value = "userIDs",required = false) Object[] users, @RequestParam("action") String action){
		List<Object[]> params = new ArrayList<Object[]>();
		if(users != null){
			for (int i = 0; i < users.length; i++) {
				params.add(new Object[]{action,Integer.parseInt(users[i].toString()),stockExID});
			}
			genericDAO.batchUpdate(queries.getProperty("bStatus"), params);
		}
		return "redirect:/bManagement";
	}
}

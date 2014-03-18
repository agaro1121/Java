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
 * The shareholder controller handles all shareholder actions
 * @author anthony.garo
 *
 */

@Component
@Controller
public class SHController {
	@Autowired
	private GenericDAO genericDAO;
	@Autowired
	@Qualifier("queries")
	private Properties queries;
	private List<List<Object>> objects = new ArrayList<List<Object>>();
	private int stockExID;

	@RequestMapping(value="/shManagement",method=RequestMethod.GET)
	public String shManagement(ModelMap model){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		stockExID = (Integer) attr.getRequest().getSession(false).getAttribute("stockExID");
		model.addAttribute("objectType", "ShareHolder");
		return "ManagementPage";
	}
	
	@RequestMapping(value="/trades/{userID}",method=RequestMethod.GET)
	public ModelAndView trades(ModelMap model, @PathVariable("userID") int userID){
		List<List<Object>> trades = new ArrayList<List<Object>>();
		trades = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("tradesBySH"), new Object[]{userID,userID,stockExID});
		return new ModelAndView("SpecificTrades","trades",trades);
	}

	@RequestMapping(value="/ShareHolders",method=RequestMethod.GET)
	public ModelAndView shareHolders(ModelMap model){
		model.addAttribute("objectType", "ShareHolders");
		model.addAttribute("action", "suspended");
		model.addAttribute("buttonValue","Suspend");
		model.addAttribute("actionType", 1);
		objects = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("shByStockExID"), new Object[]{stockExID});
		return new ModelAndView("ActionPage","objects",objects);
	}

	@RequestMapping(value="/unSuspend",method=RequestMethod.GET)
	public ModelAndView unSuspendPage(ModelMap model){
		model.addAttribute("objectType", "ShareHolders");
		model.addAttribute("action", "active");
		model.addAttribute("buttonValue","Un-Suspend");
		model.addAttribute("actionType", 2);
		objects = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("suspendedSHByStockExID"), new Object[]{stockExID});
		return new ModelAndView("ActionPage","objects",objects);
	}

	@RequestMapping(value={"/Suspend.do","/unSuspend.do"},method=RequestMethod.POST)
	public String suspendShareHolders(ModelMap model,@RequestParam(value = "userIDs",required = false) Object[] users, @RequestParam("action") String suspend){
		List<Object[]> params = new ArrayList<Object[]>();
		if(users != null){
			for (int i = 0; i < users.length; i++) {
				params.add(new Object[]{suspend, Integer.parseInt(users[i].toString()),stockExID});
			}
			genericDAO.batchUpdate(queries.getProperty("SHStatus"), params);
		}
		return "redirect:/shManagement";
	}

	@RequestMapping(value="/allSharesOwned",method=RequestMethod.GET)
	public ModelAndView allSharesOwned(ModelMap model){
		List<List<List<Object>>> ownedShares = new ArrayList<List<List<Object>>>();
		List<Object[]> queriesToRun = new ArrayList<Object[]>();
		objects = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("shByStockExID"), new Object[]{stockExID});
		for (List<Object> row : objects) {
			queriesToRun.add(new Object[]{queries.getProperty("sharesHeld"), new Object[]{row.get(0),row.get(0),row.get(0),stockExID,row.get(0)}});
		}
		ownedShares = (List<List<List<Object>>>) genericDAO.transactionReturn(queriesToRun);
		return new ModelAndView("AllSharesOwned","ownedShares",ownedShares);
	}

	@RequestMapping(value="/allSharesOwned/{userID}",method=RequestMethod.GET)
	public ModelAndView SharesOwned(ModelMap model, @PathVariable("userID") int userID){
		List<List<List<Object>>> ownedShares = new ArrayList<List<List<Object>>>();
		ownedShares.add( (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("sharesHeld"), new Object[]{userID,userID,userID,stockExID,userID}) );

		return new ModelAndView("SpecificSharesOwned","ownedShares",ownedShares);
	}
	
	@RequestMapping(value="/DenyShareHolders",method=RequestMethod.GET)
	public ModelAndView denySH(ModelMap model){
		model.addAttribute("objectType", "ShareHolders");
		model.addAttribute("action", "denied");
		model.addAttribute("buttonValue","Deny");
		model.addAttribute("actionType", 4);
		objects = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("SHToBeDenied"),new Object[]{stockExID});
		return new ModelAndView("ActionPage","objects",objects);
	}
	
	@RequestMapping(value="/ApproveShareHolders",method=RequestMethod.GET)
	public ModelAndView approveSH(ModelMap model){
		model.addAttribute("objectType", "ShareHolders");
		model.addAttribute("action", "active");
		model.addAttribute("buttonValue","Approve");
		model.addAttribute("actionType", 3);
		objects = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("SHToBeApproved"),new Object[]{stockExID});
		return new ModelAndView("ActionPage","objects",objects);
	}

	@RequestMapping(value={"/Deny.do","/Approve.do"},method=RequestMethod.POST)
	public String denyApproveShareHolders(ModelMap model,@RequestParam(value = "userIDs",required = false) Object[] users, @RequestParam("action") String action){
		List<Object[]> params = new ArrayList<Object[]>();
		if(users != null){
			for (int i = 0; i < users.length; i++) {
				params.add(new Object[]{Integer.parseInt(users[i].toString()),stockExID,action});
			}
			genericDAO.batchUpdate(queries.getProperty("approveDenySH"), params);
		}
		return "redirect:/shManagement";
	}
	
	@RequestMapping(value="/ApproveCurrentShareHolders",method=RequestMethod.GET)
	public ModelAndView approveCurrentSH(ModelMap model){
		model.addAttribute("objectType", "ShareHolders");
		model.addAttribute("action", "active");
		model.addAttribute("buttonValue","Approve");
		model.addAttribute("actionType", 5);
		objects = (List<List<Object>>) genericDAO.queryNoParamsMany(queries.getProperty("currDeniedSH"));
		return new ModelAndView("ActionPage","objects",objects);
	}
	@RequestMapping(value={"/ApproveCurrSH.do"},method=RequestMethod.POST)
	public String ApproveCurrrentShareHolders(ModelMap model,@RequestParam(value = "userIDs",required = false) Object[] users, @RequestParam("action") String action){
		List<Object[]> params = new ArrayList<Object[]>();
		if(users != null){
			for (int i = 0; i < users.length; i++) {
				params.add(new Object[]{action,Integer.parseInt(users[i].toString()),stockExID});
			}
			genericDAO.batchUpdate(queries.getProperty("SHStatus"), params);
		}
		return "redirect:/shManagement";
	}
}

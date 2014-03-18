package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import storage.GenericDAO;

@Component
@Controller
public class SEMController {
	@Autowired
	private GenericDAO genericDAO;
	@Autowired
	@Qualifier("queries")
	private Properties queries;
	private List<List<Object>> objects = new ArrayList<List<Object>>();
	private int stockExID;

	@RequestMapping({"/manager"})
	public ModelAndView managerPage(ModelMap model){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		stockExID = (Integer) attr.getRequest().getSession(false).getAttribute("stockExID");
		List<String> admins = (List<String>) genericDAO.queryParamsMany(queries.getProperty("getUserByType"), new Object[]{1});
		return new ModelAndView("ManagerPage","admins",admins);
	}

	@RequestMapping({"/trades","/tradesB"})
	public ModelAndView trades(ModelMap model){
		List<List<Object>> trades = new ArrayList<List<Object>>();
		trades = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("allTrades"),new Object[]{stockExID});
		return new ModelAndView("Trades","trades",trades);
	}

	@RequestMapping(value="/allSharesOwnedSHB",method=RequestMethod.GET)
	public ModelAndView allSharesOwnedSHB(ModelMap model){
		List<List<List<Object>>> ownedShares = new ArrayList<List<List<Object>>>();
		objects = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("bNSH"), new Object[]{stockExID});
		for (List<Object> row : objects) {
			ownedShares.add( (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("sharesHeld"), new Object[]{row.get(0),row.get(0),row.get(0),stockExID,row.get(0)}) );
		}
		return new ModelAndView("AllSharesOwned","ownedShares",ownedShares);
	}

	@RequestMapping(value="/tradesBSH",method=RequestMethod.GET)
	public ModelAndView allTradesByBSH(ModelMap model){
		objects = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("allTradesByBSH"), new Object[]{stockExID,stockExID,stockExID,stockExID});
		return new ModelAndView("TradesBSH","trades",objects);
	}

	@RequestMapping(value="/tradesBSH/{userID}",method=RequestMethod.GET)
	public ModelAndView tradesBSH(ModelMap model, @PathVariable("userID") int userID){
		objects = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("tradesByBSH"), new Object[]{stockExID,userID,stockExID,userID});
		return new ModelAndView("SpecificTradesB","trades",objects);
	}
}

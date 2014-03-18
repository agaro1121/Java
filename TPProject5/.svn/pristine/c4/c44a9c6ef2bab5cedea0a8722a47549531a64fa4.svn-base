package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import storage.GenericDAO;

/**
 * Company controller handles all company actions
 * @author anthony.garo
 *
 */

@Component
@Controller
public class CompanyController {
	@Autowired
	private GenericDAO genericDAO;
	@Autowired
	@Qualifier("queries")
	private Properties queries;
	private List<List<Object>> objects = new ArrayList<List<Object>>();
	private int stockExID;

	@RequestMapping(value="/cManagement",method=RequestMethod.GET)
	public String cManagement(ModelMap model){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		stockExID = (Integer) attr.getRequest().getSession(false).getAttribute("stockExID");
		model.addAttribute("objectType", "Company");
		return "ManagementPage";
	}

	@RequestMapping(value="/Companies",method=RequestMethod.GET)
	public ModelAndView companies(ModelMap model){
		model.addAttribute("objectType", "Companies");
		model.addAttribute("action", "delisted");
		model.addAttribute("buttonValue","De-List");
		model.addAttribute("actionType", 2);
		objects = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("listedCompanies"), new Object[]{stockExID});
		return new ModelAndView("ActionPage","objects",objects);
	}

	@RequestMapping(value={"/DeList.do","/List.do"},method=RequestMethod.POST)
	public String listDeList(ModelMap model,@RequestParam(value = "userIDs",required = false) Object[] users, @RequestParam("action") String action){
		List<Object[]> params = new ArrayList<Object[]>();
		if(users != null){
			for (int i = 0; i < users.length; i++) {
				params.add(new Object[]{action, Integer.parseInt(users[i].toString()),stockExID});
			}
			genericDAO.batchUpdate(queries.getProperty("cStatus"), params);
		}
		return "redirect:/cManagement";
	}

	@RequestMapping(value="/listCompany",method=RequestMethod.GET)
	public ModelAndView unSuspendPage(ModelMap model){
		model.addAttribute("objectType", "Companies");
		model.addAttribute("action", "listed");
		model.addAttribute("buttonValue","List");
		model.addAttribute("actionType", 1);
		objects = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("deListedCompanies"), new Object[]{stockExID});
		return new ModelAndView("ActionPage","objects",objects);
	}

	@RequestMapping(value="/SharesIssued")
	public ModelAndView sharesIssuedPage(ModelMap model){
		objects = (List<List<Object>>) genericDAO.queryParamsMany(queries.getProperty("sharesIssued"),new Object[]{stockExID});
		return new ModelAndView("SharesIssued","sharesIssued",objects);
	}

	@RequestMapping(value="/updateShareAmount.do")
	public String updateShareAmount(ModelMap model,@RequestParam("stockID") int stockID,@RequestParam("amount") double amount){
		List<Object[]> queriesToRun = new ArrayList<Object[]>();
		queriesToRun.add(new Object[]{queries.getProperty("updateAmountA"), new Object[]{stockID}});
		queriesToRun.add(new Object[]{queries.getProperty("updateAmountB"), new Object[]{stockID,stockID}});
		queriesToRun.add(new Object[]{queries.getProperty("updateAmountC"), new Object[]{(int)amount,stockID}});
		genericDAO.transaction(queriesToRun);
		return "redirect:/SharesIssued";
	}

	@RequestMapping(value="/updateSharePrice.do")
	public String updateSharePrice(ModelMap model,@RequestParam("stockID") int stockID,@RequestParam("amount") double amount){
		List<Object[]> queriesToRun = new ArrayList<Object[]>();
		queriesToRun.add(new Object[]{queries.getProperty("updatePriceA"), new Object[]{stockID}});
		queriesToRun.add(new Object[]{queries.getProperty("updatePriceB"), new Object[]{stockID,stockID}});
		queriesToRun.add(new Object[]{queries.getProperty("updatePriceC"), new Object[]{amount,stockID}});
		genericDAO.transaction(queriesToRun);
		return "redirect:/SharesIssued";
	}
}
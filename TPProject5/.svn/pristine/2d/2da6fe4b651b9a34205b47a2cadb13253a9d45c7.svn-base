package controllers;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import storage.User;

@Component
@Aspect
public class SessionAspect {
	private static Logger loginLog = Logger.getLogger("LoginLog");
	private static Logger cLog = Logger.getLogger("CompanyManagement");
	private static Logger bLog = Logger.getLogger("BrokerManagement");
	private static Logger shLog = Logger.getLogger("ShareHolderManagement");
	private static Logger errorLog = Logger.getLogger("ErrorLog");

	/**
	 * Checks if the user is still logged in before every actions is executed
	 * @param jp
	 * @return String OR ModelAndView to the index page if user is logged off otherwise method proceeds
	 * @throws Throwable
	 */
	@Around("execution (* controllers.*.*(..)) and !execution(* controllers.HomeController.index(..)) and !execution(* controllers.HomeController.login(..))")
	public Object checkPermission(ProceedingJoinPoint jp) throws Throwable{
		MethodSignature signature = (MethodSignature) jp.getSignature();
		Method method = signature.getMethod();
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		if(attr.getRequest().getSession(false).getAttribute("user") != null){
			return jp.proceed();
		}else{
			Class<?> returnType = (Class<?>) Class.forName(method.getReturnType().getCanonicalName());
			Object object = returnType.newInstance();
			if(object instanceof String){
				attr.getRequest().setAttribute("user", new User());
				return "login";
			}else{
				return new ModelAndView("login","user",new User());
			}
		}
	}
	/*********************************** LOGIN ON AND OFF 
	 * Logs whether a user successfully logged
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException ************************************************/	
	@AfterReturning("execution (* controllers.HomeController.login(..)) returning String result")
	public void loginLog(JoinPoint jp) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
		Field test = jp.getTarget().getClass().getDeclaredField("success");
		test.setAccessible(true);
		boolean loginResult = test.getBoolean(jp.getTarget());
		try {

			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			User user = (User) attr.getRequest().getSession(false).getAttribute("user");

			if(loginResult == true){
				loginLog.info(user.getUsername()+" "+"Logged in Successfully");	
			}else{
				loginLog.info(user.getUsername()+" did not log in successfully");
			}
		} catch (Exception e) {
			loginLog.info("User did not log in successfully");
			errorLog.info(e.toString());
		}
	}
	/**
	 * Logs when a user logs off
	 */
	@Before("execution (* controllers.HomeController.logOff(..))")
	public void logoffLog(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		User user = (User) attr.getRequest().getSession(false).getAttribute("user");
		loginLog.info(user.getUsername() + " Logged Off");
	}

	/*********************************** Broker ************************************************/
	/**
	 * Logs when a broker is suspended or UnSuspended
	 * @param jp
	 * @throws Throwable
	 */
	@After("execution (* controllers.BrokerController.suspendBrokers(..))")
	public void bSuspensionLog(JoinPoint jp) throws Throwable{
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		User user = (User) attr.getRequest().getSession(false).getAttribute("user");
		Object[] args = jp.getArgs();
		Object[] ids = (Object[]) args[1];
		StringBuilder message = new StringBuilder();
		message.setLength(0);

		if(args[2].equals("active")){
			message.append(user.getUsername()+" Un-Suspended: ");
			for (Object string : ids) {
				System.out.print(string+" ");
				message.append(string+" ");
			}
			bLog.info(message);
		}else{
			message.append(user.getUsername()+" Suspended: ");
			System.out.print("BrokerLog: "+user.getUsername()+" Suspended: ");
			for (Object string : ids) {
				message.append(string+" ");
			}
			bLog.info(message);
		}
	}

	/**
	 * Logs when a user approves/denies a broker
	 * @param jp
	 * @throws Throwable
	 */
	@After("execution (* controllers.BrokerController.denyApproveBroker(..)) || execution (* controllers.BrokerController.approveCurrentBroker(..))")
	public void bApproveDenyLog(JoinPoint jp) throws Throwable{
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		User user = (User) attr.getRequest().getSession(false).getAttribute("user");
		Object[] args = jp.getArgs();
		Object[] ids = (Object[]) args[1];
		StringBuilder message = new StringBuilder();
		message.setLength(0);

		if(args[2].equals("active")){
			message.append(user.getUsername() +" Approved: ");
			for (Object string : ids) {
				message.append(string+" ");
			}
			bLog.info(message);
		}else{
			message.append(user.getUsername()+" Denied: ");
			for (Object string : ids) {
				message.append(string+" ");
			}
			bLog.info(message);
		}
	}

	/*********************************** ShareHolder ************************************************/
	/**
	 * Logs when a shareholder is suspended and Unsuspended
	 * @param jp
	 * @throws Throwable
	 */
	@After("execution (* controllers.SHController.suspendShareHolders(..))")
	public void shSuspensionLog(JoinPoint jp) throws Throwable{
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		User user = (User) attr.getRequest().getSession(false).getAttribute("user");
		Object[] args = jp.getArgs();
		Object[] ids = (Object[]) args[1];
		StringBuilder message = new StringBuilder();
		message.setLength(0);

		if(args[2].equals("active")){
			message.append(user.getUsername()+" Un-Suspended: ");
			for (Object string : ids) {
				message.append(string+" ");
			}
			shLog.info(message);
		}else{
			message.append(user.getUsername()+" Suspended: ");
			for (Object string : ids) {
				message.append(string+" ");
			}
			shLog.info(message);
		}
	}

	/**
	 * Logs when a shareholder is approved/denied
	 * @param jp
	 * @throws Throwable
	 */
	@After("execution (* controllers.SHController.denyApproveShareHolders(..)) || execution (* controllers.SHController.ApproveCurrrentShareHolders(..))")
	public void shApproveDenyLog(JoinPoint jp) throws Throwable{
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		User user = (User) attr.getRequest().getSession(false).getAttribute("user");
		Object[] args = jp.getArgs();
		Object[] ids = (Object[]) args[1];
		StringBuilder message = new StringBuilder();
		message.setLength(0);

		if(args[2].equals("active")){
			message.append(user.getUsername()+" Approved: ");
			for (Object string : ids) {
				message.append(string+" ");
			}
			shLog.info(message);
		}else{
			message.append(user.getUsername()+" Denied: ");
			for (Object string : ids) {
				message.append(string+" ");
			}
			shLog.info(message);
		}
	}

	/*********************************** Company ************************************************/
	/**
	 * Logs when a company is listed and delisted
	 * @param jp
	 * @throws Throwable
	 */
	@After("execution (* controllers.CompanyController.listDeList(..))")
	public void listDeListLog(JoinPoint jp) throws Throwable{
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		User user = (User) attr.getRequest().getSession(false).getAttribute("user");
		Object[] args = jp.getArgs();
		Object[] ids = (Object[]) args[1];
		StringBuilder message = new StringBuilder();
		message.setLength(0);

		if(args[2].equals("listed")){
			message.append(user.getUsername()+" Listed: ");
			for (Object string : ids) {
				message.append(string+" ");
			}
			cLog.info(message);
		}else{
			message.append(user.getUsername()+" DeListed: ");
			for (Object string : ids) {
				message.append(string+" ");
			}
			cLog.info(message);
		}
	}

	/*************************************** ERRORS ***************************************************/
	/**
	 * Logs when an error occurs
	 * @param e
	 */
	@AfterThrowing(pointcut="execution (* *.*.*(..))",throwing="e")
	public void errorLog(Exception e){
		errorLog.info(e.toString());
	}

}

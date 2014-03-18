package anthony.garo.Main;

import java.util.HashMap;

import org.apache.log4j.Logger;

import Interfaces.Model;
import anthony.garo.Storage.DTO;
import anthony.garo.Storage.Storage;

public class Login_Model implements Model{
	private HashMap login = new HashMap();
	private Logger loginLog = Logger.getLogger("LoginLog");

	public boolean execute(String username, String password){
		Boolean isVerified = false;

		login.putAll(Storage.getInstance().getLoginHash());

		isVerified = ((password.equals(login.get(username))) ? isVerified=true:isVerified); 

		if(isVerified){
			loginLog.trace(username + " Logged in");
		}else{loginLog.trace(username + " attempted to Log in");} 
		return isVerified;
	}



	/* NOT REQUIRED */
	public boolean execute(){return false;}
	public boolean execute(DTO dto){return false;}



}

package main;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import storage.DTO;
import storage.Storage_Oracle;
import classInterfaces.Interface_Model;
import classInterfaces.Interface_Storage;
import factories.Factory_Storage;

public class Model_Login implements Interface_Model{
	private Map<String,String> login = new HashMap<String,String>();
	private Logger loginLog = Logger.getLogger("LoginLog");
	private Interface_Storage storage = Factory_Storage.getStorage();
	
	public boolean execute(String username, String password){
		boolean isVerified = false;
		login.putAll(storage.getLoginHash());

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

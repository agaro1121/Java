package main;

import java.util.HashMap;
import java.util.Map;

import classInterfaces.Interface_Storage;
import factories.Factory_Storage;

public class StatusCheck{
	private Interface_Storage storage = Factory_Storage.getStorage();

	public String execute(String username) {
		//TODO get hash from command pattern
		//TODO only get specific user and all his types
		//		2. add his types to a list
		//		3. search for admin (list.contains)
		//		4. ? return string = admin : return something else

		Map<String,String> usersNStatus = new HashMap<String,String>();
		usersNStatus.putAll(storage.getUserByType(0));

		return usersNStatus.get(username);
	}

}









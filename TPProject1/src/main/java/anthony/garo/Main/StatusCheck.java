package anthony.garo.Main;

import java.util.ArrayList;
import java.util.List;

import anthony.garo.Storage.Storage;

public class StatusCheck{

	public String execute(String username) {

		String result = new String();
		List<User> status = new ArrayList<User>();
		status = Storage.getInstance().XMLToUser();

		for (User user : status) 
		{
			if(user.getUsername().equals(username)){
				result = user.getStatus();
				break;
			}else{result = "fail";}
		}


		return result;
	}

}









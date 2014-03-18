package main;

import java.util.ArrayList;
import java.util.List;

import storage.DTO;
import storage.Status;
import classInterfaces.Interface_Controller;
import classInterfaces.Interface_Storage;
import classInterfaces.Interface_View;

import commandReceivers.Receiver_Admin;

import factories.Factory_Storage;
import factories.Factory_View;
public class Controller_Admin implements Interface_Controller {
	private List<String> users = new ArrayList<String>();
	private Interface_Storage storage = Factory_Storage.getStorage();
	private Interface_View view = Factory_View.getView(Status.ADMIN);

	public Controller_Admin(){
		view.setController(this);
		execute();
	}

	public void getInput(Object command){
		users = storage.getUserByStatus((String) command);
		for (String user : users) {
			view.display(user);
		}
	}

	public void execute() {
		view.getInitialDisplayNUserInput();
	}

	//USED DIFFERENTLY, PASSWORD IS SUPPOSED TO BE 'STATUS'
	public void getInput(String username, String password) {
		Receiver_Admin.setUsername(username);

		if(password.equals(Status.ADD_USER)){
			Mediator.getInstance().handle(Status.ADD_USER);
		}
		else if(password.equals(Status.BAN_USER)){
			Mediator.getInstance().handle(Status.BAN_USER);
		}
		else if(password.equals(Status.REMOVE_USER)){
			Mediator.getInstance().handle(Status.REMOVE_USER);
		}
	}
	//NOT NEEDED
	public void getInput(DTO input) {}
}

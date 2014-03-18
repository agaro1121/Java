package main;

import java.util.ArrayList;
import java.util.List;

import classInterfaces.iCommand;
import classInterfaces.iController;
import classInterfaces.iStorage;
import classInterfaces.iView;

import commands.SystemControls;

public class Controller_Admin implements iController {
	private List<String> users = new ArrayList<String>();
	private iStorage storage = (iStorage)Factory.getFactory().getInstance("sOracle");
	private iView view = (iView)Factory.getFactory().getInstance("guiAdmin");
	private iCommand command;

	public Controller_Admin(){
		view.setController(this);
		getRequestData();
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

	public void getInput(String username, String status) {
		SystemControls.setUsername(username);
		command = (iCommand)Factory.getFactory().getInstance(status);
		command.execute();
	}

	public void getRequestData() {
		view.setTableData(storage.getUserRequests(), storage.getColumnNames());
	}

	@Override
	public void updateRequest(int request_id, String newStatus) {
		storage.updateUserRequest(request_id, newStatus);
	}
}

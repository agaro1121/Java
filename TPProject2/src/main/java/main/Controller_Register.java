package main;

import storage.DTO;
import storage.Status;
import classInterfaces.Interface_Controller;
import classInterfaces.Interface_Model;
import classInterfaces.Interface_View;
import factories.Factory_Model;
import factories.Factory_View;

public class Controller_Register implements Interface_Controller{
	Interface_Model model = Factory_Model.getModel(Status.REGISTER);
	private Interface_View view = Factory_View.getView(Status.REGISTER);

	public Controller_Register(){
		view.setController(this);
		this.execute();
	}

	public void execute(){
		view.getInitialDisplayNUserInput();
	}

	public void getInput(DTO input) {
		view.displayResult(model.execute(input));

	}

	//NOT NEEDED
	public void getInput(String username, String password) {}

	public void getInput(Object command) {
		
	}

}

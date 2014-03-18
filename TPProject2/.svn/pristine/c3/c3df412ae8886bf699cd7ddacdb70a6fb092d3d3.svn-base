package main;

import storage.DTO;
import storage.Status;
import classInterfaces.Interface_Controller;
import classInterfaces.Interface_Model;
import classInterfaces.Interface_View;
import factories.Factory_Model;
import factories.Factory_View;

public class Controller_Login implements Interface_Controller{
	Interface_Model model = Factory_Model.getModel(Status.WELCOME);
	private Interface_View view = Factory_View.getView(Status.WELCOME);

	public Controller_Login(){
		view.setController(this);
		this.execute();
	}

	public void execute(){
		view.getInitialDisplayNUserInput();
	}

	public void getInput(String username, String password){
		view.displayResult(model.execute(username, password));

	}

	//NOT NEEDED
	public void getInput(DTO input){}
	public void getInput(Object command) {
		
	}

}

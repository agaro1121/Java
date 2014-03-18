package anthony.garo.Main;

import Interfaces.Controller;
import Interfaces.Model;
import Interfaces.View;
import anthony.garo.Storage.DTO;

public class Login_Controller implements Controller{
	View view;
	Model model;

	public Login_Controller(View view,Model model){
		this.view = view;
		this.model = model;
		view.setController(this);
		this.execute();
	}

	public void execute(){
		view.getUserInput();
	}

	public void getInput(String username, String password){
		view.displayResult(model.execute(username, password));

	}

	public void getInput(DTO input){}

}

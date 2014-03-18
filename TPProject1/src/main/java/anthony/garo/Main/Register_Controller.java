package anthony.garo.Main;

import Interfaces.Controller;
import Interfaces.Model;
import Interfaces.View;
import anthony.garo.Storage.DTO;
import anthony.garo.UIs.WelcomePage;

public class Register_Controller implements Controller{
	View view;
	Model model;

	public Register_Controller(View v,Model m){
		this.model =  m ;
		this.view = v;
		view.setController(this);
		this.execute();
	}

	public void execute(){
		view.getUserInput();
	}

	public void getInput(DTO input) {
		view.displayResult(model.execute(input));

	}


	//NOT NEEDED
	public void getInput(String username, String password) {}

}

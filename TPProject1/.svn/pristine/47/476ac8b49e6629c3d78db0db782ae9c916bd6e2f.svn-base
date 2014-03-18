package anthony.garo.UIs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Interfaces.Controller;
import Interfaces.View;
import anthony.garo.Main.Mediator;
import anthony.garo.Main.StatusCheck;

public class LoginConsole implements View{

	private String username;
	private String password;
	private Controller controller;
	private Mediator mediator;

	public LoginConsole(Mediator m){
		this.mediator = m;
	}

	public void getUserInput(){
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.println("SYSTEM LOGIN");	
			System.out.print("Username: ");
			username = input.readLine();
			System.out.print("Password: ");
			password = input.readLine();

			controller.getInput(username, password);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setController(Controller c) {
		this.controller = c;
	}

	public void displayResult(Boolean result) {
		//		 (result) ?  mediator.handle(new StatusCheck().execute(username)) : System.out.println("fail") ;
		if(result){
			mediator.handle(new StatusCheck().execute(username));
		}else{System.out.println("Fail");

		}

	}
}
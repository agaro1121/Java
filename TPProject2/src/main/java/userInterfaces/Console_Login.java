package userInterfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import classInterfaces.Interface_Controller;
import classInterfaces.Interface_View;

import main.Mediator;
import main.StatusCheck;


public class Console_Login implements Interface_View{

	private String username;
	private String password;
	private Interface_Controller controller;
	private Mediator mediator;

	public Console_Login(Mediator m){
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

	public void setController(Interface_Controller c) {
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
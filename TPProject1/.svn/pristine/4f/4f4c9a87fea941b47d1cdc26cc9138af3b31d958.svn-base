package anthony.garo.UIs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Interfaces.Controller;
import Interfaces.View;
import anthony.garo.Main.Mediator;

public class WelcomePage implements View{

	private int selection;
	private Controller controller;
	private Mediator mediator;

	public WelcomePage(Mediator m){
		this.mediator=m;
	}

	public void getUserInput(){

		System.out.println("Welcome to the FDM Trading Platform!!!!\n\nPlease make a selection: ");
		System.out.println("1. Login\n2. Register");

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.print("Selection: ");
			selection = Integer.parseInt(input.readLine());
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			System.out.println("Not a proper selection!!!\n\n");
			getUserInput();
		} catch (IOException e) {
			e.printStackTrace();
		}

		switch (selection) {
		case 1:
			mediator.handle("login");
			break;
		case 2:
			mediator.handle("register");
			break;
		default:
			System.exit(0);
		}
	}


	public void setController(Controller c) {
		this.controller = c;

	}


	public void displayResult(Boolean result) {}

}


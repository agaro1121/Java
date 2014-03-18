package userInterfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import storage.Status;

import classInterfaces.Interface_Controller;
import classInterfaces.Interface_View;

import main.Mediator;


public class Console_Welcome implements Interface_View{

	private int selection;
	private Interface_Controller controller;
	private Mediator mediator;

	public Console_Welcome(Mediator m){
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
			mediator.handle(Status.LOGIN);
			break;
		case 2:
			mediator.handle(Status.REGISTER);
			break;
		default:
			System.exit(0);
		}
	}


	public void setController(Interface_Controller c) {
		this.controller = c;

	}


	public void displayResult(Boolean result) {}

}


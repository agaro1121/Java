package userInterfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import storage.DTO;

import classInterfaces.Interface_Controller;
import classInterfaces.Interface_View;

import main.Mediator;
import main.Controller_Register;


public class Console_Register implements Interface_View{
	private Interface_Controller controller;
	private DTO dto = new DTO();
	private Mediator mediator;

	public Console_Register(Mediator m){
		this.mediator = m;
	}

	public void getUserInput(){
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.print("First Name: ");
			dto.firstname = input.readLine();

			System.out.print("Last  Name: ");
			dto.lastname = input.readLine();

			System.out.print("Password:   ");
			dto.password=input.readLine();


			controller.getInput(dto);

		} catch (IOException e){
			e.printStackTrace();
		}

	}

	public void DisplayGood(){
		System.out.println("Registration Complete!!!\nYou will be notified shortly after being verified by our administrators.");
	}

	public void setController(Interface_Controller c){
		this.controller = c;
	}

	public void displayResult(Boolean result) {
		System.out.println((result) ? "Registration Successful": "Registration failed");
	}

}

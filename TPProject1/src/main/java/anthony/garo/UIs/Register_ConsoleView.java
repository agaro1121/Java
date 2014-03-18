package anthony.garo.UIs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Interfaces.Controller;
import Interfaces.View;
import anthony.garo.Main.Mediator;
import anthony.garo.Main.Register_Controller;
import anthony.garo.Storage.DTO;

public class Register_ConsoleView implements View{
	private Controller controller;
	private DTO dto = new DTO();
	private Mediator mediator;

	public Register_ConsoleView(Mediator m){
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

	public void setController(Controller c){
		this.controller = c;
	}

	public void displayResult(Boolean result) {
		System.out.println((result) ? "Registration Successful": "Registration failed");
	}

}

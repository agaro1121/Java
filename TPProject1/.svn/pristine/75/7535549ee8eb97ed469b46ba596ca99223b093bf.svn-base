package anthony.garo.UIs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Interfaces.Controller;
import Interfaces.View;
import anthony.garo.Main.Mediator;

public class AdminConsole implements View{
	private Integer selection;
	private Mediator mediator;

	public AdminConsole(Mediator m){
		this.mediator=m;
		this.getUserInput();
	}


	public void getUserInput(){

		System.out.println("This is the ADMIN CONSOLE VIEW");
		System.out.println("Choose an item from the menu: ");
		System.out.println("1. Add User\n2. Remove User\n3. Ban User\n4. View Logs");
		System.out.print("Enter Selection: ");


		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		try {
			selection=Integer.parseInt(input.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		switch (selection) {
		case 1:
			mediator.handle("adduser");
			break;

		case 2:
			mediator.handle("removeuser");
			break;
		case 3: 
			mediator.handle("banuser");
			break;
		case 4:
			System.out.println("Tools Under Construction");
			break;
		default:
			break;
		}

	}

	public void setController(Controller c) {}

	public void displayResult(Boolean result) {}
}

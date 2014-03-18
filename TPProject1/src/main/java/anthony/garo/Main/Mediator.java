package anthony.garo.Main;

import Interfaces.Controller;
import Interfaces.Model;
import Interfaces.Receiver;
import Interfaces.View;
import anthony.garo.CommandReceivers.Admin;
import anthony.garo.UIs.AdminConsole;
import anthony.garo.UIs.LoginConsole;
import anthony.garo.UIs.Register_ConsoleView;
import anthony.garo.UIs.WelcomePage;
import anthony.garo.commands.AddUserCommand;
import anthony.garo.commands.BanUserCommand;
import anthony.garo.commands.RemoveUserCommand;

public class Mediator implements Interfaces.Mediator {

	private Receiver receiver = new Admin();
	private AddUserCommand addUser = new AddUserCommand(receiver);
	private RemoveUserCommand removeUser =  new RemoveUserCommand(receiver);
	private BanUserCommand banUser = new BanUserCommand(receiver);


	public void handle(String s) {

		if (s.equalsIgnoreCase("login")) {
			View v = new LoginConsole(this);
			Model m = new Login_Model();
			Controller c = new Login_Controller(v,m);
		} 

		else if(s.equalsIgnoreCase("register")) {
			View v = new Register_ConsoleView(this);
			Model m = new Register_Model();
			Controller c = new Register_Controller(v,m);
		}

		else if(s.equalsIgnoreCase("admin")){
			new AdminConsole(this);
		}

		else if(s.equalsIgnoreCase("addUser")){
			this.addUser.execute();
		}

		else if(s.equalsIgnoreCase("removeUser")){
			this.removeUser.execute();
		}

		else if(s.equalsIgnoreCase("banUser")){
			this.banUser.execute();
		}

		else{System.out.println("YOU ARE NOT AN ADMIN !!!");}
	}

	public View getWelcome(){
		View welcome = new WelcomePage(this);
		return welcome;
	}

}

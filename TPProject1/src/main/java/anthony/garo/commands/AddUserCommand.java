package anthony.garo.commands;

import Interfaces.Command;
import Interfaces.Receiver;
import anthony.garo.CommandReceivers.Admin;

public class AddUserCommand implements Command{
	Receiver receiver;

	public AddUserCommand(Receiver r){
		this.receiver = r;
	}

	public void execute(){
		((Admin) receiver).addUser();

	}

}

package anthony.garo.commands;

import Interfaces.Command;
import Interfaces.Receiver;
import anthony.garo.CommandReceivers.Admin;

public class RemoveUserCommand implements Command{
	Receiver receiver;

	public RemoveUserCommand(Receiver r){
		this.receiver = r;
	}

	public void execute(){
		((Admin) receiver).removeUser();

	}

}

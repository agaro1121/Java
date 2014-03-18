package anthony.garo.commands;

import Interfaces.Command;
import Interfaces.Receiver;
import anthony.garo.CommandReceivers.Admin;

public class BanUserCommand implements Command{
	Receiver receiver;

	public BanUserCommand(Receiver r){
		this.receiver=r;
	}
	public void execute() {
		((Admin) receiver).banUser();
	}

}

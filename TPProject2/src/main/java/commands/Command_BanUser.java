package commands;

import storage.Status;
import classInterfaces.Interface_Command;
import classInterfaces.Interface_Receiver;

import commandReceivers.Receiver_Admin;

import factories.Factory_Receiver;


public class Command_BanUser implements Interface_Command{
	Interface_Receiver receiver = Factory_Receiver.getReceiver(Status.ADMIN);
	
	public void execute() {
		System.out.println("Ban User: Concrete Command");
		((Receiver_Admin) receiver).changeStatus(Status.BANNED);
		
	}

}

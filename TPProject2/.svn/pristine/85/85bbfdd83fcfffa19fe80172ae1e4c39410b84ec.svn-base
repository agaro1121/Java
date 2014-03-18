package commands;

import storage.Status;
import classInterfaces.Interface_Command;
import classInterfaces.Interface_Receiver;

import commandReceivers.Receiver_Admin;

import factories.Factory_Receiver;


public class Command_AddUser implements Interface_Command{
	Interface_Receiver receiver = Factory_Receiver.getReceiver(Status.ADMIN);

	public void execute(){
		System.out.println("Add User: Concrete Command");
		((Receiver_Admin) receiver).addUser();

	}

}

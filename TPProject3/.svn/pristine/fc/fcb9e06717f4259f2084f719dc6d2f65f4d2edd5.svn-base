package commands;

import main.Factory;
import storage.AppLiterals;
import classInterfaces.iCommand;




public class cmdBanUser implements iCommand{
	SystemControls receiver = (SystemControls)Factory.getFactory().getInstance("systemControls");

	public void execute() {
		receiver.changeStatus(AppLiterals.BANNED);
		
	}

}

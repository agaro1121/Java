package main;

import storage.Status;
import storage.Storage_Oracle;
import factories.Factory_Storage;
import factories.Factory_View;



public class App 
{
	//TODO replace system.out by Log
	//TODO Java Doc
	public static void main( String[] args )
	{	
		Factory_View.getInstance().setView(Status.SWING);
		
		Factory_Storage.getInstance().setStorage(Storage_Oracle.class.getName());
		
		Mediator.getInstance().handle(Status.WELCOME_VIEW);

	}
}

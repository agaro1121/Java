package main;

import classInterfaces.Interface_Command;
import classInterfaces.Interface_Mediator;


public class Mediator implements classInterfaces.Interface_Mediator {
	private Object newClass;

	private Mediator(){}

	public static Interface_Mediator getInstance(){
		Interface_Mediator m = new Mediator();
		return m;
	}

	public void handle(String s) {
		try {
			Class<? extends Interface_Command> tempClass = (Class<? extends Interface_Command>) Class.forName(s);
			newClass = tempClass.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		if(newClass instanceof Interface_Command){
			((Interface_Command)newClass).execute();
		}
	}
}

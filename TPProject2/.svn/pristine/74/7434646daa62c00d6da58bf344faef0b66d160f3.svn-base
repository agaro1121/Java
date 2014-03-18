package factories;

import storage.Status;
import userInterfaces.GUI_Admin;
import userInterfaces.GUI_Register;
import userInterfaces.GUI_WelcomeLogin;
import classInterfaces.Interface_View;

public class Factory_View {
	private static Interface_View view;
	private static String type;

	private Factory_View(){}

	public static Factory_View getInstance(){
		Factory_View fv = new Factory_View();
		return fv;
	}

	public void setView(String type){
		this.type = type;
	}


	public static Interface_View getView(String viewClass){
		String ui = new String();
		if(type.equalsIgnoreCase(Status.CONSOLE)){
				//Code goes here
		}

		if(type.equalsIgnoreCase(Status.SWING)){
			if(viewClass.equalsIgnoreCase(Status.REGISTER)){
				ui = GUI_Register.class.getName();
			}
			if(viewClass.equalsIgnoreCase(Status.WELCOME)){
				ui = GUI_WelcomeLogin.class.getName();
			}
			if(viewClass.equalsIgnoreCase(Status.ADMIN)){
				ui = GUI_Admin.class.getName();
			}
		}

		try {	
			Class<? extends Interface_View> viewType = (Class<? extends Interface_View>) Class.forName(ui);
			view = viewType.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return view;
	}
}

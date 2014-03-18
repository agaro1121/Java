package factories;

import main.Model_Login;
import main.Model_Register;
import storage.Status;
import classInterfaces.Interface_Model;

public class Factory_Model {
	private static Interface_Model model;

	private Factory_Model(){}

	public static Interface_Model getModel(String modelType){
		String modelClass = new String();

			if(modelType.equalsIgnoreCase(Status.REGISTER)){
				modelClass = Model_Register.class.getName();
			}
			if(modelType.equalsIgnoreCase(Status.WELCOME)){
				modelClass = Model_Login.class.getName();
			}
		try {	
			Class<? extends Interface_Model> ModelType = (Class<? extends Interface_Model>) Class.forName(modelClass);
			model = ModelType.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return model;
	}
}

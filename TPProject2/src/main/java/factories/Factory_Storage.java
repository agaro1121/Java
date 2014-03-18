package factories;

import classInterfaces.Interface_Storage;

public class Factory_Storage {
	private static Interface_Storage storage;

	private Factory_Storage(){}

	public static Factory_Storage getInstance(){
		Factory_Storage fv = new Factory_Storage();
		return fv;
	}
	
	public void setStorage(String type){
		try {	
			Class<? extends Interface_Storage> StorageType = (Class<? extends Interface_Storage>) Class.forName(type);
			storage = StorageType.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Interface_Storage getStorage(){
		return storage;
	}
}

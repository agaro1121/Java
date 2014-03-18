package factories;

import storage.Status;
import classInterfaces.Interface_Receiver;

import commandReceivers.Receiver_Admin;

public class Factory_Receiver {
	private static Interface_Receiver receiver;
	
	private Factory_Receiver(){}

	public static Interface_Receiver getReceiver(String type){
		String receiverClass = new String();

			if(type.equalsIgnoreCase(Status.ADMIN)){
				receiverClass = Receiver_Admin.class.getName();
			}
		try {	
			Class<? extends Interface_Receiver> receiverType = (Class<? extends Interface_Receiver>) Class.forName(receiverClass);
			receiver = receiverType.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return receiver;
	}
}

package main;

import org.apache.log4j.Logger;

import storage.DTO;
import storage.User;
import classInterfaces.Interface_Model;
import classInterfaces.Interface_Storage;
import factories.Factory_Storage;

public class Model_Register implements Interface_Model{
	DTO dto;
	User user = new User();
	String username;
	private static final Logger registerUser = Logger.getLogger(Model_Register.class);
	private Interface_Storage storage = Factory_Storage.getStorage();

	public boolean execute(DTO input){
		this.dto=input;
		user.setFirstname(dto.firstname);
		user.setLastname(dto.lastname);
		user.setUsername(dto.firstname.concat(".").concat(dto.lastname));
		user.setPassword(dto.password);
		user.setStatus(dto.status);

		user.setIsAdmin(input.isAdmin);
		user.setIsBroker(input.isBroker);
		user.setIsSE_Manager(input.isSE_Manager);
		user.setIsShare_Holder(input.isShare_Holder);

		storage.appendUser(user);
		registerUser.info(user.getUsername() + " wishes to be verified");
		return true;
	}

	//NOT NEEDED
	public boolean execute(){return true;}
	public boolean execute(String username, String password) {return false;}
}

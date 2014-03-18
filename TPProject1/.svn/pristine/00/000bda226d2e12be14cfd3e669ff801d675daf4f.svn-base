package anthony.garo.Main;

import org.apache.log4j.Logger;

import Interfaces.Model;
import anthony.garo.Storage.DTO;
import anthony.garo.Storage.Storage;

public class Register_Model implements Model{
	DTO dto;
	User user = new User();
	String username;
	private static final Logger registerUser = Logger.getLogger(Register_Model.class);

	public boolean execute(DTO input){
		this.dto=input;
		user.setFirstname(dto.firstname);
		user.setLastname(dto.lastname);
		user.setUsername(dto.firstname.concat(".").concat(dto.lastname));
		user.setPassword(dto.password);
		user.setStatus(dto.status);

		Storage.getInstance().userToXML(user);
		registerUser.info(user.getUsername() + " wishes to be verified");
		return true;
	}


	//NOT NEEDED
	public boolean execute(){return true;}
	public boolean execute(String username, String password) {return false;}
}

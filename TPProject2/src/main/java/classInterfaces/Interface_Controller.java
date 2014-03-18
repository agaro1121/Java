package classInterfaces;

import storage.DTO;

public interface Interface_Controller {

	public void execute();
	public void getInput(DTO input);
	public void getInput(String username,String password);
	public void getInput(Object command);

}

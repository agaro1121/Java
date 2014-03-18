package classInterfaces;

import java.util.List;
import java.util.Map;

import storage.User;

public interface iStorage {
	
	public List<User> readAllUsers();
	public Map<String,String> getUserByType(int type);
	public List<String> getUserByStatus(String command);
	public List<String> getColumnNames();
	public List<Object[]> getUserRequests();
	public boolean updateUserRequest(int request_id, String newStatus);
}

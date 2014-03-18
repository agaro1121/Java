package storage;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;


public class UserDAO {
	@Autowired
	private Properties queries;
	private JdbcTemplate JT = null;

	public JdbcTemplate getJT() {
		return JT;
	}
	public void setJT(JdbcTemplate jT) {
		JT = jT;
	}
	
	/**
	 * Gets the specific user information from the database
	 * @param String username
	 * @return User Object
	 */
	public User getUser(String username){
			User user = getJT().queryForObject(queries.getProperty("user"),new Object[]{username},new UserRowMapper());
			String perm = (String)getJT().queryForObject(queries.getProperty("isSEManager"), new Object[]{username}, String.class);
			if(perm.equals("SE Manager")){
				user.setSeManager(true);
			}
			return user;
	}
	
	/**
	 * Gets the stock exchange ID number where the user works
	 * @param userID
	 * @return int Stock Exchange ID where user works
	 */
	public int getUserSE(int userID){
		int stockExID = (int)getJT().queryForObject(queries.getProperty("userStockExID"), new Object[]{userID}, Integer.class);
		return stockExID;
	}

	public void insertUser(User user){

	}

	public void updateUser(User u){

	}

}

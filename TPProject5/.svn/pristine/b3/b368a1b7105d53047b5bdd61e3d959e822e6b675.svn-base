package storage;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
/**
 * 
 * This class maps the result set from querying the user table to a User object
 * @author anthony.garo
 * @return User object
 */
public class UserRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserID(rs.getInt(1));
		user.setFirstname(rs.getString(2));
		user.setLastname(rs.getString(3));
		user.setUsername(rs.getString(4));
		user.setPassword(rs.getString(5));
		user.setVerified(Boolean.parseBoolean(rs.getString(6)));
		user.setStatus(rs.getString(7));
		user.setTheme(rs.getString(8));
		user.setTicker(rs.getString(10));
		return user;
	}

}

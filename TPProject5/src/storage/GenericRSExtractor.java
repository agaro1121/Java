package storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
/**
 * Maps the result set from a query to a List that contains many lists of objects
 * @author anthony.garo
 * @returns an object with the result set of query that was ran
 */
public class GenericRSExtractor implements ResultSetExtractor {
	private List<Object> temp;
	
	@Override
	public Object extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		int columnCount = rs.getMetaData().getColumnCount();
		List<List<Object>> objects = new ArrayList<List<Object>>();
		
		while(rs.next()){
			temp = new ArrayList<Object>();
			for (int i = 1; i <= columnCount; i++) {
				temp.add(rs.getString(i));
			}
			objects.add(temp);
		}
		return objects;
	}

}

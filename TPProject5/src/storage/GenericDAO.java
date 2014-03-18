package storage;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * One generic Data Transfer Object that handles multiple Database operations such as:
 * Run a single query
 * Run a single query and return a field
 * Query for a large list of results with or without paramters 
 * Transactions
 * Batch Updates
 * @author Anthony Garo
 *
 */
public class GenericDAO {
	private JdbcTemplate JT = null;
	private TransactionTemplate tt = null;
	private List<List<List<Object>>> bigList = new ArrayList<List<List<Object>>>();

	public JdbcTemplate getJT() {
		return JT;
	}
	public void setJT(JdbcTemplate jT) {
		JT = jT;
	}

	public void setTt(TransactionTemplate tt) {
		this.tt = tt;
	}
	public TransactionTemplate getTt() {
		return tt;
	}
	
	/**
	 * Issue a single SQL update operation (such as an insert, update or delete statement) via a prepared statement,
	 * binding the given arguments
	 * @param String query
	 * @param Object[] params
	 */
	public void executeQuery(String query, Object[] params){
		getJT().update(query, params);
	}
	
	/**
	 * Execute a query that takes in no parameters that returns many results.
	 * @param String query
	 * @return  Results
	 */
	public Object queryNoParamsMany(String query){
		return getJT().query(query, new GenericRSExtractor());
	}
	
	/**
	 * Execute a query that takes in parameters that returns many results.
	 * @param String query
	 * @param Object[] parameters
	 * @return Results
	 */
	public Object queryParamsMany(String query, Object[] parameters){
		return getJT().query(query, parameters,new GenericRSExtractor());
	}

	/**
	 * Execute a query that returns only one field
	 * @param String query
	 * @return Object field
	 */
	public Object queryFieldReturn(String query){
		return getJT().queryForObject(query, null, Object.class);
	}

	/**
	 * Runs the same query over an array of parameters
	 * @param Stirng query
	 * @param List<Object[]> parameters
	 * @return int[] Update Counts
	 */
	public int[] batchUpdate(String query, final List<Object[]> parameters) {
		int[] updateCounts = getJT().batchUpdate(
				query,
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						Object[] row = parameters.get(i);
						for (int j = 0; j < row.length; j++) {
							int index = j+1;
							ps.setObject(index, row[j]);
						}
					}

					public int getBatchSize() {
						return parameters.size();
					}
				} );
		return updateCounts;
	}

	/**
	 * Will open one connection and run many queries in that one connection
	 * @param List<Object[]> queriesToRun
	 * @param Object[0] = String query
	 * @param Object[1] = parameters
	 */
	public void transaction(final List<Object[]> queriesToRun){
		tt.execute(new TransactionCallbackWithoutResult() {
			protected void doInTransactionWithoutResult(TransactionStatus paramTransactionStatus) {
				try{
					for (Object object : queriesToRun) {
						Object[] queryNparams = (Object[]) object;
						getJT().update(queryNparams[0].toString(), (Object[])queryNparams[1]);
					}
				}catch (Exception e) {
					paramTransactionStatus.setRollbackOnly();
				}
			}
		});
	}

	/**
	 * Runs many queries in one connection and returns the result set of all those queries combined into one list
	 * @param queriesToRun
	 * @return Results
	 */
	public Object transactionReturn(final List<Object[]> queriesToRun) {
		return	tt.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus paramTransactionStatus) {
				
				for (Object object : queriesToRun) {
					Object[] query = (Object[]) object;
					Object[] params = (Object[]) query[1];
					bigList.add((List<List<Object>>) getJT().query(query[0].toString(), params,new GenericRSExtractor()));
				}
				
				return bigList;
			}
		});
	}
}

package tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import storage.Storage_Oracle;

public class TestStorage_Oracle {
Storage_Oracle storage2 = new Storage_Oracle();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testGetColumnNames(){
		List<String> test = new ArrayList<String>();
		test = storage2.getColumnNames();
		assertNotNull(test);
	}
	
	@Ignore
	public void testGetRequests(){
		List<Object[]> test = new ArrayList<Object[]>();
		test = storage2.getUserRequests();
		assertNotNull(test);
	}
	
	@Test
	public void testUpdateRequest(){
		int id = 1;
		String status = "In Progress";
		boolean result = storage2.updateUserRequest(id, status);
		assertTrue(result);
	}
	
}

package anthony.garo.TPProject2;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import storage.Storage_Oracle;
import storage.User;

public class Oracle_Test {

	Storage_Oracle test = new Storage_Oracle();
	User user = new User();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		user.setFirstname("first");
		user.setLastname("SQLtest");
		user.setPassword("testingballs3");
		user.setIsVerified(false);
		user.setStatus("removed");
		
		user.setIsBroker(true);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Ignore
	public void testAppendUser(){
		test.appendUser(user);
	}
	
//	@Ignore
//	public void testVerifyUser(){
//		test.verifyUser("coco.bunz4");
//	}
//
//	@Ignore
//	public void testChangeStatus(){
//		test.changeStatus("a.g22", "removed");
//	}
	
	@Test
	public void testLoginCredentials(){
		test.getLoginHash();
	}
}

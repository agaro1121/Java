package anthony.garo.TPProject2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import main.Model_Login;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import classInterfaces.Interface_Model;



public class Login_Test {
	private String username, password;
	Interface_Model m = new Model_Login();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testLoginPass() {
		boolean result = m.execute("admin.admin10", "admin");
		assertTrue(result);
	}
	
//	@Test
//	public void testLoginFail() {
//		boolean result = m.execute("admin.admin1", "admin");
//		assertFalse(result);
//	}	

}

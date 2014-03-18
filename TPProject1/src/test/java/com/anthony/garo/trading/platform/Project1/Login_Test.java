package com.anthony.garo.trading.platform.Project1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Interfaces.Model;
import anthony.garo.Main.Login_Model;


public class Login_Test {
	private String username, password;
	Model m = new Login_Model();
	
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
	
	@Test
	public void testLoginFail() {
		boolean result = m.execute("admin.admin1", "admin");
		assertFalse(result);
	}	

}

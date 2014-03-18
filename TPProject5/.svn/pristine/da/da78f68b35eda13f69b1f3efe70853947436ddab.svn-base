package tests;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.apache.catalina.core.ApplicationContext;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import storage.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/../controller-servlet.xml")
public class testUserDAO {
	@Autowired
	UserDAO u;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		//ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("WebContent/WEB-INF/controller-servlet.xml");
		//u = (UserDAO) c.getBean("userDAO");
//		u = new UserDAO();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUser(){
		assertNotNull(u.getUser("admin.admin10"));
	}
	
}

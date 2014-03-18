package anthony.garo.TPProject2;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import main.Model_Register;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import storage.DTO;

import classInterfaces.Interface_Model;


public class Register_Test {

	DTO mockdto = mock(DTO.class);
	Interface_Model rm = new Model_Register();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		
		mockdto.firstname="coco";
		mockdto.lastname="bunz";
		mockdto.password="getit";
		mockdto.username="coco.bunz";
		mockdto.status="Admin";
		mockdto.isVerified=false;
	}
	
	@Test
	public void testRegisterTrue(){
		boolean result = rm.execute(mockdto);
		assertTrue(result);
	}

}

package com.anthony.garo.trading.platform.Project1;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Interfaces.Model;
import anthony.garo.Main.Register_Model;
import anthony.garo.Storage.DTO;

public class Register_Test {

	DTO mockdto = mock(DTO.class);
	Model rm = new Register_Model();
	
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

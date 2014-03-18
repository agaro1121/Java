package anthony.garo.TPProject2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import storage.Storage_XML;

public class XML_Test {
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void testChangeStatus(){
		Storage_XML.getInstance().changeStatus("coco.bunz1", "share_holder");
		
	}

}

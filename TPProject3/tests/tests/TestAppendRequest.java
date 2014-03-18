package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import main.mAppendRequest;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import storage.UserRequest;
import static org.mockito.Mockito.*;

public class TestAppendRequest {
	UserRequest mockUR = mock(UserRequest.class);
	UserRequest mockUR2 = new UserRequest();
	mAppendRequest tester;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		mockUR.setUser_ID(10);
		mockUR.setDescription("Gain Admin Permissions");
		mockUR.setStatus("Unattended");
		mockUR.setAdmin_ID(10);

		when(mockUR.getUser_ID()).thenReturn(10);
		doReturn("Become SE Manager").when(mockUR).getDescription();
		doReturn("Unattended").when(mockUR).getStatus();
		when(mockUR.getAdmin_ID()).thenReturn(10);
		
		tester = new mAppendRequest(mockUR);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testAppender(){
		boolean complete = tester.execute();
		assertTrue(complete);
	}
}

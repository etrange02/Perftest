package controls.cslavemanagement;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SlaveManagementFacadeTest {
	
	private SlaveManagementFacade slaveManagementFacade;
	
	private SlaveManagementFacade getDelegate() {
		return this.slaveManagementFacade;
	}
	
	@Before
	public void initTest() {
		this.slaveManagementFacade = new SlaveManagementFacade();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void detectSlavesTest() {
		// init
		
		// oper
		getDelegate().detectSlaves("132.227.69.0");
		
		// assert
	}

}

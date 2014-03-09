package cslave;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResponseTest {

	Response response;

	@Before 
	public void before() {
		response = new Response();
	}
	
	
	/* *********************************************************************
	 * get/set Delay Tests *************************************************
	 * *********************************************************************/
	
	@Test
	public void updateDelay() {
		
		response.setDelay(10);
		Assert.assertTrue(response.getDelay()==10);
	}
	
	@Test
	public void nullDelay() {
		
		response.setDelay(0);
		Assert.assertTrue(response.getDelay()==0);
	}
	
	@Test
	public void negativeDelay() {
		
		response.setDelay(-1);
		Assert.assertTrue(response.getDelay()==-1);
	}
	
	
	
	
	
	
	/* *********************************************************************
	 * get/set ServerBinaryResponse Tests **********************************
	 * *********************************************************************/
	
	public void updateServerBinaryResponse() {
		byte[] newServerBinaryResponse = new byte[5];
		
		newServerBinaryResponse[0] = 0;
		newServerBinaryResponse[1] = 1;
		newServerBinaryResponse[2] = 2;
		newServerBinaryResponse[3] = 3;
		newServerBinaryResponse[4] = 4;
		
		response.setServerBinaryResponse(newServerBinaryResponse);
		Assert.assertArrayEquals(
				newServerBinaryResponse, 
				response.getServerBinaryResponse());
	}
	
	public void nullServerBinaryResponse() {
		response.setServerBinaryResponse(null);
		Assert.assertTrue(response.getServerBinaryResponse()==null);
	}
}

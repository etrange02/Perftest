package cslave;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cslave.interfaces.IResponse;

/**
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */

public class ResponseTest {

    private IResponse response;

    
    
    
    
    
    /* *********************************************************************
     * INIT/CLEAN METHODS **************************************************
     * *********************************************************************/
    
    @Before 
    public void before() {
	response = new Response();
    }


    
    
    
    
    /* *********************************************************************
     * get/set Delay Tests *************************************************
     * *********************************************************************/

    @Test
    public void updateDelay() {

	//initialize the IResponse (implementation-dependent)
	((Response)response).setDelay(10);

	//assert
	Assert.assertTrue(response.getDelay()==10);
    }

    @Test
    public void nullDelay() {

	//initialize the IResponse (implementation-dependent)
	((Response)response).setDelay(0);
	
	//assert
	Assert.assertTrue(response.getDelay()==0);
    }

    @Test
    public void negativeDelay() {

	//initialize the IResponse (implementation-dependent)
	((Response)response).setDelay(-1);
	
	//assert
	Assert.assertTrue(
		"expected -1 but get "+response.getDelay(),
		response.getDelay()==-1);
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

	//initialize the IResponse (implementation-dependent)
	((Response)response).setServerBinaryResponse(newServerBinaryResponse);
	
	//assert
	Assert.assertArrayEquals(
		newServerBinaryResponse, 
		response.getServerBinaryResponse());
    }

    public void nullServerBinaryResponse() {
	
	//initialize the IResponse (implementation-dependent)
	((Response)response).setServerBinaryResponse(null);
	
	//assert
	Assert.assertTrue(response.getServerBinaryResponse()==null);
    }
}

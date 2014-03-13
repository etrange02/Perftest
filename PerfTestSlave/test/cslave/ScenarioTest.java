package cslave;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cslave.interfaces.IResponse;
import cslave.interfaces.IScenario;

/**
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */

public class ScenarioTest {

//    private IScenario scenario;
//    byte[] binaryResponse;
//
//
//
//
//
//    /* *********************************************************************
//     * INIT/CLEAN METHODS **************************************************
//     * *********************************************************************/
//
//    @Before
//    public void before() {
//	this.scenario = new Scenario();
//	this.binaryResponse = new byte[5];
//
//	binaryResponse[0] = 0;
//	binaryResponse[1] = 1;
//	binaryResponse[2] = 2;
//	binaryResponse[3] = 3;
//	binaryResponse[4] = 4;
//    }
//
//
//
//
//
//
//    /* *********************************************************************
//     * addResponse tests ***************************************************
//     * *********************************************************************/
//
//    @Test
//    public void addResponseNormalCase() {
//	int nbMatchingResponseBefore = -1;
//	int nbMatchingResponseAfter = -1;
//
//
//	//initializations
//	nbMatchingResponseBefore = 
//		findResponse(binaryResponse, 5, scenario.getResponses());
//
//	scenario.addResponse(binaryResponse, 5);
//
//	nbMatchingResponseAfter = 
//		findResponse(binaryResponse, 5, scenario.getResponses());
//
//
//	//assert
//	Assert.assertTrue(
//		"nbMatchingResponseAfter="+nbMatchingResponseAfter+" and "+
//			"nbMatchingResponseBefore="+nbMatchingResponseBefore,
//			nbMatchingResponseAfter==nbMatchingResponseBefore+1);
//    }
//
//    @Test
//    public void addResponseNullAnswer() {
//	int nbMatchingResponseBefore = -1;
//	int nbMatchingResponseAfter = -1;
//
//	//initializations
//	nbMatchingResponseBefore = 
//		findResponse(null, 5, scenario.getResponses());
//
//	scenario.addResponse(null, 5);
//
//	nbMatchingResponseAfter = 
//		findResponse(null, 5, scenario.getResponses());
//
//	//assert
//	Assert.assertTrue(
//		"nbMatchingResponseAfter="+nbMatchingResponseAfter+" and "+
//			"nbMatchingResponseBefore="+nbMatchingResponseBefore,
//			nbMatchingResponseAfter==nbMatchingResponseBefore+1);
//    }
//    
//    
//    
//    
//
//
//    /* *********************************************************************
//     * getResponses tests **************************************************
//     * *********************************************************************
//     * *********************************************************************
//     * This method was implicitly tested via addResponses tests. ***********
//     * *********************************************************************/
//    
//
//    
//    
//    
//    
//    /* *********************************************************************
//     * UTIL METHODS ********************************************************
//     * *********************************************************************/
//
//    /**
//     * @param binaryResponse
//     * @param delay
//     * @return the number of founded responses.
//     */
//    public int findResponse(
//	    byte[] binaryResponse, int delay, List<IResponse> responses) {
//
//	int nbFoundedResponse = 0;
//
//	for(IResponse response : responses) {
//
//	    if(Arrays.equals(
//		    response.getServerBinaryResponse(), 
//		    binaryResponse) == false){
//		continue;
//	    }
//
//	    if(response.getDelay()!=delay) {
//		continue;
//	    }
//
//	    nbFoundedResponse++;
//	}
//
//	return nbFoundedResponse;
//    }
}

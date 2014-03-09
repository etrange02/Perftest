package cslave;


import org.junit.Assert;
import org.junit.Test;

import cslave.interfaces.ITestParameter;

/**
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class TestParameterTest {

    ITestParameter testParameter;






    /* *********************************************************************
     * INIT/CLEAN METHODS **************************************************
     * *********************************************************************/

    @Test
    public void test() {
	testParameter = new TestParameter();
    }






    /* *********************************************************************
     * set/get port tests **************************************************
     * *********************************************************************/

    @Test 
    public void setGetPortNormalCase() {
	testParameter.setPort(7); 
	Assert.assertTrue(testParameter.getPort()==7);
    }

    @Test
    public void setGetPortNegativePort() {
	testParameter.setPort(-1); 
	Assert.assertTrue(testParameter.getPort()==-1);
    }

    
    
    
    
    
    /* *********************************************************************
     * set/get IPAddress tests *********************************************
     * *********************************************************************/

    @Test
    public void setGetIPAddressNormalCase() {
	testParameter.setIPAddress("135.229.125.147");
	Assert.assertTrue(
		testParameter.getIPAddress()
		.compareTo("135.229.125.147")==0);
    }
    
    @Test
    public void setGetIPAddressEmptyString() {
	testParameter.setIPAddress("");
	Assert.assertTrue(
		testParameter.getIPAddress()
		.compareTo("")==0);
    }
    
    @Test
    public void setGetIPAddressNullString() {
	testParameter.setIPAddress(null);
	Assert.assertTrue(testParameter.getIPAddress()==null);
    }
    
    @Test
    public void setGetIPAddressWrongFormat() {
	testParameter.setIPAddress("acd.229.125.147");
	Assert.assertTrue(
		testParameter.getIPAddress()
		.compareTo("acd.229.125.147")==0);
    }
    
    
    
    
    
    
    /* *********************************************************************
     * set/get ProtocolName tests ******************************************
     * *********************************************************************/
    
    @Test
    public void setGetProtocolNameNormalCase() {
	testParameter.setProtocolName("aProtocol");
	Assert.assertTrue(
		testParameter.getProtocolName()
		.compareTo("aProtocol")==0);
    }
    
    @Test
    public void setGetProtocolNameEmptyProtocolName() {
	testParameter.setProtocolName("");
	Assert.assertTrue(
		testParameter.getProtocolName()
		.compareTo("")==0);
    }
    
    @Test
    public void setGetProtocolNameNullProtocolName() {
	testParameter.setProtocolName(null);
	Assert.assertTrue(testParameter.getProtocolName()==null);
    }
}

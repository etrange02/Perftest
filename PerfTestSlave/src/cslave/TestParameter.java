/**
 * 
 */
package cslave;

import java.util.ArrayList;
import java.util.List;

import shared.SendableTest;
import cslave.interfaces.IResponse;
import cslave.interfaces.IScenario;
import cslave.interfaces.ITCPConnectionToTestedServer;
import cslave.interfaces.ITestParameter;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 * 
 * @author Jean-Luc Amitousa Mankoy jean-luc.amitousa-mankoy@hotmail.fr
 * @version 2.0
 */
public class TestParameter implements ITestParameter {

    private final int POOL_MAX_SIZE = 32;

    private int port;
    private String ipAddress;
    private String protocolName;

    private SendableTest test;
    private IScenario scenario;
    private  Class<? extends ITCPConnectionToTestedServer> tcpConnectionClazz;
    private List<TCPConnectionThread> pool;






    /* *********************************************************************
     * CONSTRUCTORS/CLEANS METHODS *****************************************
     * *********************************************************************/

    public TestParameter() {
	port = 0;
	ipAddress = null;
	protocolName = null;
	test = null;
	scenario = null;
	tcpConnectionClazz = null;
	pool = new ArrayList<TCPConnectionThread>();
    }

    public void stop() {
	for(TCPConnectionThread tcpCT : pool) {
	    tcpCT.interrupt();
	}
	
	pool.clear();
    }






    /* *********************************************************************
     * GETTER/SETTER METHODS ***********************************************
     * *********************************************************************/

    public String getProtocolName() {
	return protocolName;
    }

    public void setPort(int port) {
	this.port = port;
    }

    public void setIPAddress(String IPAddress) {
	this.ipAddress = IPAddress;
    }

    public void setProtocolName(String protocolName) {
	this.protocolName = protocolName;
    }

    public void setTest(SendableTest test) {
	this.test = test;
    }

    public void setTcpConnectionClazz(
	    Class<? extends ITCPConnectionToTestedServer> tcpConnectionClazz) {
	this.tcpConnectionClazz = tcpConnectionClazz;
    }

    public List<IResponse> getNextResponses() {
	return scenario.getNextResponses();
    }





    /* *********************************************************************
     * IMPORTANTS METHODS **************************************************
     * *********************************************************************/

    public void run() {

	boolean scheduled = false;
	int instructionDelay = test.getInstructionDelay();


	scenario = new Scenario(System.currentTimeMillis());


	try {
	    while(true) {

		for(TCPConnectionThread connection : pool) {
		    ITCPConnectionToTestedServer tcpCTTS =
			    connection.getITCPConnectionToTestedServer();


		    if(tcpCTTS.tryGiveWork()) {
			scheduled = true;
			break;
		    }
		}

		if(scheduled == false && pool.size() < POOL_MAX_SIZE) {

		    ITCPConnectionToTestedServer tcpCTTS =
			    tcpConnectionClazz.newInstance();


		    tcpCTTS.init(ipAddress, port, test, scenario);

		    pool.add(new TCPConnectionThread(tcpCTTS));
		    pool.get(pool.size()-1).start();
		    tcpCTTS.tryGiveWork();  //should always return true
		}

		scheduled = false;

		if(instructionDelay > 0) {
		    Thread.sleep(instructionDelay); 
		}
	    }
	}
	catch(Exception e) {
	    e.printStackTrace();
	}
    }
}
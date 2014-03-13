/**
 * 
 */
package cslave;

import java.util.ArrayList;
import java.util.List;

import shared.AbstractTest;
import shared.ITest;
import cslave.interfaces.IResponse;
import cslave.interfaces.IScenario;
import cslave.interfaces.ITCPConnectionToTestedServer;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 * 
 * @author Jean-Luc Amitousa Mankoy jean-luc.amitousa-mankoy@hotmail.fr
 * @version 1.1
 * Add delay
 */
public class TestParameter extends Thread {

    private final int POOL_MAX_SIZE = 32;
    private final int RESULT_PACK_SIZE = 1000;

    private int port;
    private String ipAddress;
    private String protocolName;
    private int  delay;

    private ITest test;
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
	delay = 0;
	test = null;
	scenario = new Scenario();
	tcpConnectionClazz = null;
	pool = new ArrayList<TCPConnectionThread>();
    }

    @Override
    public void interrupt() {
	for(TCPConnectionThread tcpCT : pool) {
	    tcpCT.interrupt();
	}
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

    public void setDelay(int delay) {
	this.delay = delay;
    }

    public void setAbstractTest(AbstractTest abstractTest) {
	this.test = abstractTest;
    }

    public void setPool(List<TCPConnectionThread> pool) {
	this.pool = pool;
    }

    public void setTcpConnectionClazz(
	    Class<? extends ITCPConnectionToTestedServer> tcpConnectionClazz) {
	this.tcpConnectionClazz = tcpConnectionClazz;
    }






    /* *********************************************************************
     * IMPORTANTS METHODS **************************************************
     * *********************************************************************/

    @Override
    public void run() {

	boolean scheduled = false;


	//TODO check every things is ok before start run

	try {
	    while(true) {

		for(TCPConnectionThread connection : pool) {
		    ITCPConnectionToTestedServer tcpCTTS =
			    connection.getITCPConnectionToTestedServer();

		    if(tcpCTTS.isRunning() == false) {
			tcpCTTS.execOneInst();
			scheduled = true;
		    }
		}

		if(scheduled == false && pool.size() < POOL_MAX_SIZE) {

		    try {

			ITCPConnectionToTestedServer tcpCTTS =
				tcpConnectionClazz.newInstance();


			tcpCTTS.init(ipAddress, port, test, scenario);

			pool.add(new TCPConnectionThread(tcpCTTS));
			pool.get(pool.size()-1).start();
			tcpCTTS.execOneInst();

			scheduled = true;

		    } catch (InstantiationException e) {
			e.printStackTrace();
		    } catch (IllegalAccessException e) {
			e.printStackTrace();
		    }
		}


		scheduled = false;

		Thread.sleep(delay*1000); //TODO Put the real unit 
	    }
	}
	catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public List<IResponse> getResponsePack() {

	List<IResponse> responses = scenario.getResponses();
	List<IResponse> resultPack = null;
	int packSize = Math.min(
		RESULT_PACK_SIZE, responses.size());


	resultPack = responses.subList(0, packSize);
	responses.removeAll(resultPack);

	return resultPack;
    }
}
package tools;
import shared.AbstractTest;
import controls.cslavemanagement.TCPConnection;
import controls.ctestplanmanagement.ScalabilityTest;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class Factory {

	/**
	 * Returns a new ScalabitityTest
	 * @param name a test name
	 * @return a ScalabilityTest
	 */
	public static AbstractTest createScalabilityTest(String name) {
		ScalabilityTest test = new ScalabilityTest();
		return test;
	}

	/**
	 * Returns a new WorkloadTest
	 * @param name a test name
	 * @return a WorkloadTest
	 */
	public static AbstractTest createWorkloadTest(String name) {
		return null;
	}

	/**
	 * Returns a new TCPConnection (Socket) which communicates with Slaves applications
	 * @param IPAddress a network address
	 * @param port a port
	 * @return a TCPConnection
	 */
	public static TCPConnection createTCPConnection(String IPAddress, int port) {
		return null;
	}
}
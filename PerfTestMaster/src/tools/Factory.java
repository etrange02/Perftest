package tools;
import java.io.IOException;
import java.net.UnknownHostException;

import controls.cslavemanagement.TCPConnection;
import controls.ctestplanmanagement.ScalabilityTest;
import controls.ctestplanmanagement.WorkloadTest;

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
	public static ScalabilityTest createScalabilityTest(String name) {
		return new ScalabilityTest(name);
	}

	/**
	 * Returns a new WorkloadTest
	 * @param name a test name
	 * @return a WorkloadTest
	 */
	public static WorkloadTest createWorkloadTest(String name) {
		return new WorkloadTest(name);
	}

	/**
	 * Returns a new TCPConnection (Socket) which communicates with Slaves applications
	 * @param IPAddress a network address
	 * @param port a port
	 * @return a TCPConnection
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static TCPConnection createTCPConnection(String IPAddress, int port) throws UnknownHostException, IOException {
		return new TCPConnection(IPAddress, port);
	}
}
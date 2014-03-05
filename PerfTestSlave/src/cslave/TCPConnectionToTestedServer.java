/**
 * 
 */
package cslave;

import cslave.interfaces.ITCPConnectionToTestedServer;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class TCPConnectionToTestedServer implements ITCPConnectionToTestedServer {

	private Scenario scenario;
	
	public TCPConnectionToTestedServer() {
		this.scenario = null;
	}

	/**
	 * Returns the scenario associated
	 * @return the scenario
	 */
	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}
}
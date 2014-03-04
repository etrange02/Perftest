/**
 * 
 */
package cslave.interfaces;

import cslave.Scenario;

/**
 * 
 * @author David Lecoconnier davi.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public interface ITCPConnectionToTestedServer {

	/**
	 * Modifies the scenario associated. It must be empty
	 * @param scenario an empty scenario
	 */
	public void setScenario(Scenario scenario);
}
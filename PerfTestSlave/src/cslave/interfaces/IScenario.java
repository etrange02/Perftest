/**
 * 
 */
package cslave.interfaces;

import java.util.List;

/**
 * 
 * @author David Lecoconnier davi.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public interface IScenario {
	
	/**
	 * Adds a new response given by the target server and the time to respond
	 * @param binaryResponse the response
	 * @param delay the answer delay
	 */
	public void addResponse(byte[] binaryResponse, List<Integer> delay);

	/**
	 * Returns the list responses handled by the scenario
	 * @return a list of responses
	 */
	public List<IResponse> getResponses();
}
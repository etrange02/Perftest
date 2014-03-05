/**
 * 
 */
package cslave;

import java.util.ArrayList;
import java.util.List;

import cslave.interfaces.IResponse;
import cslave.interfaces.IScenario;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class Scenario implements IScenario {

	private List<IResponse> responses;
	
	public Scenario() {
		this.responses = new ArrayList<>();
	}

	/**
	 * Modifies the list of responses
	 * @param response a list of responses
	 */
	public void setResponse(List<IResponse> responses) {
		this.responses = responses;
	}

	public void addResponse(byte[] binaryResponse, List<Integer> delay) {
	}

	public List<IResponse> getResponses() {
		return this.responses;
	}
}
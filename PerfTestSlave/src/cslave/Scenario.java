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
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 2.0
 */
public class Scenario implements IScenario {

	private List<IResponse> responses;



	/* *********************************************************************
	 * CONSTRUCTORS ********************************************************
	 * *********************************************************************/

	public Scenario() {
		this.responses = new ArrayList<>();
	}



	/* *********************************************************************
	 * GETTER/SETTER *******************************************************
	 * *********************************************************************/

	public void addResponse(IResponse response) {
		
		synchronized(responses) {
			this.responses.add(response);
		}
	}

	public List<IResponse> getResponses() {
		return this.responses;
	}
}
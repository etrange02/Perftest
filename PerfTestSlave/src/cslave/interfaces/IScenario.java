/**
 * 
 */
package cslave.interfaces;

import java.util.List;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 2.0
 */
public interface IScenario {

    /**
     * Adds a new response given by the target server and the time to respond
     * @param response the response to add
     */
    public void addResponse(IResponse response);
    
    /**
     * Returns the tested-server responses for the next second
     * @return a list of responses
     */
    public List<IResponse> getNextResponses();
}
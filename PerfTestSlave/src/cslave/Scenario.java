/**
 * 
 */
package cslave;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.Constants;
import cslave.interfaces.IResponse;
import cslave.interfaces.IScenario;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 2.0
 */
public class Scenario implements IScenario {

    private long absoluteStartInMillis;
    private Map<Long,List<IResponse>> responses;
    private long nextPackKey; //the key of the next pack that will be give



    /* *********************************************************************
     * CONSTRUCTORS ********************************************************
     * *********************************************************************/

    /**
     * 
     * @param absoluteStartTimeInMillis specifie the start of the test
     */
    public Scenario(long absoluteStartTimeInMillis) {
	this.absoluteStartInMillis = absoluteStartTimeInMillis;
	this.responses = new HashMap<>();
	this.nextPackKey = 0;
    }



    /* *********************************************************************
     * GETTER/SETTER *******************************************************
     * *********************************************************************/

    public void addResponse(IResponse r) {

	synchronized(responses) {

	    Long key = 
		    new Long(
			    (r.getSendTimeMillis() - absoluteStartInMillis) / 
			    (Constants.SECS_IN_INTERVAL_FOREACH_RESPPACK * 1000)
			    );
	    List<IResponse> listR = responses.get(key);

	    if(listR==null) {

		listR = new ArrayList<>();
		listR.add(r);
		responses.put(key, listR);
	    }
	    else {

		int size = listR.size();
		if(0 < size && size < Constants.MAXSIZE_FOREACH_RESPPACK) {
		    listR.add(r);
		}
	    }
	}
    }

    public List<IResponse> getNextResponses() {

	synchronized (responses) {

	    List<IResponse> nextResponses = responses.get(nextPackKey);

	    //force size 0 in order to discard new value in this pack
	    responses.put(nextPackKey, new ArrayList<IResponse>());
	    nextPackKey++;

	    return nextResponses == null ?
		    new ArrayList<IResponse>() :
			nextResponses;
	}
    }
}
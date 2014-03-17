package monitoring;

import java.util.HashMap;
import java.util.Map;

import shared.SendableResponsePack;

public class DataInfosProvider {
    
    private Map<DelayKey, Integer> delayInfos;
    private int globalSuccess;
    private int globalMiss;
    
    
    
    /* *********************************************************************
     * CONSTRUCTORS ********************************************************
     * *********************************************************************/
    
    public DataInfosProvider() {
	this.delayInfos = new HashMap<>();
	globalSuccess = 0;
	globalMiss = 0;
    }
    
    
    
    /* *********************************************************************
     * GETTERS/SETTERS *****************************************************
     * *********************************************************************/
    
    public Map<DelayKey, Integer> getDelayInfos() {
	return delayInfos;
    }
    
    public int getGlobalSuccess() {
        return globalSuccess;
    }


    public int getGlobalMiss() {
        return globalMiss;
    }
    
    
    
    
    /* *********************************************************************
     * OTHERS **************************************************************
     * *********************************************************************/
    
    public void addInfos(SendableResponsePack sendableResponsePack) {
	
	globalSuccess += sendableResponsePack.getNbSuccess();
	globalMiss += sendableResponsePack.getNbMiss();
	
//	for(int delay : sendableResponsePack.getDelays()) {
//	    
//	    DelayKey delayKey = new DelayKey(delay);
//	    Integer nbForThisDelay = delayInfos.get(delayKey);
//	    
//	    if(nbForThisDelay==null) {
//		delayInfos.put(delayKey, new Integer(1));
//	    }
//	    else {
//		delayInfos.put(delayKey, nbForThisDelay+1);
//	    }
//	}
    }
}

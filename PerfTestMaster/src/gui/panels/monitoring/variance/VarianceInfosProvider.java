package gui.panels.monitoring.variance;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import controls.cslavemanagement.SlaveManagementFacade;
import gui.panels.monitoring.AbstractInfosProvider;
import gui.panels.monitoring.RawData;

public class VarianceInfosProvider extends AbstractInfosProvider {

    /**
     * in millisec
     */
    private final static long BANDWIDTH = 1;
    private double expectedValue;

    private SortedMap<Long, Long> varianceInfos;

    
    
    /* *********************************************************************
     * CONSTRUCTORS ********************************************************
     * *********************************************************************/
    
    public VarianceInfosProvider(SlaveManagementFacade slaveManagementFacade) {
	super(slaveManagementFacade);

	varianceInfos = new TreeMap<>();
	expectedValue = -1;
    }

    
    /* *********************************************************************
     * GETTERS/SETTERS *****************************************************
     * *********************************************************************/
    
    /**
     * Return request's delay-expected-value(as defined in  probability theory).
     * computeEsperanceAndVarianceInfos  must have been called before you get 
     * it.
     * @return A double that represent the expected value
     */
    public double getExpectedValue() {

	return expectedValue;
    }
    
    
    /* *********************************************************************
     * IMPORTANTS **********************************************************
     * *********************************************************************/
    
    /**
     * Compute the expected value (as defined in probability theory) and 
     * variance infos.
     * @return the variances infos. Use getExpectedValue for the expected value.
     */
    public SortedMap<Long, Double> computeExpectedValueAndVarianceInfos() {

	SortedMap<Long, Double> variance = new TreeMap<>();


	refreshInfos();
	expectedValue = 0.0;

	for(Long key : varianceInfos.keySet()) {
	    Long value = varianceInfos.get(key);

	    //we compute % from total requests
	    Double probForKey = 
		    new Double(value) / 
		    (super.getTotalMiss()+super.getTotalSuccess());
	    
	    expectedValue += key*probForKey;
	    
	    variance.put(key, 100.0*probForKey);
	}


	return variance;
    }

    private void refreshInfos() {

	List<RawData> rawData = super.getData();


	for(RawData r : rawData) {

	    long delay = r.getReceptionTimeMillis() - r.getSendTimeMillis();
	    long bandID = delay / BANDWIDTH;
	    Long l = varianceInfos.get(bandID);

	    varianceInfos.put(bandID, l==null? 1 : l + 1);
	}
    }
}

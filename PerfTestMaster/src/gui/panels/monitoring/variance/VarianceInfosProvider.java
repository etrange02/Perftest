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
    private final long BANDWIDTH = 5;

    private SortedMap<Long, Long> varianceInfos;

    public VarianceInfosProvider(SlaveManagementFacade slaveManagementFacade) {
	super(slaveManagementFacade);

	varianceInfos = new TreeMap<>();
    }

    public SortedMap<Long, Double> getVarianceInfos() {

	SortedMap<Long, Double> variance = new TreeMap<>();

	
	refreshInfos();

	
	for(Long key : varianceInfos.keySet()) {
	    Long value = varianceInfos.get(key);

	    //we compute % from total requests
	    variance.put(
		    key, 
		    new Double(
			    value /
			    (super.getTotalMiss()+super.getTotalSuccess())
			    )
		    );
	}


	return variance;
    }

    private void refreshInfos() {

	List<RawData> rawData = super.getData();
	long absoluteTimeOrigin = super.getAbsoluteTimeOrigin();


	for(RawData r : rawData) {

	    long relativeSendTime = r.getSendTimeMillis()-absoluteTimeOrigin;
	    long bandID = relativeSendTime / BANDWIDTH;
	    Long l = varianceInfos.get(bandID);

	    varianceInfos.put(bandID, l==null? 1 : l + 1);
	}
    }
}

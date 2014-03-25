package gui.panels.monitoring.delays;

import gui.panels.monitoring.AbstractInfosProvider;
import gui.panels.monitoring.RawData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import controls.cslavemanagement.SlaveManagementFacade;

public class DelaysInfosProvider extends AbstractInfosProvider {

    /**
     * Forall k natural, all request sent into 
     * [          0           + (k+1)*(TIME_INTERVAL/2.0),
     *  (k+1)*(TIME_INTERVAL) + (k+1)*(TIME_INTERVAL/2.0) + [ 
     * are considered to belong to average for (k+1)*TIME_INTERVAL. 
     * For example, if TIME_INTERVAL = 500 all request sent in [250-750[ will
     * be used to compute average at 500.TIME_INTERVAL is in millisec.
     */
    private final long TIME_INTERVAL = 500;

    private SortedMap<Long, List<Long>> delaysInfos;

    private int nbSecToSave;



    /* *********************************************************************
     * CONSTRUCTORS ********************************************************
     * *********************************************************************/

    /**
     * @param slaveManagementFacade give us information about last received
     * responses from slaves.
     * @param nbSecToSave The number of past second to save from now.
     */
    public DelaysInfosProvider(
	    SlaveManagementFacade slaveManagementFacade,
	    int nbSecToSave) {

	super(slaveManagementFacade);

	this.delaysInfos = new TreeMap<>();
	this.nbSecToSave = nbSecToSave;
    }



    /* *********************************************************************
     * IMPORTANT ***********************************************************
     * *********************************************************************/

    /**
     * @return the list of request/s average for times in millisec
     */
    public SortedMap<Long, Double> getDelaysAverages() {

	SortedMap<Long, Double> datas = new TreeMap<>();


	refreshInfos();


	for(Long timeFromOriginInMillis : delaysInfos.keySet()) {

	    List<Long> delays = delaysInfos.get(timeFromOriginInMillis);
	    double accum = 0;

	    if(delays.size() > 0) {

		for(Long delay : delays) {
		    accum += delay;
		}

		datas.put(timeFromOriginInMillis, accum/delays.size());
	    }
	}


	return datas;
    }

    private void refreshInfos() {

	List<RawData> rawData = super.getData();
	long absoluteTimeOrigin = super.getAbsoluteTimeOrigin();



	//Add last received data for treatment
	for(RawData r : rawData) {

	    long relativeSendTime = r.getSendTimeMillis()-absoluteTimeOrigin;
	    long k = (relativeSendTime - (TIME_INTERVAL/2)) / TIME_INTERVAL + 1;
	    Long timeFromOriginInMillis = 
		    new Long((k+1)*TIME_INTERVAL);
	    List<Long> delays = delaysInfos.get(timeFromOriginInMillis);


	    if(delays==null) {

		delays = new ArrayList<>(); 

		delays.add(
			new Long(
				r.getReceptionTimeMillis() -
				r.getSendTimeMillis()
				)
			);

		delaysInfos.put(timeFromOriginInMillis, delays);
	    }
	    else {

		delays.add(
			new Long(
				r.getReceptionTimeMillis() -
				r.getSendTimeMillis()
				)
			);
	    }
	}



	//Remove old data
	removeOutdatedData();
    }

    /**
     * Remove outdated data
     */
    private void removeOutdatedData() {

	Set<Long> keySet = delaysInfos.keySet();
	Iterator<Long> keySetIterator = null;
	int nbOfFirstDataList = keySet.size() - nbSecToSave;
	List<Long> keyToRemove = null;

	if(nbOfFirstDataList > 0) {

	    keySetIterator = keySet.iterator();
	    keyToRemove = new ArrayList<Long>();

	    while(nbOfFirstDataList > 0 && keySetIterator.hasNext()) {

		Long key = keySetIterator.next();
		keyToRemove.add(key);
		nbOfFirstDataList--;
	    }

	    for(Long key : keyToRemove) {
		delaysInfos.remove(key);
	    }

	}
    }
}

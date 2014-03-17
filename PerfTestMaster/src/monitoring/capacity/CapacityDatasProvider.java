package monitoring.capacity;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import shared.SendableResponsePack;

public class CapacityDatasProvider {

    private final int MIN_TIME_INTERVAL  = 100;
    private final int MAX_TIME_INTERVAL = 1000;

    private SortedMap<TimeInMillisFromTestStart, List<Double>> timeInfos;
    private int timeInterval;



    /* *********************************************************************
     * CONSTRUCTORS ********************************************************
     * *********************************************************************/

    /**
     * @param timeInterval Forall k natural, all request sent into 
     * [0 + k*timeInterval,(k+1)*timeInterval[ are considered to have 
     * been sent at (k+1)*(timeInterval/2). timeInterval is in millisec.
     */
    public CapacityDatasProvider(int timeInterval) throws Exception {

	//ensure timeInterval is in [MIN_TIME_INTERVAL, MAX_TIME_INTERVAL]
	this.timeInterval = 
		Math.min(Math.max(timeInterval, MIN_TIME_INTERVAL), 
			MAX_TIME_INTERVAL);
	this.timeInfos = new TreeMap<>();
    }





    /* *********************************************************************
     * IMPORTANT ***********************************************************
     * *********************************************************************/

    public void addInfos(SendableResponsePack sendableResponsePack) {

	long[] sendTimeMillis = sendableResponsePack.getSendTimeMillis();
	long[] receptionTimeMillis = 
		sendableResponsePack.getReceptionTimeMilis();
	int nbResponses = sendTimeMillis.length;

	for(int i = 0; i < nbResponses; i++) {

	    //this response was sent at ((k+1)*timeInterval/2)
	    int k = ((int)(sendTimeMillis[i] / timeInterval)) + 1;

	    TimeInMillisFromTestStart tmfts = 
		    new TimeInMillisFromTestStart((k+1)*timeInterval/2);
	    List<Double> receptionTimesMillis = timeInfos.get(tmfts);

	    if(receptionTimesMillis==null) {

		receptionTimesMillis = new ArrayList<>(); 

		receptionTimesMillis.add(new Double(receptionTimeMillis[i]));
		timeInfos.put(tmfts, receptionTimesMillis);
	    }
	    else {
		receptionTimesMillis.add(new Double(receptionTimeMillis[i]));
	    }
	}
    }

    /**
     * @return the list of request/s average for times in millisec
     */
    public SortedMap<TimeInMillisFromTestStart, Double> getRequestBySecAverages() {

	SortedMap<TimeInMillisFromTestStart, Double> datas = new TreeMap<>();

	for(TimeInMillisFromTestStart tmfts : timeInfos.keySet()) {

	    //compute average

	    List<Double> receptionTimesMillis = timeInfos.get(tmfts);
	    double accum = 0;
	    double nbTimes = 0;
	    double nbReqBySecAverage = -1;

	    for(Double d : receptionTimesMillis) {
		accum += d;
		nbTimes++;
	    }

	    if(nbTimes > 0.0) {
		nbReqBySecAverage = 
			new Double(
				receptionTimesMillis.size()*
				(accum/nbTimes));
	    }

	    datas.put(tmfts, nbReqBySecAverage);
	}

	return datas;
    }
}

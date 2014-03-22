package monitoring.capacity;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import shared.SendableResponsePack;

public class CapacityDatasProvider {

	private final int MIN_TIME_INTERVAL  = 100;
	private final int MAX_TIME_INTERVAL = 1000;

	private SortedMap<TimeInMillis, List<Long>> delaysInfos;
	private int timeInterval;
	private long timeOrigin;



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
		this.delaysInfos = new TreeMap<>();
		this.timeOrigin=System.currentTimeMillis();
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

			//this response was sent at ((k+1)*timeInterval/2)*timeOrigin
			int k = ((int)((sendTimeMillis[i]-timeOrigin) / timeInterval)) + 1;

			if(k > 0) {
				System.out.println("CapacityDatasProvider.addResponsePack(): k="+k);

				TimeInMillis tm = 
						new TimeInMillis(((k+1)*timeInterval/2));
				List<Long> delays = delaysInfos.get(tm);

				if(delays==null) {

					delays = new ArrayList<>(); 

					System.out.println("CapacityDatasProvider.addResponsePack(): "+receptionTimeMillis[i]+", goto: "+new Double(receptionTimeMillis[i]));

					delays.add(new Long(
							receptionTimeMillis[i]-sendTimeMillis[i]));
					delaysInfos.put(tm, delays);
				}
				else {
					delays.add(new Long(
							receptionTimeMillis[i]-sendTimeMillis[i]));
				}
			}
		}
	}

	/**
	 * @return the list of request/s average for times in millisec
	 */
	public SortedMap<TimeInMillis, Double> getDelaysAverages() {

		SortedMap<TimeInMillis, Double> datas = new TreeMap<>();

		for(TimeInMillis tmfts : delaysInfos.keySet()) {

			//compute average

			List<Long> delays = delaysInfos.get(tmfts);
			double accum = 0;

			if(delays.size() > 0) {
				
				for(Long delay : delays) {
					accum += delay;
				}
				
				datas.put(tmfts, accum/delays.size());
			}
			
			
		}

		return datas;
	}
}

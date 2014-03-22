package gui.monitoring.capacity;

import gui.monitoring.LightWeightResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import shared.SendableResponsePack;

public class DelaysInfosProvider {

	private final int MIN_TIME_INTERVAL  = 100;
	private final int MAX_TIME_INTERVAL = 1000;

	private SortedMap<Double, List<Long>> delaysInfos;
	private int timeInterval;
	long timeOrigin;
	private List<LightWeightResponse> lightWeightResponses;



	/* *********************************************************************
	 * CONSTRUCTORS ********************************************************
	 * *********************************************************************/

	/**
	 * @param timeInterval Forall k natural, all request sent into 
	 * [0 + k*timeInterval,(k+1)*timeInterval[ are considered to have 
	 * been sent at (k+1)*(timeInterval/2.0). timeInterval is in millisec.
	 */
	public DelaysInfosProvider(int timeInterval) {

		//ensure timeInterval is in [MIN_TIME_INTERVAL, MAX_TIME_INTERVAL]
		this.timeInterval = 
				Math.min(Math.max(timeInterval, MIN_TIME_INTERVAL), 
						MAX_TIME_INTERVAL);
		delaysInfos = new TreeMap<>();
		this.lightWeightResponses = new ArrayList<>();
		
		refreshTimeOrigin();
	}



	/* *********************************************************************
	 * GETTERS/SETTERS *****************************************************
	 * *********************************************************************/

	public void addInfos(SendableResponsePack sendableResponsePack) {

		long[] sendTimeMillis = sendableResponsePack.getSendTimeMillis();
		long[] receptionTimeMillis = 
				sendableResponsePack.getReceptionTimeMilis();
		int nbResponses = sendTimeMillis.length;

		for(int i = 0; i < nbResponses; i++) {

			lightWeightResponses.add(
					new LightWeightResponse(
							sendTimeMillis[i], 
							receptionTimeMillis[i]
							)
					);
		}
	}
	
	public void refreshTimeOrigin() {
		 timeOrigin = System.currentTimeMillis();
	}

	
	
	/* *********************************************************************
	 * IMPORTANT ***********************************************************
	 * *********************************************************************/

	/**
	 * @return the list of request/s average for times in millisec
	 */
	public SortedMap<Double, Double> getDelaysAverages() {

		SortedMap<Double, Double> datas = new TreeMap<>();
		
		
		refreshInfos();
		
		
		for(Double timeFromOriginInMillis : delaysInfos.keySet()) {

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

		//Responses to delete
		List<LightWeightResponse> staleResponses = new ArrayList<>(); 
		


		delaysInfos.clear();


		for(LightWeightResponse r : lightWeightResponses) {

			//this response was sent at ((k+1)*timeInterval/2.0) from timeOrigin
			long k = ((r.getSendTimeMillis()-timeOrigin) / timeInterval) + 1;


			if(k < 0) {
				staleResponses.add(r);
			}
			else {

				Double timeFromOriginInMillis = 
						new Double(((k+1)*(timeInterval/2.0)));
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
		}

		lightWeightResponses.removeAll(staleResponses);
		
		System.out.println("DelaysInfosProvider: size="+delaysInfos.size());
	}
}

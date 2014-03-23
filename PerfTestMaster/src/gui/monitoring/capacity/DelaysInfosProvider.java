package gui.monitoring.capacity;

import gui.monitoring.RawData;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import controls.cslavemanagement.SlaveManagementFacade;
import gui.interfaces.SlaveListener;
import shared.DataBuffer;

public class DelaysInfosProvider implements SlaveListener {

	private final int MIN_TIME_INTERVAL  = 100;
	private final int MAX_TIME_INTERVAL = 1000;

	private SortedMap<Double, List<Long>> delaysInfos;
	private int timeInterval;
	long absoluteTimeOrigin; 
	long relativeTimeOrigin;
	private List<RawData> rawData;
	private SlaveManagementFacade slaveManagementFacade;


	/* *********************************************************************
	 * CONSTRUCTORS ********************************************************
	 * *********************************************************************/

	/**
	 * @param timeInterval Forall k natural, all request sent into 
	 * [0 + k*timeInterval,(k+1)*timeInterval[ are considered to have 
	 * been sent at (k+1)*(timeInterval/2.0). timeInterval is in millisec.
	 * @param slaveManagementFacade give us information about last received
	 * responses from slaves.
	 */
	public DelaysInfosProvider(
			int timeInterval, 
			SlaveManagementFacade slaveManagementFacade) {

		//ensure timeInterval is in [MIN_TIME_INTERVAL, MAX_TIME_INTERVAL]
		this.timeInterval = 
				Math.min(Math.max(timeInterval, MIN_TIME_INTERVAL), 
						MAX_TIME_INTERVAL);
		this.slaveManagementFacade = slaveManagementFacade;
		delaysInfos = new TreeMap<>();
		this.rawData = new ArrayList<>();
		relativeTimeOrigin = -1;
		absoluteTimeOrigin = System.currentTimeMillis();
	}



	/* *********************************************************************
	 * GETTERS/SETTERS *****************************************************
	 * *********************************************************************/

	private void addInfos(DataBuffer sendableResponsePack) {

		long[] sendTimeMillis = sendableResponsePack.getSendTimeMillis();
		long[] receptionTimeMillis = 
				sendableResponsePack.getReceptionTimeMilis();
		int nbResponses = sendTimeMillis.length;

		for(int i = 0; i < nbResponses; i++) {

			rawData.add(
					new RawData(
							sendTimeMillis[i], 
							receptionTimeMillis[i]
							)
					);
		}
	}

	public void refreshTimeOrigin() {

		long oldAbsoluteTimeOrigin = absoluteTimeOrigin;


		absoluteTimeOrigin = oldAbsoluteTimeOrigin+2000;

		if(relativeTimeOrigin >= 0) {
			relativeTimeOrigin+= (absoluteTimeOrigin-oldAbsoluteTimeOrigin);
		} 
		else { //it's the first time we display the graph so start to
			relativeTimeOrigin = 0;
		}
		
		System.out.println("DelaysInfosProvider.refreshTimeOrigion(): new Relative="+relativeTimeOrigin);
		System.out.println("DelaysInfosProvider.refreshTimeOrigion(): new Absolute="+absoluteTimeOrigin);
		
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
		List<RawData> staleResponses = new ArrayList<>(); 

		
		delaysInfos.clear();
		

		for(RawData r : rawData) {

			//this response was sent at ((k+1)*timeInterval/2.0) from timeOrigin
			long k = ((r.getSendTimeMillis()-absoluteTimeOrigin) / timeInterval) + 1;


			if(k < 0) {
				staleResponses.add(r);
			}
			else {

				Double timeFromOriginInMillis = 
						new Double(
								relativeTimeOrigin+((k+1)*(timeInterval/2.0)));
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

		rawData.removeAll(staleResponses);
		refreshTimeOrigin(); //for the next display
		System.out.println("DelaysInfosProvider: size="+delaysInfos.size());
	}

	@Override
	public void updateData() {
		
		System.out.println("DelaysInfosProvider.updateData(): BEGIN");
		
		for(DataBuffer responsePack : 
			slaveManagementFacade.getLastReceivedResponsesPack()) {
			
			addInfos(responsePack);
		}
		
		System.out.println("DelaysInfosProvider.updateData(): END");
	}
}

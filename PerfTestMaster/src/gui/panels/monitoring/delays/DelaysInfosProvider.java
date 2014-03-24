package gui.panels.monitoring.delays;

import gui.interfaces.SlaveListener;
import gui.panels.monitoring.RawData;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import shared.DataBuffer;
import controls.cslavemanagement.SlaveManagementFacade;

public class DelaysInfosProvider implements SlaveListener {

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
	long absoluteTimeOrigin; 
	private List<RawData> rawData;
	private SlaveManagementFacade slaveManagement;


	/* *********************************************************************
	 * CONSTRUCTORS ********************************************************
	 * *********************************************************************/

	/**
	 * @param slaveManagementFacade give us information about last received
	 * responses from slaves.
	 */
	public DelaysInfosProvider(SlaveManagementFacade slaveManagementFacade) {

		this.slaveManagement = slaveManagementFacade;
		delaysInfos = new TreeMap<>();
		this.rawData = new ArrayList<>();
		absoluteTimeOrigin = 0;
	}



	/* *********************************************************************
	 * GETTERS/SETTERS *****************************************************
	 * *********************************************************************/

	private void feedWithLastReceivedData(List<DataBuffer> dataBuffers) {

		long minAbsoluteTimeOrigin = Long.MAX_VALUE;

		for(DataBuffer dataBuffer : dataBuffers) {

			long[] sendTimeMillis = dataBuffer.getSendTimeMillis();
			long[] receptionTimeMillis = 
					dataBuffer.getReceptionTimeMilis();
			int nbResponses = sendTimeMillis.length;


			for(int i = 0; i < nbResponses; i++) {

				if(absoluteTimeOrigin==0) {
					minAbsoluteTimeOrigin = Math.min(
							minAbsoluteTimeOrigin, 
							sendTimeMillis[i]);
				}

				rawData.add(
						new RawData(
								sendTimeMillis[i], 
								receptionTimeMillis[i]
								)
						);
			}
		}


		absoluteTimeOrigin = 
				absoluteTimeOrigin == 0 ? 
						minAbsoluteTimeOrigin :
							absoluteTimeOrigin;
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


		delaysInfos.clear();


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

		
		rawData.clear();
	}

	@Override
	public void updateData() {

		List<DataBuffer> dataBuffers = new ArrayList<>();

		for(DataBuffer dataBuffer : slaveManagement.getLastReceivedData()) {
			dataBuffers.add(dataBuffer);
		}

		feedWithLastReceivedData(dataBuffers);
	}
}

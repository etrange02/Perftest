package gui.panels.monitoring;

import gui.interfaces.SlaveListener;

import java.util.ArrayList;
import java.util.List;

import shared.DataBuffer;
import controls.cslavemanagement.SlaveManagementFacade;

public class AbstractInfosProvider implements SlaveListener {

    /**
     * Flag value, used for absoluteTimeOrigin. It means that we need to 
     * compute the absolute time origin. This can be done by feeding this
     * infos provider.
     */
    protected static final int ABS_TIME_ORIGIN_UNINITIALIZED_VALUE = -1;

    private long absoluteTimeOrigin; 
    private List<RawData> rawData;
    private int nbMiss;
    private int nbSuccess;
    private SlaveManagementFacade slaveManagement;



    /* *********************************************************************
     * CONSTRUCTORS ********************************************************
     * *********************************************************************/

    /**
     * @param slaveManagementFacade give us information about last received
     * responses from slaves.
     */
    public AbstractInfosProvider(SlaveManagementFacade slaveManagementFacade) {

	this.slaveManagement = slaveManagementFacade;
	this.rawData = new ArrayList<>();
	this.absoluteTimeOrigin = ABS_TIME_ORIGIN_UNINITIALIZED_VALUE;
	this.nbMiss = 0;
	this.nbSuccess = 0;
    }



    /* *********************************************************************
     * GETTERS/SETTERS *****************************************************
     * *********************************************************************/

    protected long getAbsoluteTimeOrigin() {
	return absoluteTimeOrigin;
    }

    /**
     * Give the last received data. This is a destructive read.
     * @return a List that contains the last received data.
     */
    protected List<RawData> getData() {

	synchronized(rawData) {

	    return new ArrayList<>(rawData);
	}
    }

    /**
     * Return the total of successful request since the beginning of the 
     * test (comparison actual/expected) .
     * @return an integer that represent the total of success since the 
     * beginning of the test
     */
    public int getTotalSuccess() {
	return nbSuccess;
    }

    /**
     * Return the total of missed request since the beginning of the 
     * test (comparison actual/expected).
     * @return an integer that represent the total of miss since the 
     * beginning of the test
     */
    public int getTotalMiss() {
	return nbMiss;
    }



    /* *********************************************************************
     * OTHERS **************************************************************
     * *********************************************************************/

    /**
     * Give raw datas that will be used to compute infos. The first time this 
     * method is used will initialized the absolute time origin of the test. 
     * @param dataBuffers
     */
    private void feedWithLastReceivedData(List<DataBuffer> dataBuffers) {

	long minAbsoluteTimeOrigin = Long.MAX_VALUE;


	synchronized(rawData) {


	    rawData.clear();


	    for(DataBuffer dataBuffer : dataBuffers) {

		long[] sendTimeMillis = dataBuffer.getSendTimeMillis();
		long[] receptionTimeMillis = 
			dataBuffer.getReceptionTimeMilis();
		int nbResponses = sendTimeMillis.length;


		nbMiss += dataBuffer.getNbMiss();
		nbSuccess += dataBuffer.getNbSuccess();


		for(int i = 0; i < nbResponses; i++) {

		    if(absoluteTimeOrigin==ABS_TIME_ORIGIN_UNINITIALIZED_VALUE) {
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
		    absoluteTimeOrigin == ABS_TIME_ORIGIN_UNINITIALIZED_VALUE ? 
			    minAbsoluteTimeOrigin :
				absoluteTimeOrigin;
	}
    }


    @Override
    public void updateData() {

	List<DataBuffer> dataBuffers = new ArrayList<>();

	for(DataBuffer dataBuffer : slaveManagement.getLastReceivedData()) {

	    //TODO slave disconnection can provoc null pointer, fixme ?
	    
	    if(dataBuffer != null && dataBuffer.getSendTimeMillis() != null) {
		dataBuffers.add(dataBuffer);
	    }
	}


	feedWithLastReceivedData(dataBuffers);
    }
}

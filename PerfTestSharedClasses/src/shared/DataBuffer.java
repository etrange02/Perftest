package shared;

import shared.interfaces.ISendableResponsePack;

public class DataBuffer implements ISendableResponsePack {

	/**
	 * 
	 */
	private long[] sendTimeMillis;

	/**
	 * 
	 */
	private long[] receptionTimeMilis;

	/**
	 * 
	 */
	private int nbMiss;

	/**
	 * 
	 */
	private int nbSuccess;






	/* *********************************************************************
	 * CONSTRUCTORS/CLEANS METHODS *****************************************
	 * *********************************************************************/

	public DataBuffer(
			long[] sendTimeMillis, 
			long[] receptionTimeMilis,
			int nbSuccess,
			int nbMiss) {

		this.sendTimeMillis = sendTimeMillis;
		this.receptionTimeMilis = receptionTimeMilis;
		this.nbSuccess = nbSuccess;
		this.nbMiss = nbMiss;
	}






	/* *********************************************************************
	 * GETTER/SETTER METHODS ***********************************************
	 * *********************************************************************/

	/**
	 * 
	 * @return
	 */
	public long[] getSendTimeMillis() {
		return sendTimeMillis;
	}

	/**
	 * 
	 * @return
	 */
	public long[] getReceptionTimeMilis() {
		return receptionTimeMilis;
	}

	/**
	 * 
	 * @return
	 */
	public int getNbSuccess() {
		return nbSuccess;
	}

	/**
	 * 
	 * @return
	 */
	public int getNbMiss() {
		return nbMiss;
	}
	
//	public int countReceivedInstructions() {
//		int sum = 0;
//		Iterator<DataBuffer> iter = this.tCPDataBuffer.iterator();
//		while (iter.hasNext()) {
//			sum += iter.next().getReceptionTimeMilis().length;
//		}
//		return sum;
//	}
}

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
		
		System.out.println("DataBuffer.constructor(): nbMiss="+nbMiss);
		System.out.println("DataBuffer.constructor(): nbSuccess="+nbSuccess);
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
}

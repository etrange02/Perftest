package shared;

import shared.interfaces.ISendableResponsePack;

public class SendableResponsePack implements ISendableResponsePack {

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

	public SendableResponsePack(
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
}

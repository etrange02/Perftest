/**
 * 
 */
package cslave;

import java.util.Arrays;
import java.util.List;

import shared.SendableResponsePack;
import cslave.interfaces.IResponse;
import cslave.interfaces.ITCPConnectionToTestedServer;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 2.0
 *
 */
public abstract class Comparator {



	/* *********************************************************************
	 * GETTERS/SETTERS *****************************************************
	 * *********************************************************************/
	/**
	 * Returns the name of the protocol that this Comparator handle.
	 * @return the name of the protocol.
	 */
	public abstract String getProtocolName();

	/**
	 * Returns a protocol specific connector class that going to actually send 
	 * request.
	 * @return a specific connector class
	 */
	public abstract 
	Class<? extends ITCPConnectionToTestedServer> getTcpConnectionClazz();






	/* *********************************************************************
	 * OTHERS **************************************************************
	 * *********************************************************************/

	/**
	 * Tell if the binary response given by the server is the one expected.
	 * 
	 * @param response the Object that contains the expected
	 * and actual responses
	 * 
	 * @return true if the binary response is the one expected, else false.
	 */
	public boolean isExpectedResponse(IResponse response) {
		return Arrays.equals(
				response.getExpectedBinaryResponse(),
				response.getServerBinaryResponse());
	}

	/**
	 * Tests if the given protocol name is the one implemented by the comparator
	 * @param protocolName a protocol name
	 * @return true if it is implemented, false otherwise
	 */
	public boolean isConcernedComparator(String protocolName) {

		if(protocolName==null) {
			return false;
		}

		return protocolName.compareTo(getProtocolName()) == 0;
	}

	/**
	 * Create a light-weight response pack that can be send to 
	 * Master by network.
	 * 
	 * @param responsePack the list of all server responses
	 * @return the created sendable response pack.
	 */
	public SendableResponsePack 
	createSendableResponsePack(List<IResponse> responsePack) {

		int responsePackSize = responsePack.size();
		long[] sendTimeMillis = new long[responsePackSize];
		long[] receptionTimeMillis = new long[responsePackSize];
		int nbMiss = 0;
		int nbSuccess = 0;

		if(responsePack != null) {
			for(int i = 0; i < responsePackSize; i++) {
				IResponse response = responsePack.get(i);

				sendTimeMillis[i] = response.getSendTimeMillis();
				receptionTimeMillis[i] = response.getReceptionTimeMillis();

				if(isExpectedResponse(response) == true) {
					nbSuccess++;
				}
				else {
					nbMiss++;
				}
			}
		}


		return new SendableResponsePack(
				sendTimeMillis, 
				receptionTimeMillis, 
				nbSuccess, 
				nbMiss);
	}
}
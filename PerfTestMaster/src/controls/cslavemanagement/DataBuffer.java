/**
 * 
 */
package controls.cslavemanagement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controls.cslavemanagement.interfaces.IDataBuffer;

/**
 * A DataBuffer is a storage structure used to keep some data which comes from network.
 * It is a data keeper which accumulates data before released it (Buffer).
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class DataBuffer implements IDataBuffer {

	private List<TCPDataBuffer> tCPDataBuffer;
	
	public DataBuffer() {
		this.tCPDataBuffer = new ArrayList<>();
	}

	public List<TCPDataBuffer> getTCPDataBuffer() {
		return this.tCPDataBuffer;
	}

	public int countReceivedInstructions() {
		int sum = 0;
		Iterator<TCPDataBuffer> iter = this.tCPDataBuffer.iterator();
		while (iter.hasNext()) {
			sum += 0;//iter.next().;
		}
		return sum;
	}
}
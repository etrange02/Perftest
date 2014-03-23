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
 * It is a data keeper which accumulates data before releasing sit (Buffer).
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class DataBuffer implements IDataBuffer {

	@Override
	public List<shared.DataBuffer> getTCPDataBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countReceivedInstructions() {
		// TODO Auto-generated method stub
		return 0;
	}

//	private List<DataBuffer> tCPDataBuffer;
//	
//	public DataBuffer() {
//		this.tCPDataBuffer = new ArrayList<>();
//	}
//
//	public List<DataBuffer> getTCPDataBuffer() {
//		return this.tCPDataBuffer;
//	}
//
//	public int countReceivedInstructions() {
//		int sum = 0;
//		Iterator<DataBuffer> iter = this.tCPDataBuffer.iterator();
//		while (iter.hasNext()) {
//			sum += iter.next().getReceptionTimeMilis().length;
//		}
//		return sum;
//	}
}
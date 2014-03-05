/**
 * 
 */
package shared;

import java.io.Serializable;


/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class AbstractInstruction implements IInstruction, Serializable {
	
	private static final long serialVersionUID = 5531059771292171658L;
	private String readableRequest;
	private byte[] binaryRequest;
	private byte[] binaryResponse;
	private String name;
	
	public AbstractInstruction() {
		this.binaryRequest = new byte[1024];
		this.binaryResponse = new byte[1024];
	}

	public String getReadableRequest() {
		return this.readableRequest;
	}

	public void setReadableRequest(String readableRequest) {
		this.readableRequest = readableRequest;
	}

	public byte[] getBinaryRequest() {
		return this.binaryRequest;
	}

	public void setBinaryRequest(byte[] binaryRequest) {
		this.binaryRequest = binaryRequest;
	}

	public byte[] getBinaryResponse() {
		return this.binaryResponse;
	}

	public void setBinaryResponse(byte[] binaryResponse) {
		this.binaryResponse = binaryResponse;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
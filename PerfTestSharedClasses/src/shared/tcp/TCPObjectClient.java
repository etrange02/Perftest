package shared.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPObjectClient extends AbstractTCPClient {

	private ObjectInputStream in;
	private ObjectOutputStream out;






	/* *********************************************************************
	 * CONSTRUCTORS/CLEAN **************************************************
	 * *********************************************************************/

	public TCPObjectClient() {

		super();

		in = null;
		out = null;
	}

	/**
	 * Start a connection to the specified server in order to 
	 * send/receive Object datas
	 * @param hostname Targeted-server hostname
	 * @param port Targeted-server port
	 * @throws IOException
	 */
	public void startObjectConnection(String hostname, int port, int timeout) 
			throws IOException {

		Socket clientSocket = super.startConnection(hostname, port, timeout);

		//init "out" before "in" because other-end ObjectInputStream wait 
		//for ObjectOutputStream header informations.
		out = new ObjectOutputStream(
				clientSocket.getOutputStream());
		in = new ObjectInputStream(
				clientSocket.getInputStream());
	}

	/**
	 * Close the last established connection
	 */
	public void close() throws IOException {

		if(in != null) {
			in.close();
			in = null;
		}

		if(out != null) {
			out.close();
			out = null;
		}

		super.close();
	}





	/* *********************************************************************
	 * OTHERS **************************************************************
	 * *********************************************************************/

	/**
	 * Read an Object from the server
	 * @return The readed Object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object read() throws IOException, ClassNotFoundException{
		return in.readObject();
	}

	/**
	 * Write an Object for the server
	 * @param obj the Object to write
	 * @throws IOException
	 */
	public void write(Object obj) throws IOException {

		if(obj != null) {
			out.writeObject(obj);
		}
	}
}

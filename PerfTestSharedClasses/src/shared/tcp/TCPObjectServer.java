package shared.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPObjectServer extends AbstractTCPServer {

	private ObjectInputStream in;
	private ObjectOutputStream out;






	/* *********************************************************************
	 * CONSTRUCTORS/CLEAN **************************************************
	 * *********************************************************************/

	public TCPObjectServer() {

		super();

		in = null;
		out = null;
	}

	/**
	 * Start a connection a client in order to send/receive Object datas.
	 * @param port The port to listen on localhost
	 * @throws IOException
	 */
	public void startObjectConnection(int port) throws IOException {

		Socket clientSocket = super.acceptConnection(port);

		//init "out" before "in" because other-end ObjectInputStream wait 
		//for ObjectOutputStream header informations.
		out = new ObjectOutputStream(
				clientSocket.getOutputStream());
		in = new ObjectInputStream(
				clientSocket.getInputStream());
	}

	/**
	 * Close the connection established to the last client and stop
	 * listen. 
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
	 * Read an Object from the client
	 * @return The readed Object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object read() throws IOException, ClassNotFoundException{
		return in.readObject();
	}

	/**
	 * Write an Object for the client
	 * @param obj the Object to write
	 * @throws IOException
	 */
	public void write(Object obj) throws IOException {

		if(obj != null) {
			out.writeObject(obj);
		}
	}
}

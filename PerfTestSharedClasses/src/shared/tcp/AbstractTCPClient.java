package shared.tcp;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class AbstractTCPClient implements Closeable {

	private Socket clientSocket;






	/* *********************************************************************
	 * CONSTRUCTORS/CLEAN **************************************************
	 * *********************************************************************/

	public AbstractTCPClient() {
		clientSocket = null;
	}

	/**
	 * Start a TCP connection to a server
	 * @param hostname Targeted-server hostname
	 * @param port Rargeted-server port
	 * @return The Socket that represente the established connection
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	protected Socket startConnection(String hostname, int port) 
			throws UnknownHostException, IOException {

		clientSocket = 
				new Socket(
						InetAddress.getByName(hostname),
						port);

		return clientSocket;
	}

	/**
	 * Close the last established connection
	 */
	@Override
	public void close() throws IOException {
		if(isClosed()==false) {
			clientSocket.close();
		}
	}






	/* *********************************************************************
	 * OTHERS **************************************************************
	 * *********************************************************************/

	public boolean isClosed() {
		return (clientSocket==null || clientSocket.isClosed());
	}
}

package shared.tcp;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class AbstractTCPServer implements Closeable {

	private ServerSocket serverSocket;
	private Socket clientSocket; 






	/* *********************************************************************
	 * CONSTRUCTORS/CLEAN **************************************************
	 * *********************************************************************/

	public AbstractTCPServer() {
		serverSocket = null;
		clientSocket = null;
	}

	/**
	 * Start a TCP connection to a client.
	 * @param port Rhe port to listen on locahost
	 * @return The Socket that represent the established connection
	 * @throws IOException
	 */
	protected Socket acceptConnection(int port) throws IOException {

		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();

		return clientSocket;
	}

	/**
	 * Close the connection established to the last client and stop
	 * listen. 
	 */
	public void close() throws IOException {

		if(clientSocket!=null && clientSocket.isClosed()==false) {
			clientSocket.close();
		}

		if(serverSocket!=null && serverSocket.isClosed()==false) {
			serverSocket.close();
		}
	}

	public boolean isClosed() {
		return (clientSocket==null ||
				clientSocket.isClosed() ||
				serverSocket==null ||
				serverSocket.isClosed());
	}
}

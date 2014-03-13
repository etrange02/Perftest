package cslave;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class AbstractTCPServerConnection implements Closeable {

    private ServerSocket serverSocket; 
    private Socket clientSocket;






    /* *********************************************************************
     * CONSTRUCTORS/CLEAN **************************************************
     * *********************************************************************/

    public AbstractTCPServerConnection() {
	serverSocket = null;
	clientSocket = null;
    }
    
    public void acceptConnection(int port) throws IOException {

	close();

	serverSocket = new ServerSocket(port);
	clientSocket = serverSocket.accept();
    }

    public void close() throws IOException {

	if(clientSocket != null) {
	    clientSocket.close();
	}

	if(serverSocket != null) {
	    serverSocket.close();
	}
    }






    /* *********************************************************************
     * GETTERS/SETTERS *****************************************************
     * *********************************************************************/

    /**
     * 
     * @return
     */
    protected Socket getClientSocket() {
	return clientSocket;
    }






    /* *********************************************************************
     * OTHERS METHODS ******************************************************
     * *********************************************************************/

    /**
     * 
     * @return
     */
    public boolean isClosed() {
	return serverSocket.isClosed() || clientSocket.isClosed();
    }
}

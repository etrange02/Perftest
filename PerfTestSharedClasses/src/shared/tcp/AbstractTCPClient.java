package shared.tcp;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
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
     * @param maw time to wait for the connection. timeout < 0 means no timeout.
     * 0 means infinite timeout
     * @return The Socket that represente the established connection
     * @throws UnknownHostException
     * @throws IOException
     */
    protected Socket startConnection(String hostname, int port, int timeout) 
	    throws UnknownHostException, IOException {

	SocketAddress socketAddress = new InetSocketAddress(hostname, port);

	clientSocket = new Socket();

	if(timeout > 0){
	    clientSocket.connect(socketAddress, timeout);
	}
	else {
	    clientSocket.connect(socketAddress);
	}

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

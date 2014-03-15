package shared.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class TCPStringClient extends AbstractTCPClient {
    
    private BufferedReader in;
    private OutputStream out;

    
    
    


    /* *********************************************************************
     * CONSTRUCTORS/CLEAN **************************************************
     * *********************************************************************/

    public TCPStringClient() {
	
	super();
	
	in = null;
	out = null;
    }

    /**
     * Start a connection to the specified server in order to 
     * send/receive String  datas
     * @param hostname Targeted-server hostname
     * @param port Targeted-server port
     * @throws IOException
     */
    public void startStringConnection(String hostname, int port) throws IOException {

	Socket clientSocket = super.startConnection(hostname, port);

	in = 
		new BufferedReader(
			new InputStreamReader(
				clientSocket.getInputStream()));
	out = clientSocket.getOutputStream();
    }

    /**
     * Close the last established connection
     */
    public void close() throws IOException {
	
	in.close();
	out.close();
	
	super.close();
    }    
    
    
    

    /* *********************************************************************
     * OTHERS **************************************************************
     * *********************************************************************/
    
    /**
     * Read an String from the server
     * @return The readed String
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public String read() throws IOException{
	return in.readLine();
    }

    /**
     * Write an String for the server
     * @param message the String to write.
     * @throws IOException
     */
    public void write(String message) throws IOException {

	if(message != null) {
	    out.write(message.getBytes());
	}
    }
}

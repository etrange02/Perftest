package cslave;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import shared.Constants;

import com.sun.xml.internal.ws.Closeable;


/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class CommandTCPConnectionToMaster 
extends AbstractTCPServerConnection
implements Closeable {
    
    private BufferedReader bufferedReader;
    private OutputStream outputStream;
    
    
    
    
    
    
    /* *********************************************************************
     * CONSTRUCTORS/CLEANS METHODS *****************************************
     * *********************************************************************/

    public CommandTCPConnectionToMaster() throws IOException {
	
	bufferedReader = null;
	outputStream = null;
	
    }

    public void startConnectionToMaster() throws IOException {

	super.acceptConnection(Constants.SOCKET_COMMAND_PORT);
	
	bufferedReader = 
		new BufferedReader(
			new InputStreamReader(
				getClientSocket().getInputStream()));
	outputStream = getClientSocket().getOutputStream();
    }

  
  
    
    
    
    /* *********************************************************************
     * IMPORTANTS METHODS **************************************************
     * *********************************************************************/

    public String read() throws IOException {
	return bufferedReader.readLine();
    }
    
    public void write(String cmd) throws IOException {
	outputStream.write(cmd.getBytes());
    }
    
    public void close() {
	
	try {
	   
	    
	    bufferedReader.close();
	    outputStream.close();
	    
	    super.close();
	    
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}

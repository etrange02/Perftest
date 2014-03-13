package cslave;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.sun.xml.internal.ws.Closeable;

import cslave.interfaces.IResponse;
import shared.AbstractTest;
import shared.Constants;
import shared.Result;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class ObjectTCPConnectionToMaster 
extends AbstractTCPServerConnection
implements Closeable {

    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;






    /* *********************************************************************
     * CONSTRUCTORS/CLEANS METHODS *****************************************
     * *********************************************************************/

    public ObjectTCPConnectionToMaster() throws IOException {

	objectInputStream =  null;
	objectOutputStream = null;
    }

    public void startConnectionToMaster() throws IOException {
	
	super.acceptConnection(Constants.SOCKET_OBJECT_PORT);
	
	objectInputStream = 
		new ObjectInputStream(
			getClientSocket().getInputStream());
	objectOutputStream =
		new ObjectOutputStream(
			getClientSocket().getOutputStream());
    }
    
    public void close() {
	
	try {

	    objectInputStream.close();
	    objectOutputStream.close();
	    
	    super.close();

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }






    /* *********************************************************************
     * IMPORTANT METHODS ***************************************************
     * *********************************************************************/

    public AbstractTest read() throws ClassNotFoundException, IOException {
	return (AbstractTest)objectInputStream.readObject();
    }

    public void write(List<IResponse> responses) throws IOException {
	objectOutputStream.writeObject(responses);
    }
}

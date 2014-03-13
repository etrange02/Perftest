package cslave;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import shared.AbstractTest;
import shared.Constants;

import com.sun.xml.internal.ws.Closeable;

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

    public void write(Object object) throws IOException {
	objectOutputStream.writeObject(object);
    }
}

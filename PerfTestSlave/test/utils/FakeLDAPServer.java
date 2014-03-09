package utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class FakeLDAPServer implements Runnable {

    private static final int BERDATAS_MAXLENGTH = 9998;
    private ServerSocket serverSocket;
    private byte[] lastReadedDatas;
    private Object testor;






    /* *********************************************************************
     * CONSTRUCTORS ********************************************************
     * *********************************************************************/

    /**
     * @param testor the object to notify when we get an LDAP request
     * @param port the port to listen on localhost for LDAP request.
     * @throws IOException
     */
    public FakeLDAPServer(Object testor, int port) throws IOException {
	serverSocket = new ServerSocket(port);

	lastReadedDatas = null;
    }






    /* *********************************************************************
     * GETTERS/SETTERS *****************************************************
     * *********************************************************************/

    public byte[] getLastReadedDatas() {
	return lastReadedDatas;
    }






    /* *********************************************************************
     * IMPORTANT METHODS ***************************************************
     * *********************************************************************/

    @Override
    public void run() {

	try (
		Socket clientSocket = serverSocket.accept();
		InputStream in = clientSocket.getInputStream();
		DataOutputStream out = 
			new DataOutputStream(clientSocket.getOutputStream())) {

	    wait();
	    read(in);
	    testor.notify();
	}
	catch(Exception e) {
	    e.printStackTrace();
	}
    }






    /* *********************************************************************
     * UTIL METHODS ********************************************************
     * *********************************************************************/

    /**
     * 
     * @param in
     * @throws Exception
     */
    private void read(InputStream in) throws Exception {

	byte[] tmpArray = new byte[BERDATAS_MAXLENGTH];
	byte[] resizedArray = null;//the resized array 
	int datasSize = -1;


	datasSize = in.read(tmpArray, 0, BERDATAS_MAXLENGTH);

	if(datasSize > 0) {
	    resizedArray = new byte[datasSize];
	    System.arraycopy(
		    tmpArray, 0, 
		    resizedArray, 0, 
		    datasSize);	
	}
	else if(datasSize != -1){ //-1 means EOF: end of connection
	    throw new Exception("Error while reading BER datas");
	}




	lastReadedDatas =  resizedArray;
    }
}

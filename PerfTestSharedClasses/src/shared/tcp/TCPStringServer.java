package shared.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public final class TCPStringServer extends AbstractTCPServer {

	private BufferedReader in;
	private OutputStream out;






	/* *********************************************************************
	 * CONSTRUCTORS/CLEAN **************************************************
	 * *********************************************************************/

	public TCPStringServer() {
		super();

		in = null;
		out = null;
	}

	/**
	 * Start a connection a client in order to send/receive String datas.
	 * @param port The port to listen on localhost
	 * @throws IOException
	 */
	public void startStringConnection(int port) throws IOException {

		Socket clientSocket = super.acceptConnection(port);

		in = 
				new BufferedReader(
						new InputStreamReader(
								clientSocket.getInputStream()));
		out = clientSocket.getOutputStream();
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
	 * Read an String from the client
	 * @return The readed String
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public String read() throws IOException{
		
		if(in==null) {
			System.out.println("TCPStringServer.read(): in is null");
		}
		else {
			System.out.println("TCPStringServer.read(): in is not null");
		}
		
		return in.readLine();
	}

	/**
	 * Write an String for the client
	 * @param message the String to write.
	 * @throws IOException
	 */
	public void write(String message) throws IOException {

		if(message != null) {
			out.write(message.getBytes());
		}
	}
}

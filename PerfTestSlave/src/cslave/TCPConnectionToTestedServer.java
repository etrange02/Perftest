package cslave;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import shared.SendableTest;
import shared.interfaces.IInstruction;
import shared.tcp.AbstractTCPClient;
import cslave.interfaces.IScenario;
import cslave.interfaces.ITCPConnectionToTestedServer;

public abstract class TCPConnectionToTestedServer 
extends AbstractTCPClient
implements ITCPConnectionToTestedServer {

	private Socket clientSocket;
	private SendableTest test;
	private IScenario scenario;
	private boolean initialized;

	private Lock workLock;
	private Condition haveToWork;



	/* *********************************************************************
	 * CONSTRUCTORS/CLEANS METHODS *****************************************
	 * *********************************************************************/

	public TCPConnectionToTestedServer() {

		super();

		this.test = null;
		this.scenario = null;
		this.initialized = false;
		this.workLock = new ReentrantLock();
		this.haveToWork = workLock.newCondition();

		workLock.lock();
	}

	public void init(
			String hostname, 
			int port, 
			SendableTest test, 
			IScenario scenario) 
					throws IOException {

		clientSocket = super.startConnection(hostname, port);

		this.test = test;
		this.scenario = scenario;
		this.initialized = true;
	}



	/* *********************************************************************
	 * GETTERS/SETTERS  ****************************************************
	 * *********************************************************************/

	public Socket getClientSocket() {
		return clientSocket;
	}



	/* *********************************************************************
	 * IMPORTANT METHODS ***************************************************
	 * *********************************************************************/

	protected abstract 
	byte[] runInst(IInstruction instruction) throws Exception;

	public boolean tryGiveWork() {

		if(workLock.tryLock()) {

			haveToWork.signal();
			workLock.unlock();
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void run() {

		if(initialized) {

			List<IInstruction> instructions = test.getInstructions();
			int nbInstructions = instructions.size();
			int nextInstructionPos = 0;

			try {

				while(true) {

					IInstruction nextInst = 
							instructions.get(nextInstructionPos);

					Response response = new Response();
					response.setExpectedBinaryResponse(
							nextInst.getBinaryResponse());


					haveToWork.await();


					response.setSendTimeMillis(System.currentTimeMillis());
					response.setServerBinaryResponse(runInst(nextInst));
					response.setReceptionTimeMillis(System.currentTimeMillis());
					scenario.addResponse(response);	    


					nextInstructionPos = nextInstructionPos%nbInstructions;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}

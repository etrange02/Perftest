package controls.protocols;

import shared.interfaces.ITest;
import shared.tcp.AbstractTCPClient;

public abstract class AbstractClientForBlankTest 
extends AbstractTCPClient
implements Runnable {
	
	private ITest test;
	private int currentInstructionIndex;
	
	
	
	/* *********************************************************************
	 * CONSTRUCTORS ********************************************************
	 * *********************************************************************/
	
	public AbstractClientForBlankTest(ITest test) {
		this.test = test;
		this.currentInstructionIndex = 0;
	}
	
	
	
	/* *********************************************************************
	 * GETTERS/SETTERS *****************************************************
	 * *********************************************************************/
	
	/**
	 * Indicates the index of the instruction that must be bind with the next
	 * tcp data.
	 * @return the index of the instruction that must be bind with the next
	 * tcp data.
	 */
	public int getCurrentInstructionIndex() {
		return currentInstructionIndex;
	}
	
	public void incrCurrentInstructionIndex() {
		currentInstructionIndex++;
	}
	
	
	
	/* *********************************************************************
	 * OTHERS **************************************************************
	 * *********************************************************************/
	
	/**
	 * Indicates if another instruction has to be sent
	 * @return true if present, false otherwise
	 */
	public boolean hasNext() {
		return currentInstructionIndex < test.getInstructions().size();
	}
	
	/**
	 * Indicates that the next sent tcp data have to be bind to an instruction
	 * @return true if the next sent tcp data have to be bind,
	 * false otherwise.
	 */
	public abstract boolean toHandle();
}

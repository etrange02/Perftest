/**
 * 
 */
package shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author David Lecoconnier davi.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class AbstractTest implements ITest, Serializable {

	private static final long serialVersionUID = 8474876293913450173L;
	private int instructionDelay;
	private Status status;
	private String name;
	private List<IInstruction> instruction;
	
	public AbstractTest() {
		this.instruction = new ArrayList<IInstruction>();
		this.instructionDelay = 0;
	}

	public int getInstructionDelay() {
		return instructionDelay;
	}

	/**
	 * Modifies the delay in ms between two launched instructions
	 * @param instructionDelay the delay in ms
	 */
	public void setInstructionDelay(int instructionDelay) {
		this.instructionDelay = instructionDelay;
	}

	public Status getStatus() {
		return status;
	}

	/**
	 * Modfiies the test status 
	 * @param status a status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	/**
	 * Modifies the name of the test
	 * @param name a name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public List<IInstruction> getInstruction() {
		return instruction;
	}

	/**
	 * Modifies the list of instructions
	 * @param instruction a list of instructions
	 */
	public void setInstruction(List<IInstruction> instruction) {
		this.instruction = instruction;
	}

}
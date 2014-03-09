/**
 * 
 */
package shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public abstract class AbstractTest implements ITest, Serializable {

	private static final long serialVersionUID = 8474876293913450173L;
	private int instructionDelay;
	private Status status;
	private String name;
	private List<IInstruction> instructions;
	
	public AbstractTest() {
		this("");
	}
	
	public AbstractTest(String name) {
		this.name = name;
		this.instructions = new ArrayList<IInstruction>();
		this.instructionDelay = 0;
		this.status = Status.WAITING;
	}

	public int getInstructionDelay() {
		return this.instructionDelay;
	}

	/**
	 * Modifies the delay in ms between two launched instructions
	 * @param instructionDelay the delay in ms
	 */
	public void setInstructionDelay(int instructionDelay) {
		if (instructionDelay >= 0)
			this.instructionDelay = instructionDelay;
	}

	public Status getStatus() {
		return this.status;
	}

	/**
	 * Modfiies the test status 
	 * @param status a status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Modifies the name of the test
	 * @param name a name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public List<IInstruction> getInstructions() {
		return this.instructions;
	}

	/**
	 * Modifies the list of instructions
	 * @param instruction a list of instructions
	 */
	public void setInstructions(List<IInstruction> instructions) {
		this.instructions = instructions;
	}

}
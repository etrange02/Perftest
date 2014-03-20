package shared;

import java.io.Serializable;
import java.util.List;

import shared.interfaces.IInstruction;

public class SendableTest implements Serializable {

	private int instructionDelay;
	private List<IInstruction> instructions;
	
	public SendableTest(int instructionDelay, List<IInstruction> instructions) {
		this.instructionDelay = instructionDelay;
		this.instructions = instructions;
	}
	
	public int getInstructionDelay() {
		return instructionDelay;
	}

	public List<IInstruction> getInstructions() {
		return instructions;
	}
}

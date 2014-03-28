package shared;

import java.util.List;

import shared.interfaces.IInstruction;
import shared.interfaces.ISendableTest;

public class SendableTest implements ISendableTest {

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

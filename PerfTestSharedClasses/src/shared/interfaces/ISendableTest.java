package shared.interfaces;

import java.io.Serializable;
import java.util.List;

public interface ISendableTest extends Serializable {
	
	public int getInstructionDelay();

	public List<IInstruction> getInstructions();
}

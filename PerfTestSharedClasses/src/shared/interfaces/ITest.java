/**
 * 
 */
package shared.interfaces;

import java.util.List;

import shared.Status;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public interface ITest {

	/**
	 * Returns the delay in ms between two launched instructions
	 * @return the delay
	 */
	public int getInstructionDelay();

	/**
	 * Returns the test name
	 * @return the name
	 */
	public String getName();

	/**
	 * Returns the test status
	 * @return the status
	 */
	public Status getStatus();

	/**
	 * Returns the list of all instructions handled by the test
	 * @return the list of instructions
	 */
	public List<IInstruction> getInstructions();

	/**
	 * Turns the test into a JSON string
	 * @return the JSON string
	 */
	public String writeJSONString();

}
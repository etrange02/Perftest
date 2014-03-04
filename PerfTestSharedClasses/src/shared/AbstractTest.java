/**
 * 
 */
package shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author Etrange02
 *
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

	/** 
	 * @return instructionDelay
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getInstructionDelay() {
		return instructionDelay;
	}

	/** 
	 * @param instructionDelay instructionDelay � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setInstructionDelay(int instructionDelay) {
		this.instructionDelay = instructionDelay;
	}

	/** 
	 * @return status
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Status getStatus() {
		return status;
	}

	/** 
	 * @param status status � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/** 
	 * @return name
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getName() {
		return name;
	}

	/** 
	 * @param name name � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * @return instruction
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<IInstruction> getInstruction() {
		return instruction;
	}

	/** 
	 * @param instruction instruction � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setInstruction(List<IInstruction> instruction) {
		this.instruction = instruction;
	}

}
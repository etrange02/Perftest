/**
 * 
 */
package controls.protocols.ldap.instructions;

import shared.AbstractInstruction;

/**
 * 
 * @author David Lecoconnier david.lecoconnier@gmail.com
 * @version 1.0
 */
public abstract class LDAPInstruction extends AbstractInstruction {

	public abstract String writeJSONString();
}
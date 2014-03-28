package controls.protocols.ldap.instructions;

public class LDAPInstructionConnect extends LDAPInstruction {
	
	private String host;
	private String port;
	private String rootdn;
	private String username;
	private String password;
	
	
	
	/* *********************************************************************
	 * CONSTRUCTORS ********************************************************
	 * *********************************************************************/
	
	public LDAPInstructionConnect(
			String host, String port, String rootdn, 
			String username, String password) {
		
		this.host = host;
		this.port = port;
		this.rootdn = rootdn;
		this.username = username;
		this.password = password;
	}

	
	
	/* *********************************************************************
	 * GETTERS/SETTERS *****************************************************
	 * *********************************************************************/
	
	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getRootdn() {
		return rootdn;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	
	
	/* *********************************************************************
	 * OTHERS **************************************************************
	 * *********************************************************************/
	
	@Override
	public String writeJSONString() {
	    // TODO Auto-generated method stub
	    return null;
	}
}

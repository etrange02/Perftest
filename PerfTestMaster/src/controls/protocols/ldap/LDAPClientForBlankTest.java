/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 *  Class realized from org.apache.jmeter.protocol.ldap.sampler.LdapClient that
 *  is an class from the project Apache JMeter version 2.11
 */
package controls.protocols.ldap;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;

import shared.interfaces.IInstruction;
import shared.interfaces.ITest;
import controls.ctestplanmanagement.TCPProxy;
import controls.protocols.AbstractClientForBlankTest;
import controls.protocols.ldap.instructions.LDAPInstructionConnect;
import controls.protocols.ldap.instructions.LDAPInstructionCreate;
import controls.protocols.ldap.instructions.LDAPInstructionDelete;
import controls.protocols.ldap.instructions.LDAPInstructionDisconnect;
import controls.protocols.ldap.instructions.LDAPInstructionRead;
import controls.protocols.ldap.instructions.LDAPInstructionUpdate;

public class LDAPClientForBlankTest extends AbstractClientForBlankTest {

	private DirContext dirContext;
	private ITest test;





	/* *********************************************************************
	 * CONSTRUCTORS ********************************************************
	 * *********************************************************************/

	public LDAPClientForBlankTest(
			LDAPPlanTest ldapTestPlan, 
			String hostname, 
			ITest test) {

		super(test);

		List<IInstruction> instructions = test.getInstructions();


		this.dirContext = null;
		this.test = test;	


		if(instructions.size() > 0) {

			//remove connect/disconnect stuff from last test if necessary
			if(instructions.get(0) instanceof LDAPInstructionConnect) {
				instructions.remove(0);
			}
			if(instructions.get(instructions.size()-1) 
					instanceof LDAPInstructionDisconnect) {
				instructions.remove(instructions.size()-1);
			}
		}

		//add connect/disconnect stuff
		instructions.add(0, 
				new LDAPInstructionConnect(
						"localhost", 
						new Integer(TCPProxy.PROXY_PORT).toString(),
						ldapTestPlan.getRoot(),
						ldapTestPlan.getLogin(),
						ldapTestPlan.getPassword()
						)
				);
		instructions.add(instructions.size(), new LDAPInstructionDisconnect());
	}





	/* *********************************************************************
	 * IMPORTANTS METHODS **************************************************
	 * *********************************************************************/

	/**
	 * Connect to server.
	 */
	private void connect(
			String host, String port, String rootdn, 
			String username, String password) throws NamingException {

		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, 
				"com.sun.jndi.ldap.LdapCtxFactory"); //$NON-NLS-1$
		env.put(Context.PROVIDER_URL, 
				"ldap://" + host + ":" + port + "/" + rootdn); //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$
		env.put(Context.REFERRAL, "throw"); //$NON-NLS-1$
		env.put(Context.SECURITY_CREDENTIALS, password);
		env.put(Context.SECURITY_PRINCIPAL, username);
		dirContext = new InitialDirContext(env);
	}

	/**
	 * Disconnect from the server.
	 */
	private void disconnect() {
		try {
			if (dirContext != null) {
				dirContext.close();
				dirContext = null;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Filter the data in the ldap directory for the given search base.
	 *
	 * @param searchBase
	 *            where the search should start
	 * @param searchFilter
	 *            filter this value from the base
	 */
	private boolean searchTest(String searchBase, String searchFilter) 
			throws NamingException {

		SearchControls searchcontrols = 
				new SearchControls(SearchControls.SUBTREE_SCOPE,
						1L, // count limit
						0, // time limit
						null,// attributes (null = all)
						false,// return object ?
						false);// dereference links?
		NamingEnumeration<?> ne = 
				dirContext.search(searchBase, searchFilter, searchcontrols);
		// System.out.println("Loop "+ne.toString()+" "+ne.hasMore());
		// while (ne.hasMore()){
		// Object tmp = ne.next();
		// System.out.println(tmp.getClass().getName());
		// SearchResult sr = (SearchResult) tmp;
		// Attributes at = sr.getAttributes();
		// System.out.println(at.get("cn"));
		// }
		// System.out.println("Done "+ne.hasMore());
		return ne.hasMore();
	}

	/**
	 * Modify the attribute in the ldap directory for the given string.
	 *
	 * @param mods
	 *            add all the entry in to the ModificationItem
	 * @param string
	 *            the string (dn) value
	 */
	private void modifyTest(ModificationItem[] mods, String string) 
			throws NamingException {
		dirContext.modifyAttributes(string, mods);
	}

	/**
	 * Create the attribute in the ldap directory for the given string.
	 *
	 * @param basicattributes
	 *            add all the entry in to the basicattribute
	 * @param string
	 *            the string (dn) value
	 */
	private void createTest(BasicAttributes basicattributes, String string) 
			throws NamingException {
		// DirContext dc = //TODO perhaps return this?
		dirContext.createSubcontext(string, basicattributes);
	}

	/**
	 * Delete the attribute from the ldap directory.
	 *
	 * @param string
	 *            the string (dn) value
	 */
	private void deleteTest(String string) throws NamingException {
		dirContext.destroySubcontext(string);
	}






	/* *********************************************************************
	 * OTHERS  *************************************************************
	 * *********************************************************************/

	@Override
	public boolean toHandle() {
		return true;
	}

	@Override
	public void run() {

		List<IInstruction> instructions = test.getInstructions();

		try {

			for(IInstruction inst : instructions) {

				System.out.println("LDAPClientForBlankTest(): handle "+inst.getClass().getSimpleName());

				if(inst instanceof LDAPInstructionConnect) {

					LDAPInstructionConnect connect = 
							(LDAPInstructionConnect) inst;
					connect(connect.getHost(), 
							connect.getPort(),
							connect.getRootdn(),
							connect.getUsername(),
							connect.getPassword());
				}
				else if (inst instanceof LDAPInstructionDisconnect) {

					disconnect();
				}
				else if(inst instanceof LDAPInstructionCreate) {

					LDAPInstructionCreate create = 
							(LDAPInstructionCreate) inst;
					createTest(
							create.getBasicAttributes(), 
							create.getDNEntry());
				}
				else if(inst instanceof LDAPInstructionRead) {

					LDAPInstructionRead read = (LDAPInstructionRead) inst;
					searchTest(read.getSearchBase(), read.getSearchFilter());
				}
				else if (inst instanceof LDAPInstructionUpdate) {

					LDAPInstructionUpdate update = (LDAPInstructionUpdate)inst;
					modifyTest(
							update.getModificationItems(), 
							update.getDNEntry());
				}
				else if (inst instanceof LDAPInstructionDelete) {

					LDAPInstructionDelete delete = (LDAPInstructionDelete) inst;
					deleteTest(delete.getToDelete());
				}

				super.incrCurrentInstructionIndex();
			}

			instructions.remove(instructions.size()-1); //remove disconnection instruction TODO proprify

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
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
 *  Class realized from org.apache.jmeter.protocol.ldap.sampler.LdapClientExt
 *  that is an class from the project Apache JMeter version 2.11
 */
package controls.protocols.ldap.instructions;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class LDAPInstructionUpdate extends LDAPInstruction {

	private String dnEntry;
	private List<ModificationItem> modifications;



	/* *********************************************************************
	 * CONSTRUCTORS ********************************************************
	 * *********************************************************************/

	/**
	 * 
	 * @param dnEntry
	 */
	public LDAPInstructionUpdate(String dnEntry){
		this.dnEntry = dnEntry;
		modifications = new ArrayList<>();
	}





	/* *********************************************************************
	 * GETTERS/SETTERS *****************************************************
	 * *********************************************************************/

	/**
	 * 
	 * @return
	 */
	public String getDNEntry() {
		return dnEntry;
	}

	/**
	 * 
	 * @return
	 */
	public ModificationItem[] getModificationItems() {
		return modifications.toArray(new ModificationItem[0]);
	}



	/* *********************************************************************
	 * OTHERS **************************************************************
	 * *********************************************************************/

	/**
	 * 
	 * @param attributeName
	 * @param attributValue
	 */
	public void addAttribute(String attributeName, String attributValue) {

		boolean validArgs = true;

		//Check args
		validArgs = validArgs && (attributeName!=null);
		validArgs = validArgs && (attributeName.isEmpty()==false);
		validArgs = validArgs && (attributValue!=null);
		validArgs = validArgs && (attributValue.isEmpty()==false);



		if(validArgs) {

			BasicAttribute attr = 
					new BasicAttribute(attributeName, attributValue);

			modifications.add(
					new ModificationItem(
							DirContext.ADD_ATTRIBUTE, 
							attr
							)
					);
		}
	}

	/**
	 * 
	 * @param attributeName
	 * @param attributValue
	 */
	public void modifyAttribute(String attributeName, String attributValue) {
		boolean validArgs = true;

		//Check args
		validArgs = validArgs && (attributeName!=null);
		validArgs = validArgs && (attributeName.isEmpty()==false);
		validArgs = validArgs && (attributValue!=null);
		validArgs = validArgs && (attributValue.isEmpty()==false);



		if(validArgs) {

			BasicAttribute attr = 
					new BasicAttribute(attributeName, attributValue);

			modifications.add(
					new ModificationItem(
							DirContext.REPLACE_ATTRIBUTE, 
							attr
							)
					);
		}
	}

	/**
	 * 
	 * @param attributeName
	 */
	public void removeAtribute(String attributeName) {

		boolean validArgs = 
				(attributeName!=null) && 
				(attributeName.isEmpty()==false);


		if(validArgs) {

			BasicAttribute attr = 
					new BasicAttribute(attributeName);

			modifications.add(
					new ModificationItem(
							DirContext.REMOVE_ATTRIBUTE, 
							attr
							)
					);
		}
	}
}
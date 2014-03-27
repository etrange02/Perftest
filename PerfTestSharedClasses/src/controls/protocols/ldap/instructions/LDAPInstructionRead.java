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

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.1
 */
public class LDAPInstructionRead extends LDAPInstruction {
	
	private String searchBase;
	private String searchFilter;
	
	public LDAPInstructionRead() {
		this.searchBase = null;
		this.searchFilter = null;
	}

	
	
	/* *********************************************************************
	 * GETTERS/SETTERS *****************************************************
	 * *********************************************************************/
	
	public String getSearchBase() {
		return searchBase;
	}
	
	public void setSearchBase(String searchBase) {
	    this.searchBase = searchBase;
	}

	public String getSearchFilter() {
		return searchFilter;
	}
	

	public void setSearchFilter(String searchFilter) {
	    this.searchFilter = searchFilter;
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

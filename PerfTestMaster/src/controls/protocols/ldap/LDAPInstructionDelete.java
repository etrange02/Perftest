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
package controls.protocols.ldap;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class LDAPInstructionDelete extends LDAPInstruction {

	private String toDelete;
	
	public LDAPInstructionDelete(String toDelete) {
		this.toDelete = toDelete;
	}

	public String getToDelete() {
		return toDelete;
	}
}

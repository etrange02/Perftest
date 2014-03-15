package main;

import cslave.TestManager;
import cslave.protocols.ldap.LDAPComparator;

public class Main {

	public static void main(String[] args) throws Exception {
		Thread t = null;
		TestManager testManager = new TestManager();
		testManager.addComparator(new LDAPComparator());
		
		t = new Thread(testManager);
		t.start();
		t.wait();
	}

}

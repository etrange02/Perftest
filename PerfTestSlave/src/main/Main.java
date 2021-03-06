package main;

import cslave.TestManager;
import cslave.protocols.ldap.LDAPComparator;

public class Main {

	public static void main(String[] args) throws Exception {

		TestManager testManager = new TestManager();

		testManager.addComparator(new LDAPComparator());

		testManager.start();
		testManager.join();
	}
}

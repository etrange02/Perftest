package main;

import java.io.IOException;

import cslave.TestManager;
import cslave.protocols.ldap.LDAPComparator;

public class Main {

	public static void main(String[] args) throws IOException {
		TestManager testManager = new TestManager();
		testManager.addComparator(new LDAPComparator());
	}

}

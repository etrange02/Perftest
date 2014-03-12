package tools;

import java.util.List;

import javax.swing.JFrame;

import tools.widgets.InstructionCreator;
import tools.widgets.TestCreator;
import tools.widgets.TestPlanCreator;

public class GUIFactory {
	
	public static TestPlanCreator testPlanCreator(JFrame parent, String title, boolean modal, List<String> protocolList) {
		return new TestPlanCreator(parent, title, modal, protocolList);
	}
	
	public static TestCreator testCreator(JFrame parent, String title, boolean modal) {
		return new TestCreator(parent, title, modal);
	}
	
	public static InstructionCreator instructionCreator(JFrame parent, String title, boolean modal) {
		return new InstructionCreator(parent, title, modal);
	}
}

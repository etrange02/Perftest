/**
 * 
 */
package controls.ctestplanmanagement.interfaces;

import java.util.List;

import controls.cslavemanagement.interfaces.ISlave;
import controls.ctestplanmanagement.AbstractMonitoredTest;
import shared.ITest;


/**
 * 
 * @author David Lecoconnier davi.lecoconnier@gmail.com
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public interface ITestPlan {
	
	public String getName();

	public List<AbstractMonitoredTest> getTests();

	public int getPort();
	
	public String writeJSONString();
	
	public List<ITest> getTest();

	public List<ISlave> getTargets();
}
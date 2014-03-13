/**
 * 
 */
package cslave.interfaces;

import shared.ITest;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 2.0
 */
public interface ITCPConnectionToTestedServer extends Runnable {

    public void init(String hostname, int port, ITest test, IScenario scenario);

    public boolean isRunning();

    public void execOneInst();
}
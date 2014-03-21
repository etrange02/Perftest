package shared.interfaces;

import java.io.Serializable;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public interface ISendableResponsePack extends Serializable {
    
    /**
     * 
     * @return
     */
    public long[] getSendTimeMillis();
    
    /**
     * 
     * @return
     */
    public long[] getReceptionTimeMilis();
    
    /**
     * 
     * @return
     */
    public int getNbSuccess();
    
    /**
     * 
     * @return
     */
    public int getNbMiss();
}

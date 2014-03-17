package cslave.interfaces;

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 2.1
 */
public interface IResponse {

    /**
     * 
     * @param sendTimeMillis
     */
    public void setSendTimeMillis(long sendTimeMillis);

    /** 
     * @return the time in milliseconds at witch the
     * request was send to the target server.
     */
    public long getSendTimeMillis();

    /**
     * 
     * @param receptionTimeMillis
     */
    public void setReceptionTimeMillis(long receptionTimeMillis);

    /**
     * 
     * @return
     */
    public long getReceptionTimeMillis();
    
    /**
     * Returns the response expected by the server
     * @return the expected binary response
     */
    public byte[] getExpectedBinaryResponse();

    /**
     * Returns the response given by the server
     * @return the binary response
     */
    public byte[] getServerBinaryResponse();
}
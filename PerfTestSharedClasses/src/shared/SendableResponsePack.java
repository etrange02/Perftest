package shared;

public class SendableResponsePack {
    
    private int[] delays;
    private int nbMiss;
    private int nbSuccess;
    
    
    
    
    
    
    /* *********************************************************************
     * CONSTRUCTORS/CLEANS METHODS *****************************************
     * *********************************************************************/
    
    public SendableResponsePack(int[] delays, int nbMiss, int nbSuccess) {
	
	this.delays = delays;
	this.nbSuccess = nbSuccess;
	this.nbMiss = nbMiss;
    }
    
    
    
    
    
    
    /* *********************************************************************
     * GETTER/SETTER METHODS ***********************************************
     * *********************************************************************/
    
    public int[] getDelays() {
	return delays;
    }
    
    public int getNbSuccess() {
	return nbSuccess;
    }
    
    public int getNbMiss() {
	return nbMiss;
    }
}

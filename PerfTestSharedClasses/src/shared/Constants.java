package shared;
/**
 * 
 */

/**
 * 
 * @author Jean-Luc Amitousa-Mankoy jeanluc.amitousa.mankoy@gmail.com
 * @version 1.0
 */
public class Constants {

	/**
	 * Socket port for objects transmission
	 */
	public static final int SOCKET_OBJECT_PORT = 2000;

	/**
	 * Socket port for commands transmission
	 */
	public static final int SOCKET_COMMAND_PORT = 2001;
	
	/**
	 * The maximum number of bytes that can be transmit in one time
	 */
	public static final int TCP_DATAS_MAXLENGTH = 9998;
	
	
	//Commands
	public static final String RUN_CMD = "run";
	public static final String STOP_CMD = "stop";
	public static final String RESET_CMD = "reset";
	public static final String DEPLOY_CMD = "deploy";
	public static final String RESULT_CMD = "result";
	
	//Responses
	public static final String OK_RESP = "ok";
	public static final String KO_RESP = "ko";
	
	/**
	 * Each sent response pack contains all responses for an time-interval.
	 * This constant specifies the size of this interval.
	 */
	public static final long SECS_IN_INTERVAL_FOREACH_RESPPACK = 3;
	
	/**
	 * The max number of responses we want for each responses pack
	 */
	public static final long MAXSIZE_FOREACH_RESPPACK = 100000;
}
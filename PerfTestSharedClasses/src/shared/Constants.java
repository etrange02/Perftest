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
	
	
	//Commands
	public static final String RUN_CMD = "run";
	public static final String STOP_CMD = "stop";
	public static final String RESET_CMD = "reset";
	public static final String DEPLOY_CMD = "deploy";
	public static final String RESULT_CMD = "result";
	
	//Responses
	public static final String OK_RESP = "ok";
	public static final String KO_RESP = "ko";
	
}
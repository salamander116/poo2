package exceptions;

/**
 * 
 * @author 50503_50647
 *
 */
public class AdminNotOn extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "User cannot execute this command.";
	}

}

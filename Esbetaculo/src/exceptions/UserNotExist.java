package exceptions;
/**
 * 
 * @author 50503_50647
 *
 */
public class UserNotExist extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public String getMessage(){
		return "User does not exist.";
	}

}

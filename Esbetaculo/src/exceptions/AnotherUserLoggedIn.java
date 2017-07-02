package exceptions;

/**
 * 
 * @author 50503_50647
 *
 */
public class AnotherUserLoggedIn extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "Another user is logged in.";
	}

}

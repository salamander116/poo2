package exceptions;
/**
 * 
 * @author 50503_50647
 *
 */
public class UserLoggedIn extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "User already logged in.";
	}

}

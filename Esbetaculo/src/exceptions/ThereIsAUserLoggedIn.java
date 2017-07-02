package exceptions;
/**
 * 
 * @author 50503_50647
 *
 */
public class ThereIsAUserLoggedIn extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "There is a user logged in.";
	}
	

}

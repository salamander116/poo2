package exceptions;
/**
 * 
 * @author 50503_50647
 *
 */
public class NoUserLoggedIn  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "No user is logged in.";
	}
	

}

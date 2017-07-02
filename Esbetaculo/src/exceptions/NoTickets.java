package exceptions;
/**
 * 
 * @author 50503_50647
 *
 */
public class NoTickets extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "There are not sufficient seats for the request.";
	}

}

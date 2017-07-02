package exceptions;
/**
 * 
 * @author 50503_50647
 *
 */
public class UnknownShow extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "Unknown type of show.";
	}
	

}

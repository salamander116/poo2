package exceptions;
/**
 * 
 * @author 50503_50647
 *
 */
public class ShowAlreadyExists extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public String getMessage(){
		return "Show already exists.";
	}
	
	

}

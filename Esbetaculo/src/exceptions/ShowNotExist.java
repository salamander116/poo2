package exceptions;
/**
 * 
 * @author 50503_50647
 *
 */
public class ShowNotExist extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "Show does not exist.";
	}

}

package exceptions;
/**
 * 
 * @author 50503_50647
 *
 */
public class BandAlreadyExists extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "Artist name already exists.";
	}

}

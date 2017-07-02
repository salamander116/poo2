package shows;

import java.time.LocalDate;
/**
 * 
 * @author 50503_50647
 *
 */
public interface ShowsInterface {
	
	/**
	 * 
	 * @return The name of the show
	 */
	String getName();

	/**
	 * 
	 * @return The description of the show
	 */
	String getDesc();
	
	/**
	 * 
	 * @return The start day of the show
	 */
	LocalDate getStartDate();
	
	/**
	 * 
	 * @param i
	 * @return The price giving a quantity i 
	 */
	int getPrice(int i);
	/**
	 * 
	 * @return The number of soldTickets of each show
	 */
	int getSoldTickets();
	
	

}

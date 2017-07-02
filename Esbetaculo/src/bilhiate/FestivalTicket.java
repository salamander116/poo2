package bilhiate;

import java.time.LocalDate;
import java.util.Iterator;
/**
 * 
 * @author 50503_50647
 *
 */
public interface FestivalTicket {

	/**
	 * 
	 * @return The iterator of the bought tickets list of a user
	 */
	Iterator<LocalDate> returnDates();
	
	/**
	 * 
	 * @return The number of tickets bought of the festival
	 */
	int totalTickets();

}
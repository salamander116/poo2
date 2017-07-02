package bilhiate;

import java.time.LocalDate;
/**
 * 
 * @author 50503_50647
 *
 */

public interface ConcertTicket extends Ticket{
	
	/**
	 * 
	 * @return the date of the concert of the bought ticked
	 */
	LocalDate getDate();
}
